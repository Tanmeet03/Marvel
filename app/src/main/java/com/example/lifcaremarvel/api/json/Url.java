package com.example.lifcaremarvel.api.json;

public class Url {
    public static final String TYPE_DETAIL = "detail";
    public static final String TYPE_WIKI = "wiki";
    public static final String TYPE_COMICLINK = "comiclink";

    private String type;
    private String url;

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

}
