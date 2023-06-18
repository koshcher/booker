package booker.mobile.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import booker.mobile.R;
import booker.mobile.api.Api;
import booker.mobile.api.lib.NoDataApiResult;
import booker.mobile.api.models.Book;
import booker.mobile.ui.fragments.AuthFragment;
import booker.mobile.ui.fragments.BookListFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private final List<Book> books;
    private final FragmentManager fragmentManager;
    private final Context context;

    public BookAdapter(List<Book> books, FragmentManager fragmentManager, Context context) {
        this.fragmentManager = fragmentManager;
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);
        return new BookViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);

        holder.idText.setText("Id: " + book.getId());
        holder.titleText.setText("Title: " + book.getTitle());
        holder.authorText.setText("Author: " + book.getAuthor());
        holder.deleteBookBtn.setOnClickListener(v -> {
            if(!Api.isAccessTokenExist()) {
                fragmentManager.beginTransaction().replace(R.id.layoutContainer, new AuthFragment()).commit();
                return;
            }
//book.getId()
            Api.book().deleteOne(book.getId()).enqueue(new Callback<NoDataApiResult>() {
                @Override
                public void onResponse(Call<NoDataApiResult> call, Response<NoDataApiResult> response) {
                    NoDataApiResult result = response.body() ;

                    if(result == null) {
                        Toast.makeText(context,"Sorry! Can't delete book.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if(result.getError() != null) {
                        Toast.makeText(context, result.getError().getMessage(), Toast.LENGTH_LONG).show();
                        return;
                    }

                    fragmentManager.beginTransaction()
                            .replace(R.id.layoutContainer, new BookListFragment()).commit();
                }

                @Override
                public void onFailure(Call<NoDataApiResult> call, Throwable t) {
                    Toast.makeText(context, "Sorry! Can't delete book.", Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    @Override
    public int getItemCount() { return books.size(); }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        private final TextView idText;
        private final TextView titleText;
        private final TextView authorText;
        private final Button deleteBookBtn;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            idText = itemView.findViewById(R.id.idText);
            titleText = itemView.findViewById(R.id.titleText);
            authorText = itemView.findViewById(R.id.authorText);
            deleteBookBtn = itemView.findViewById(R.id.deleteBookBtn);
        }
    }
}
