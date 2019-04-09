package com.example.lifcaremarvel.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

/**
 * This class <i>AbsPresenter</i> is abstract class for presenter class
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public abstract class AbsPresenter<V> {

    protected V mView;

    protected AbsPresenter() {
    }

    /**
     * Call this method in {@link Activity#onCreate}
     * or {@link Fragment#onAttach(Context)} to attach the MVP View object
     */
    @CallSuper
    public void attachView(@NonNull V view) {
        mView = view;
    }

    /**
     * Call this method in {@link Activity#onDestroy()}
     * or {@link Fragment#onDetach()} to detach the MVP View object
     */
    @CallSuper
    public void detachView() {
        mView = null;
    }

    /**
     * Check if the view is attached.
     * This checking is only necessary when returning from an asynchronous call
     */
    protected final boolean isViewAttached() {
        return mView != null;
    }

}
