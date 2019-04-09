package com.example.lifcaremarvel.ui.binding;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lifcaremarvel.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

/**
 * This class <i>CustomBindingAdapter</i> manipulate how values with imageurl expressions are set to views
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public class CustomBindingAdapter {

    @BindingAdapter(value = {"imageUrl", "listener"}, requireAll = false)
    public static void loadImage(@NonNull ImageView imageView, @Nullable String url, @Nullable ImageLoadingListener listener) {
        Glide.with(imageView.getContext().getApplicationContext())
                .load(url)
                .listener(listener)
                .error(R.drawable.ic_no_image)
                .into(imageView);
    }

}