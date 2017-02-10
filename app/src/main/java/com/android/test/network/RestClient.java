package com.android.test.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Manish on 9/2/17.
 */

public class RestClient {

    //mainurl
    private static final String BASE_URL = "http://192.168.1.115:8080/";
    IApiInterface retrofitApi;
    private Context mContext;
    private Retrofit retrofit = null;

    public RestClient() {

    }

    public RestClient(String base) {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.writeTimeout(30, TimeUnit.SECONDS);
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            httpClient.addInterceptor(logging);
            retrofit = new Retrofit.Builder()
                    .baseUrl(base)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();

            retrofitApi = retrofit.create(IApiInterface.class);
        }

    }



    public IApiInterface getRetrofitApi() {
        return retrofitApi;
    }

}
