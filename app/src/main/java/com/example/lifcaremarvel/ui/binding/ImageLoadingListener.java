package com.example.lifcaremarvel.ui.binding;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import androidx.annotation.NonNull;

public abstract class ImageLoadingListener implements RequestListener<String, GlideDrawable> {

    @Override
    public final boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
        onSuccess();
        return false;
    }

    @Override
    public final boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
        onFailed(e);
        return false;
    }

    public abstract void onSuccess();

    public abstract void onFailed(@NonNull Exception e);
}
