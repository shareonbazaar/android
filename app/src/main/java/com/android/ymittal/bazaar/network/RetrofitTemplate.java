package com.android.ymittal.bazaar.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTemplate {
    public static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://bazaar-dev.eu-central-1.elasticbeanstalk.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}