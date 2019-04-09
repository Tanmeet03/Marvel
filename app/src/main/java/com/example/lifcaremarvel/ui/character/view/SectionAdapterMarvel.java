package com.example.lifcaremarvel.ui.character.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lifcaremarvel.BR;
import com.example.lifcaremarvel.R;
import com.example.lifcaremarvel.api.data.SectionPOJO;
import com.example.lifcaremarvel.ui.adapter.MarvelArrayAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * This class <i>SectionAdapterMarvel</i> is used to update different section under marvel character details activity
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public class SectionAdapterMarvel extends MarvelArrayAdapter<SectionPOJO, SectionAdapterMarvel.ViewHolder> {

    private final String mImageTransitionName;
    private final int mType;

    SectionAdapterMarvel(Context context, @SectionPOJO.Type int type, OnItemClickListener listener) {
        super(listener);
        mImageTransitionName = context.getString(R.string.transition_section_image);
        mType = type;
    }

    @SectionPOJO.Type
    public int getType() {
        return mType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_list_section, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mBinding.setVariable(BR.section, getItem(position));
        holder.mBinding.setVariable(BR.imageTransition, mImageTransitionName + mType + position);
        holder.mBinding.executePendingBindings();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    class ViewHolder extends MarvelArrayAdapter.ViewHolder {
        ViewHolder(ViewDataBinding binding) {
            super(binding);
        }
    }

}
