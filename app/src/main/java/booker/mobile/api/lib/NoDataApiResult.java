package booker.mobile.api.lib;

import android.graphics.Path;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Optional;

import booker.mobile.api.lib.Error;

public class NoDataApiResult {
    @SerializedName("error")
    @Expose
    private Error error = null;

    public Error getError() { return error; }
}
