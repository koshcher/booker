package booker.mobile.api.controllers;

import java.util.List;

import booker.mobile.api.lib.ApiResult;
import booker.mobile.api.models.Book;
import booker.mobile.api.models.BookCreation;
import booker.mobile.api.lib.NoDataApiResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookController {
    @GET("/api/v1/book")
    Call<ApiResult<List<Book>>> getAll();

    @GET("/api/v1/book/{id}")
    Call<ApiResult<Book>> getOne(@Path("id") String id);

    @POST("/api/v1/book")
    Call<NoDataApiResult> postOne(@Body BookCreation bookCreation);

    @DELETE("/api/v1/book/{id}")
    Call<NoDataApiResult> deleteOne(@Path("id") String id);
}
