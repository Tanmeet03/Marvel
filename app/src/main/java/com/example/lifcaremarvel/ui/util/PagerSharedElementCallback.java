package com.example.lifcaremarvel.ui.util;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.ViewCompat;

public class PagerSharedElementCallback extends SharedElementCallback {

    private final List<View> mSharedElementViews;

    public PagerSharedElementCallback() {
        mSharedElementViews = new ArrayList<>();
    }

    public void setSharedElementViews(@NonNull View... sharedElementViews) {
        mSharedElementViews.clear();
        mSharedElementViews.addAll(Arrays.asList(sharedElementViews));
    }

    @Override
    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
        if (!mSharedElementViews.isEmpty()) {
            removeObsoleteElements(names, sharedElements, mapObsoleteElements(names));
            for (View sharedElementView : mSharedElementViews) {
                String transitionName = ViewCompat.getTransitionName(sharedElementView);
                names.add(transitionName);
                sharedElements.put(transitionName, sharedElementView);
            }
        }
    }

    @Override
    public void onSharedElementEnd(List<String> sharedElementNames,
                                   List<View> sharedElements,
                                   List<View> sharedElementSnapshots) {
        for (View sharedElementView : mSharedElementViews) {
            forceSharedElementLayout(sharedElementView);
        }
    }

    /**
     * Maps all views that don't start with "android" namespace.
     *
     * @param names All shared element names.
     * @return The obsolete shared element names.
     */
    @NonNull
    private List<String> mapObsoleteElements(List<String> names) {
        List<String> elementsToRemove = new ArrayList<>(names.size());
        for (String name : names) {
            if (name.startsWith("android")) continue;
            elementsToRemove.add(name);
        }
        return elementsToRemove;
    }

    /**
     * Removes obsolete elements from names and shared elements.
     *
     * @param names            Shared element names.
     * @param sharedElements   Shared elements.
     * @param elementsToRemove The elements that should be removed.
     */
    private void removeObsoleteElements(List<String> names,
                                        Map<String, View> sharedElements,
                                        List<String> elementsToRemove) {
        if (elementsToRemove.size() > 0) {
            names.removeAll(elementsToRemove);
            for (String elementToRemove : elementsToRemove) {
                sharedElements.remove(elementToRemove);
            }
        }
    }

    private void forceSharedElementLayout(View view) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(view.getWidth(),
                View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(view.getHeight(),
                View.MeasureSpec.EXACTLY);
        view.measure(widthSpec, heightSpec);
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

}
