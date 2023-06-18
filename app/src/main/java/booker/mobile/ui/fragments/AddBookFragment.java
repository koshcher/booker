package booker.mobile.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import booker.mobile.R;
import booker.mobile.api.Api;
import booker.mobile.api.lib.ApiResult;
import booker.mobile.api.lib.NoDataApiResult;
import booker.mobile.api.models.Book;
import booker.mobile.api.models.BookCreation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookFragment extends Fragment {

    Button backFromAddBtn;
    Button confirmAddBtn;
    EditText titleInput;
    EditText authorInput;
    EditText descriptionInput;

    public AddBookFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        backFromAddBtn = view.findViewById(R.id.backFromAddBtn);
        confirmAddBtn = view.findViewById(R.id.confirmAddBtn);
        titleInput = view.findViewById(R.id.titleInput);
        authorInput = view.findViewById(R.id.authorInput);
        descriptionInput = view.findViewById(R.id.descriptionInput);

        backFromAddBtn.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.layoutContainer, new BookListFragment()).commit();
        });

        confirmAddBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString().trim();
            if(title.isEmpty()) {
                Toast.makeText(getContext(), "Title can't be empty", Toast.LENGTH_LONG).show();
                return;
            }

            String author = authorInput.getText().toString().trim();
            if(author.isEmpty()) {
                Toast.makeText(getContext(), "Author can't be empty", Toast.LENGTH_LONG).show();
                return;
            }

            String description = descriptionInput.getText().toString().trim();
            if(description.isEmpty()) {
                Toast.makeText(getContext(), "Description can't be empty", Toast.LENGTH_LONG).show();
                return;
            }

            Api.book().postOne(new BookCreation(title, description, author)).enqueue(new Callback<NoDataApiResult>() {
                @Override
                public void onResponse(Call<NoDataApiResult> call, Response<NoDataApiResult> response) {
                    NoDataApiResult result = response.body();

                    if(result.getError() != null) {
                        Toast.makeText(getContext(), result.getError().getMessage(), Toast.LENGTH_LONG).show();
                        return;
                    }

                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.layoutContainer, new BookListFragment()).commit();
                }

                @Override
                public void onFailure(Call<NoDataApiResult> call, Throwable t) {
                    Toast.makeText(getContext(),"Something went wrong.", Toast.LENGTH_LONG).show();
                }
            });
        });


        return view;
    }
}