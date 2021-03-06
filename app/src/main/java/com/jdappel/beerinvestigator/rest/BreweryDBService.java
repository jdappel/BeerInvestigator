package com.jdappel.beerinvestigator.rest;

import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Package-private implementation class for constructing a REST client with {@code Retrofit} and
 * {@code OkHttp}
 */
class BreweryDBService {

    static BreweryDBApi createBreweryDBService(final String apiToken) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("https://api.brewerydb.com/v2/");

        if (!apiToken.isEmpty()) {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("key", apiToken).build();
                Request newReq = request.newBuilder().url(url).build();
                return chain.proceed(newReq);
            }).build();
            builder.client(client);
        }
        return builder.build().create(BreweryDBApi.class);
    }
}
