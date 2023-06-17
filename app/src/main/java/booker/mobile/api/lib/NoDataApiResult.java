package booker.mobile.api.lib;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import booker.mobile.api.lib.Error;

public class NoDataApiResult {
    @SerializedName("error")
    @Expose
    @Nullable
    private Error error;

    public Error getError() { return error; }
}
