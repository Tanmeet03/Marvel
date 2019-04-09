package com.example.lifcaremarvel.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class <i>MarvelCallback</i> is abstract class to implement response success and failure methods
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public abstract class MarvelCallback<T> implements Callback<T> {

    @Override
    public final void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onResult(response.body());
        } else {
            onError(new MarvelException(response.code(), response.message()));
        }
    }

    @Override
    public final void onFailure(Call<T> call, Throwable t) {
        if (!call.isCanceled()) { // Cancellation is not an error because we cancel it
            onError(t);
        }
    }

    public abstract void onResult(T data);

    public abstract void onError(Throwable e);

}
