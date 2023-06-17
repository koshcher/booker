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

    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public String getTitle() { return title; }
}
