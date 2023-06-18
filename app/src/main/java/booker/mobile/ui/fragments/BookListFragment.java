package booker.mobile.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import booker.mobile.R;
import booker.mobile.api.Api;
import booker.mobile.api.lib.ApiResult;
import booker.mobile.api.models.Book;
import booker.mobile.ui.adapters.BookAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListFragment extends Fragment {

    Button addNewBookBtn;
    RecyclerView bookListRecyclerView;
    List<Book> books = Collections.emptyList();

    public BookListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);

        addNewBookBtn = view.findViewById(R.id.addNewBookBtn);
        bookListRecyclerView = view.findViewById(R.id.bookListRecyclerView);

        addNewBookBtn.setOnClickListener(v -> {
            FragmentManager manager = getParentFragmentManager();

            if(!Api.isAccessTokenExist()) {
                manager.beginTransaction().replace(R.id.layoutContainer, new AuthFragment()).commit();
                return;
            }

            manager.beginTransaction().replace(R.id.layoutContainer, new AddBookFragment()).commit();
        });

        Api.book().getAll().enqueue(new Callback<ApiResult<List<Book>>>() {
            @Override
            public void onResponse(Call<ApiResult<List<Book>>> call, Response<ApiResult<List<Book>>> response) {
                if(!response.isSuccessful()) return;

                ApiResult<List<Book>> result = response.body();

                if(result.getError() != null) {
                    Toast.makeText(getContext(), result.getError().getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                books = result.getData();

                bookListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                BookAdapter adapter = new BookAdapter(books);
                bookListRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ApiResult<List<Book>>> call, Throwable t) {
                Toast.makeText(getContext(), "Sorry! Can't load books from the server.", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}