package booker.mobile.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("id")
    @Expose
    String id;

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
    public String getId() { return id; }
    public String getTitle() { return title; }
}
