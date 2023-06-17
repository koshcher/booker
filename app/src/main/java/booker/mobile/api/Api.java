package booker.mobile.api;

import booker.mobile.api.controllers.AuthController;
import booker.mobile.api.controllers.BookController;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit;
    private static final String base = "https://booker.hop.sh"; //"https://booker-api-b41r.onrender.com"; // "https://localhost:44314";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl(base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }

    public static AuthController auth() {
        return Api.getClient().create(AuthController.class);
    }

    public static BookController book() {
        return Api.getClient().create(BookController.class);
    }
}
