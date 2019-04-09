package com.example.lifcaremarvel.api.auth;


import com.example.lifcaremarvel.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class <i>AuthenticatorInterceptor</i> is An OkHttp interceptor which logs HTTP request and response data.
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public class AuthenticatorInterceptor implements Interceptor {

    private static final String QUERY_NAME_TIMESTAMP = "ts";
    private static final String QUERY_NAME_APIKEY = "apikey";
    private static final String QUERY_NAME_HASH = "hash";

    @Override
    public Response intercept(Chain chain) throws IOException {

        String ts = String.valueOf(System.currentTimeMillis());
        // encrypted data received
        String hash = HashHelper.generate(ts + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY);

        Request currentRequest = chain.request();

        HttpUrl url = currentRequest.url().newBuilder()
                .addQueryParameter(QUERY_NAME_TIMESTAMP, ts)
                .addQueryParameter(QUERY_NAME_APIKEY, BuildConfig.MARVEL_PUBLIC_KEY)
                .addQueryParameter(QUERY_NAME_HASH, hash).build();

        Request newRequest = currentRequest.newBuilder().url(url).build();

        return chain.proceed(newRequest);
    }
}
