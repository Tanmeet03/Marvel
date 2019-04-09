package com.example.lifcaremarvel.api.data;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
/**
 * This class <i>SectionPOJO</i> is data POJO class for type of section data
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public class SectionPOJO implements Serializable {

    public static final int TYPE_COMIC = 0;
    public static final int TYPE_SERIES = 1;
    public static final int TYPE_STORY = 2;
    public static final int TYPE_EVENT = 3;

    @IntDef({TYPE_COMIC, TYPE_SERIES, TYPE_STORY, TYPE_EVENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    private long mId;
    private String mTitle;
    private String mThumbnail;
    private String mImage;

    public SectionPOJO() {
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
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

    @Override
    public String toString() {
        return "Section{" + mId + ", '" + mTitle + "'}";
    }
}
