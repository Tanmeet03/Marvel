package com.example.lifcaremarvel.ui.home.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lifcaremarvel.BR;
import com.example.lifcaremarvel.R;
import com.example.lifcaremarvel.api.data.CharacterPOJO;
import com.example.lifcaremarvel.ui.adapter.MarvelArrayAdapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class CharacterAdapterMarvel extends MarvelArrayAdapter<CharacterPOJO, CharacterAdapterMarvel.ViewHolder> {

    private static final int VIEW_TYPE_DEFAULT = 0;
    private static final int VIEW_TYPE_MORE = 1;

    private final int mItemLayoutRes;
    private final OnLoadListener mLoadListener;
    private boolean mHasMore;
    private boolean mLoading;

    CharacterAdapterMarvel(@LayoutRes int itemLayoutRes, @Nullable OnItemClickListener itemClickListener, @Nullable OnLoadListener loadListener) {
        super(itemClickListener);
        mItemLayoutRes = itemLayoutRes;
        mLoadListener = loadListener;
    }

    void setHasMore(boolean hasMore) {
        if (hasMore != mHasMore) {
            mHasMore = hasMore;
            notifyDataSetChanged();
        }
    }

    void setLoading(boolean loading) {
        mLoading = loading;
    }

    @Override
    public int getItemCount() {
        int count = super.getItemCount();
        if (mHasMore) {
            count++;
        }
        return count;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = mItemLayoutRes;
        if (viewType == VIEW_TYPE_MORE) {
            layout = R.layout.item_list_more;
        }
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (holder.getItemViewType() == VIEW_TYPE_MORE) {
            if (mLoadListener != null && !mLoading) {
                mLoadListener.onLoadMore(getItemCount() - 1);
                mLoading = true;
            }
            return;
        }

        holder.mBinding.setVariable(BR.character, getItem(position));
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1 && mHasMore) {
            return VIEW_TYPE_MORE;
        }
        return VIEW_TYPE_DEFAULT;
    }

    @Override
    public long getItemId(int position) {
        if (getItemViewType(position) == VIEW_TYPE_MORE) {
            return RecyclerView.NO_ID;
        }
        return getItem(position).getId();
    }

    public interface OnLoadListener {
        void onLoadMore(int offset);
    }

    class ViewHolder extends MarvelArrayAdapter.ViewHolder {
        ViewHolder(@NonNull ViewDataBinding binding) {
            super(binding);
        }
    }
}
