package com.example.lifcaremarvel.api;

import com.example.lifcaremarvel.BuildConfig;
import com.example.lifcaremarvel.api.auth.AuthenticatorInterceptor;
import com.example.lifcaremarvel.api.json.CharacterDataWrapper;
import com.example.lifcaremarvel.api.json.SectionDataWrapper;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class <i>MarvelApi</i> is used to fetch response from server
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public class MarvelApi {
    //pagination set to 20. list of 20 will be fected in one request
    private static final int MAX_FETCH_LIMIT = 20;

    private static final String BASE_URL = "http://gateway.marvel.com/v1/public/";
    private static MarvelApi sMarvelApi;
    private final MarvelService mService;

    private MarvelApi() {
        AuthenticatorInterceptor authenticator = new AuthenticatorInterceptor();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.BASIC);
        //init okhtttp builder obj
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(authenticator)
                .addInterceptor(logging)
                .build();
        //initialize retrofit client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(MarvelService.class);
    }

    public static MarvelApi getInstance() {
        if (sMarvelApi == null) {
            sMarvelApi = new MarvelApi();
        }
        return sMarvelApi;
    }

    /**
     * get list of character of marvel
     */
    public void listCharacters(int offset, MarvelCallback<CharacterDataWrapper> callback) {
        mService.listCharacters(null, offset, MAX_FETCH_LIMIT).enqueue(callback);
    }

    /**
     * get list of comics related to that character of marvel
     */
    public void listComics(long characterId, int offset, MarvelCallback<SectionDataWrapper> callback) {
        mService.listComics(characterId, offset, MAX_FETCH_LIMIT).enqueue(callback);
    }

    /**
     * get list of series related to that character of marvel
     */
    public void listSeries(long characterId, int offset, MarvelCallback<SectionDataWrapper> callback) {
        mService.listSeries(characterId, offset, MAX_FETCH_LIMIT).enqueue(callback);
    }

    /**
     * get list of stories related to that character of marvel
     */
    public void listStories(long characterId, int offset, MarvelCallback<SectionDataWrapper> callback) {
        mService.listStories(characterId, offset, MAX_FETCH_LIMIT).enqueue(callback);
    }

    /**
     * get list of events related to that character of marvel
     */
    public void listEvents(long characterId, int offset, MarvelCallback<SectionDataWrapper> callback) {
        mService.listEvents(characterId, offset, MAX_FETCH_LIMIT).enqueue(callback);
    }
}
