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
   private String deaths28Days;
   private String deaths;
   private String confirmed;
   private String cases28Days;

    public StatesData(String lastUpdate, String provinceState, String active, String deaths28Days, String deaths, String confirmed, String cases28Days) {
        this.lastUpdate = lastUpdate;
        this.provinceState = provinceState;
        this.active = active;
        this.deaths28Days = deaths28Days;
        this.deaths = deaths;
        this.confirmed = confirmed;
        this.cases28Days = cases28Days;
    }

    public String getCases28Days() {
        return cases28Days;
    }

    public void setCases28Days(String cases28Days) {
        this.cases28Days = cases28Days;
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

    public String getDeaths28Days() {
        return deaths28Days;
    }

    public void setDeaths28Days(String deaths28Days) {
        this.deaths28Days = deaths28Days;
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