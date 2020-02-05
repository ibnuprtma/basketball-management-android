package com.example.managementbasket.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitService {
    private static String URL = "http://b2d1b2f0.ngrok.io/";

    public static Retrofit setInit(){
        return new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiPlayer getInstance(){
        return setInit().create(ApiPlayer.class);
    }

    public static String getUrl(){
        return URL;
    }
}
