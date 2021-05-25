package com.sachin.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherInfo {

    @SerializedName("main")
    private MainData mainData;

    public MainData getMainData() {
        return mainData;
    }

}
