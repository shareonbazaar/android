package eu.shareonbazaar.dev.bazaar.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTemplate {

    private static String baseUrl = "http://dev.shareonbazaar.eu/";
    //private String baseUrl = "http://bazaar-dev.eu-central-1.elasticbeanstalk.com/"''

    public static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}