package com.example.lifcaremarvel.api.data;

import java.io.Serializable;
import java.util.List;

/**
 * This class <i>CharacterPOJO</i> is data POJO class for character data
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public class CharacterPOJO implements Serializable {

    private long mId;
    private String mName;
    private String mDescription;
    private String mThumbnail;
    private String mImage;
    private List<SectionPOJO> mComics;
    private List<SectionPOJO> mSeries;
    private List<SectionPOJO> mStories;
    private List<SectionPOJO> mEvents;
    private String mDetail;
    private String mWiki;
    private String mComicLink;

    public CharacterPOJO() {
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String thumbnail) {
        mThumbnail = thumbnail;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public List<SectionPOJO> getComics() {
        return mComics;
    }

    public void setComics(List<SectionPOJO> comics) {
        mComics = comics;
    }

    public List<SectionPOJO> getSeries() {
        return mSeries;
    }

    public void setSeries(List<SectionPOJO> series) {
        mSeries = series;
    }

    public List<SectionPOJO> getStories() {
        return mStories;
    }

    public void setStories(List<SectionPOJO> stories) {
        mStories = stories;
    }

    public List<SectionPOJO> getEvents() {
        return mEvents;
    }

    public void setEvents(List<SectionPOJO> events) {
        mEvents = events;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    public String getWiki() {
        return mWiki;
    }

    public void setWiki(String wiki) {
        mWiki = wiki;
    }

    public String getComicLink() {
        return mComicLink;
    }

    public void setComicLink(String comicLink) {
        mComicLink = comicLink;
    }

    @Override
    public String toString() {
        return "Character{" + mId + ", '" + mName + "'}";
    }
}
