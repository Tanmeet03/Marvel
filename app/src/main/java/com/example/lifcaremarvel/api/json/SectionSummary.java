package com.example.lifcaremarvel.api.json;

public class SectionSummary {

    private String resourceURI;

    private String name;

    public String getName() {
        return name;
    }

    public long getId() {
        String id = getLastPathSegment(resourceURI);
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String getLastPathSegment(String uri) {
        if (uri == null) {
            return "";
        }
        int indexOf = uri.lastIndexOf("/");
        return uri.substring(indexOf + 1);
    }

}
