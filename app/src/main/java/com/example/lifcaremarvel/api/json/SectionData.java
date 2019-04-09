package com.example.lifcaremarvel.api.json;

public class SectionData {

    public long id;
    public String title;
    public Image thumbnail;

    public String getThumbnail() {
        if (thumbnail == null) {
            return null;
        }
        return Image.getUrl(thumbnail.getPath(), Image.SIZE_PORTRAIT_XLARGE, thumbnail.getExtension());
    }

    public String getImage() {
        if (thumbnail == null) {
            return null;
        }
        return Image.getUrl(thumbnail.getPath(), Image.SIZE_DETAIL, thumbnail.getExtension());
    }

}
