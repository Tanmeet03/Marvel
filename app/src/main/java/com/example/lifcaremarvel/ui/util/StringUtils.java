package com.example.lifcaremarvel.ui.util;

import android.content.Context;

import com.example.lifcaremarvel.R;
import com.example.lifcaremarvel.api.MarvelException;

import java.io.IOException;

import androidx.annotation.NonNull;

public class StringUtils {

    private StringUtils() {
    }

    public static String getApiErrorMessage(@NonNull Context context, @NonNull Throwable e) {
        if (e instanceof IOException) {
            return context.getString(R.string.connection_error);
        } else if (e instanceof MarvelException) {
            return context.getString(R.string.server_error);
        } else {
            return "";
        }
    }

}
