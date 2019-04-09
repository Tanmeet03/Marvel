package com.example.lifcaremarvel.ui.character;

import android.view.View;

import com.example.lifcaremarvel.api.MarvelApi;
import com.example.lifcaremarvel.api.MarvelCallback;
import com.example.lifcaremarvel.api.MarvelResult;
import com.example.lifcaremarvel.api.data.CharacterPOJO;
import com.example.lifcaremarvel.api.data.SectionPOJO;
import com.example.lifcaremarvel.api.json.SectionDataWrapper;
import com.example.lifcaremarvel.api.util.DataParser;
import com.example.lifcaremarvel.ui.AbsPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class CharacterPresenter extends AbsPresenter<CharacterContract.View> implements CharacterContract.Actions {

    private final MarvelApi mMarvelApi;
    private final CharacterPOJO mCharacter;
    private final List<SectionPOJO> mComicList;
    private final List<SectionPOJO> mSeriesList;
    private final List<SectionPOJO> mStoryList;
    private final List<SectionPOJO> mEventList;
    private String mAttribution;

    public CharacterPresenter(@NonNull CharacterPOJO character) {
        mMarvelApi = MarvelApi.getInstance();
        mCharacter = character;
        mComicList = new ArrayList<>();
        mSeriesList = new ArrayList<>();
        mStoryList = new ArrayList<>();
        mEventList = new ArrayList<>();
    }

    @Override
    public void attachView(@NonNull CharacterContract.View view) {
        super.attachView(view);
        mView.showAttribution(mAttribution);
        mView.showCharacter(mCharacter);
        if (mComicList.isEmpty()) {
            mView.showComics(mCharacter.getComics());
            loadComics(mCharacter.getId(), 0);
        } else {
            mView.showComics(mComicList);
        }
        if (mSeriesList.isEmpty()) {
            mView.showSeries(mCharacter.getSeries());
            loadSeries(mCharacter.getId(), 0);
        } else {
            mView.showSeries(mSeriesList);
        }
        if (mStoryList.isEmpty()) {
            mView.showStories(mCharacter.getStories());
            loadStories(mCharacter.getId(), 0);
        } else {
            mView.showStories(mStoryList);
        }
        if (mEventList.isEmpty()) {
            mView.showEvents(mCharacter.getEvents());
            loadEvents(mCharacter.getId(), 0);
        } else {
            mView.showEvents(mEventList);
        }
    }

    @Override
    public void loadComics(long characterId, int offset) {
        mMarvelApi.listComics(characterId, offset, new MarvelCallback<SectionDataWrapper>() {

            @Override
            public void onResult(SectionDataWrapper data) {
                MarvelResult<SectionPOJO> result = DataParser.parse(data);
                mComicList.addAll(result.getEntries());
                mAttribution = result.getAttribution();
                if (!isViewAttached()) {
                    return;
                }
                mView.showComics(mComicList);
                mView.showAttribution(mAttribution);
            }

            @Override
            public void onError(Throwable e) {
                if (!isViewAttached()) {
                    return;
                }
                mView.showError(e);
            }
        });
    }

    @Override
    public void loadSeries(long characterId, int offset) {
        mMarvelApi.listSeries(characterId, offset, new MarvelCallback<SectionDataWrapper>() {

            @Override
            public void onResult(SectionDataWrapper data) {
                MarvelResult<SectionPOJO> result = DataParser.parse(data);
                mSeriesList.addAll(result.getEntries());
                mAttribution = result.getAttribution();
                if (!isViewAttached()) {
                    return;
                }
                mView.showSeries(mSeriesList);
            }

            @Override
            public void onError(Throwable e) {
                if (!isViewAttached()) {
                    return;
                }
                mView.showError(e);
            }
        });
    }

    @Override
    public void loadStories(long characterId, int offset) {
        mMarvelApi.listStories(characterId, offset, new MarvelCallback<SectionDataWrapper>() {

            @Override
            public void onResult(SectionDataWrapper data) {
                MarvelResult<SectionPOJO> result = DataParser.parse(data);
                mStoryList.addAll(result.getEntries());
                mAttribution = result.getAttribution();
                if (!isViewAttached()) {
                    return;
                }
                mView.showStories(mStoryList);
            }

            @Override
            public void onError(Throwable e) {
                if (!isViewAttached()) {
                    return;
                }
                mView.showError(e);
            }
        });
    }

    @Override
    public void loadEvents(long characterId, int offset) {
        mMarvelApi.listEvents(characterId, offset, new MarvelCallback<SectionDataWrapper>() {

            @Override
            public void onResult(SectionDataWrapper data) {
                MarvelResult<SectionPOJO> result = DataParser.parse(data);
                mEventList.addAll(result.getEntries());
                mAttribution = result.getAttribution();
                if (!isViewAttached()) {
                    return;
                }
                mView.showEvents(mEventList);
            }

            @Override
            public void onError(Throwable e) {
                if (!isViewAttached()) {
                    return;
                }
                mView.showError(e);
            }
        });
    }

    @Override
    public void sectionClick(@SectionPOJO.Type int type, @NonNull View heroView, @NonNull List<SectionPOJO> entries, int position) {
        mView.openSection(type, heroView, mAttribution, entries, position);
    }

    @Override
    public void linkClick(@NonNull String url) {
        mView.openLink(url);
    }

}
