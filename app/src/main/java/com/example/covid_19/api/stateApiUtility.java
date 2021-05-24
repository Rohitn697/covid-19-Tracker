package com.example.covid_19.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class stateApiUtility {
    private static Retrofit retrofit = null;
    public static stateApiInterface getStateApiInterface(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(stateApiInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            stateApiInterface requestInterface = retrofit.create(stateApiInterface.class);
        }
        return retrofit.create(stateApiInterface.class);
    }
}
