package booker.mobile.api.lib;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResult<T> {
    @SerializedName("data")
    @Expose
    @Nullable
    T data;

    @SerializedName("error")
    @Expose
    @Nullable
    Error error;

    @Nullable
    public T getData() { return data; }
    @Nullable
    public Error getError() { return error; }
}
