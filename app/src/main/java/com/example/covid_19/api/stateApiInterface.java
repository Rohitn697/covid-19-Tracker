package com.example.covid_19.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface stateApiInterface {

    static final String BASE_URL = "https://covid19.mathdro.id/api/countries/india/";
    @GET("confirmed")
    Call<List<StatesData>> getStatesData();
}
