package com.example.lifcaremarvel.api.json;

public class SectionDataContainer {
    private int offset;
    private int total;
    private SectionData[] results;

    public int getOffset() {
        return offset;
    }

    public int getTotal() {
        return total;
    }

    public SectionData[] getResults() {
        return results;
    }
}
