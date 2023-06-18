package booker.mobile.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookCreation {
    @SerializedName("title")
    @Expose
    String title;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("author")
    @Expose
    String author;

    public BookCreation(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }
}
