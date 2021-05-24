package com.example.covid_19.api;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;


public class StatesData {
   private String lastUpdate;
   private String provinceState;
   private String active;
   private String recovered;
   private String deaths;
   private String confirmed;


    public StatesData(String lastUpdate, String provinceState, String active, String recovered, String deaths, String confirmed) {
        this.lastUpdate = lastUpdate;
        this.provinceState = provinceState;
        this.active = active;
        this.recovered = recovered;
        this.deaths = deaths;
        this.confirmed = confirmed;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getProvinceState() {
        return provinceState;
    }

    public void setProvinceState(String provinceState) {
        this.provinceState = provinceState;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }
}