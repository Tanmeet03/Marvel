package com.example.lifcaremarvel.ui.adapter;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This class <i>MarvelArrayAdapter</i> is root adapter class which has implemented item click listener interface
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public abstract class MarvelArrayAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final OnItemClickListener mItemClickListener;
    private final List<T> mItems;

    public MarvelArrayAdapter(@Nullable OnItemClickListener itemClickListener) {
        super();
        mItemClickListener = itemClickListener;
        mItems = new ArrayList<>();
        setHasStableIds(true);
    }

    @Override
    public abstract long getItemId(int position);

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public T getItem(int position) {
        if (mItems == null) {
            return null;
        }
        return mItems.get(position);
    }

    public void setItems(List<T> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public List<T> getItems() {
        return new ArrayList<>(mItems);
    }

    public interface OnItemClickListener<T, VH extends RecyclerView.ViewHolder, VB extends ViewDataBinding> {
        void onItemClick(MarvelArrayAdapter<T, VH> adapter, VB binding, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ViewDataBinding mBinding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && mItemClickListener != null) {
                mItemClickListener.onItemClick(MarvelArrayAdapter.this, mBinding, position);
            }
        }
    }

}
