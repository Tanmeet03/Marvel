package com.example.lifcaremarvel.api.util;

import com.example.lifcaremarvel.api.MarvelResult;
import com.example.lifcaremarvel.api.data.CharacterPOJO;
import com.example.lifcaremarvel.api.data.SectionPOJO;
import com.example.lifcaremarvel.api.json.CharacterData;
import com.example.lifcaremarvel.api.json.CharacterDataContainer;
import com.example.lifcaremarvel.api.json.CharacterDataWrapper;
import com.example.lifcaremarvel.api.json.SectionData;
import com.example.lifcaremarvel.api.json.SectionDataContainer;
import com.example.lifcaremarvel.api.json.SectionDataWrapper;
import com.example.lifcaremarvel.api.json.SectionSummary;
import com.example.lifcaremarvel.api.json.Url;

import java.util.ArrayList;
import java.util.List;

/**
 * This class <i>DataParser</i> is used to modify and access response from server
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public class DataParser {

    public static MarvelResult<CharacterPOJO> parse(CharacterDataWrapper dataWrapper) {
        MarvelResult<CharacterPOJO> result = new MarvelResult<>();
        CharacterDataContainer dataContainer = dataWrapper.getData();
        if (dataContainer != null) {
            result.setOffset(dataContainer.getOffset());
            result.setTotal(dataContainer.getTotal());
            CharacterData[] results = dataContainer.getResults();
            if (results != null) {
                List<CharacterPOJO> characterList = new ArrayList<>(results.length);
                for (CharacterData characterData : results) {
                    CharacterPOJO character = new CharacterPOJO();
                    character.setId(characterData.getId());
                    character.setName(characterData.getName());
                    character.setDescription(characterData.getDescription());
                    character.setThumbnail(characterData.getThumbnail());
                    character.setImage(characterData.getImage());
                    for (Url url : characterData.getUrls()) {
                        if (Url.TYPE_DETAIL.equals(url.getType())) {
                            character.setDetail(url.getUrl());
                        } else if (Url.TYPE_WIKI.equals(url.getType())) {
                            character.setWiki(url.getUrl());
                        } else if (Url.TYPE_COMICLINK.equals(url.getType())) {
                            character.setComicLink(url.getUrl());
                        }
                    }
                    character.setComics(parseSection(characterData.getComics().getItems()));
                    character.setSeries(parseSection(characterData.getSeries().getItems()));
                    character.setStories(parseSection(characterData.getStories().getItems()));
                    character.setEvents(parseSection(characterData.getEvents().getItems()));
                    characterList.add(character);
                }
                result.setEntries(characterList);
            }
        }
        result.setAttribution(dataWrapper.getAttributionText());
        return result;
    }

    private static List<SectionPOJO> parseSection(SectionSummary[] items) {
        List<SectionPOJO> list = new ArrayList<>();
        for (SectionSummary summary : items) {
            SectionPOJO section = new SectionPOJO();
            section.setId(summary.getId());
            section.setTitle(summary.getName());
            list.add(section);
        }
        return list;
    }

    public static MarvelResult<SectionPOJO> parse(SectionDataWrapper dataWrapper) {
        MarvelResult<SectionPOJO> result = new MarvelResult<>();
        SectionDataContainer dataContainer = dataWrapper.getData();
        if (dataContainer != null) {
            result.setOffset(dataContainer.getOffset());
            result.setTotal(dataContainer.getTotal());
            SectionData[] results = dataContainer.getResults();
            if (results != null) {
                List<SectionPOJO> list = new ArrayList<>(results.length);
                for (SectionData sectionData : results) {
                    SectionPOJO sectionPOJO = new SectionPOJO();
                    sectionPOJO.setId(sectionData.id);
                    sectionPOJO.setTitle(sectionData.title);
                    sectionPOJO.setThumbnail(sectionData.getThumbnail());
                    sectionPOJO.setImage(sectionData.getImage());
                    list.add(sectionPOJO);
                }
                result.setEntries(list);
            }
        }
        result.setAttribution(dataWrapper.getAttributionText());
        return result;
    }
}
