package com.example.lifcaremarvel.ui.home;

import android.view.View;

import com.example.lifcaremarvel.api.MarvelApi;
import com.example.lifcaremarvel.api.MarvelCallback;
import com.example.lifcaremarvel.api.MarvelResult;
import com.example.lifcaremarvel.api.data.CharacterPOJO;
import com.example.lifcaremarvel.api.json.CharacterDataWrapper;
import com.example.lifcaremarvel.api.util.DataParser;
import com.example.lifcaremarvel.ui.AbsPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * This class <i>MainPresenter</i> is presenter layer.the presenter is the middle-man between model and view
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public class MainPresenter extends AbsPresenter<MainContract.View> implements MainContract.Actions {

    private final MarvelApi mMarvelApi;
    private final List<CharacterPOJO> mEntries;
    private String mAttribution;
    private boolean mHasMore;

    public MainPresenter(@NonNull MarvelApi marvelApi) {
        mMarvelApi = marvelApi;
        mEntries = new ArrayList<>();
    }

    @Override
    public void initScreen() {
        if (mEntries.isEmpty()) {
            loadCharacters(0);
        } else {
            mView.showResult(mEntries);
            mView.showAttribution(mAttribution);
            mView.stopProgress(mHasMore);
        }
    }

    @Override
    public void loadCharacters(int offset) {
        mView.showProgress();
        mMarvelApi.listCharacters(offset, new MarvelCallback<CharacterDataWrapper>() {

            @Override
            public void onResult(CharacterDataWrapper data) {
                MarvelResult<CharacterPOJO> result = DataParser.parse(data);
                mEntries.addAll(result.getEntries());
                mAttribution = result.getAttribution();
                int offset = result.getOffset();
                int count = result.getCount();
                int total = result.getTotal();
                mHasMore = total > offset + count;
                if (!isViewAttached()) {
                    return;
                }
                mView.showResult(mEntries);
                mView.showAttribution(mAttribution);
                mView.stopProgress(mHasMore);
            }

            @Override
            public void onError(Throwable e) {
                if (!isViewAttached()) {
                    return;
                }
                mView.showError(e);
                mView.stopProgress(mHasMore);
            }
        });
    }

    @Override
    public void characterClick(@NonNull View heroView, @NonNull CharacterPOJO character) {
        mView.openCharacter(heroView, character);
    }

    @Override
    public void refresh() {
        mEntries.clear();
        loadCharacters(0);
    }

}
