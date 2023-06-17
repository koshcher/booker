package booker.mobile.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterBody {
    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("password")
    @Expose
    String password;

    @SerializedName("confirmPassword")
    @Expose
    String confirmPassword;
}

