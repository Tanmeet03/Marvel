package com.example.lifcaremarvel.api;

import com.example.lifcaremarvel.api.json.CharacterDataWrapper;
import com.example.lifcaremarvel.api.json.SectionDataWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface MarvelService {

    @GET("characters")
    Call<CharacterDataWrapper> listCharacters(
            @Query("nameStartsWith") String query,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET("characters/{characterId}/comics")
    Call<SectionDataWrapper> listComics(
            @Path("characterId") long characterId,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET("characters/{characterId}/series")
    Call<SectionDataWrapper> listSeries(
            @Path("characterId") long characterId,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET("characters/{characterId}/stories")
    Call<SectionDataWrapper> listStories(
            @Path("characterId") long characterId,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET("characters/{characterId}/events")
    Call<SectionDataWrapper> listEvents(
            @Path("characterId") long characterId,
            @Query("offset") int offset,
            @Query("limit") int limit);
}
