package com.example.lifcaremarvel.api.json;

public class CharacterDataContainer {
    private int offset;
    private int total;
    private CharacterData[] results;

    public int getOffset() {
        return offset;
    }

    public int getTotal() {
        return total;
    }

    public CharacterData[] getResults() {
        return results;
    }
}
