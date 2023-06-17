package booker.mobile.api.controllers;

import booker.mobile.api.lib.ApiResult;
import booker.mobile.api.models.LoginBody;
import booker.mobile.api.models.RegisterBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthController {
    @POST("/api/v1/auth/login")
    Call<ApiResult<String>> passwordLogin(@Body LoginBody body);

    @POST("/api/v1/auth/register")
    Call<ApiResult<String>> passwordRegister(@Body RegisterBody body);
}
