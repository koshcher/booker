package booker.mobile.api;

import booker.mobile.api.controllers.AuthController;
import booker.mobile.api.controllers.BookController;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit;
    private static final String base = "https://booker.hop.sh";
    private static String accessToken = "";

    private static Retrofit getClient() {
        if (retrofit == null) {
            // adding auth token
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + accessToken)
                        .build();
                return chain.proceed(request);
            }).build();

            retrofit = new Retrofit.Builder()
                .baseUrl(base)
                .client(client)
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

    public synchronized static void setAccessToken(String token) {
        accessToken = token;
    }
    public static boolean isAccessTokenExist() {
        return !accessToken.isEmpty();
    }
}
