package com.example.lifcaremarvel.ui.section;

import com.example.lifcaremarvel.api.data.SectionPOJO;
import com.example.lifcaremarvel.ui.AbsPresenter;

import java.util.List;

import androidx.annotation.NonNull;

public class SectionPresenter extends AbsPresenter<SectionContract.View> implements SectionContract.Actions {

    private final List<SectionPOJO> mEntries;
    private final int mInitialPosition;

    public SectionPresenter(@NonNull List<SectionPOJO> entries, int initialPosition) {
        mEntries = entries;
        mInitialPosition = initialPosition;
    }

    @Override
    public void attachView(@NonNull SectionContract.View view) {
        super.attachView(view);
        mView.showItems(mEntries, mInitialPosition);
    }

    @Override
    public void closeClick() {
        mView.close();
    }

}
