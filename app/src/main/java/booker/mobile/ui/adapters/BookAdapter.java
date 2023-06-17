package booker.mobile.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import booker.mobile.R;
import booker.mobile.api.models.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private final List<Book> books;

    public BookAdapter(List<Book> books) {
        this.books = books;
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
    }

    @Override
    public int getItemCount() { return books.size(); }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        private final TextView idText;
        private final TextView titleText;
        private final TextView authorText;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            idText = itemView.findViewById(R.id.idText);
            titleText = itemView.findViewById(R.id.titleText);
            authorText = itemView.findViewById(R.id.authorText);
        }
    }
}
