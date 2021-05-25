package com.sachin.weather;

import com.google.gson.annotations.SerializedName;

public class MainData {

    @SerializedName("temp")
    private Double temperature;

    @SerializedName("pressure")
    private String pressure;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("feels_like")
    private Double feelsLike;

    public Double getFeelsLike() {
        return feelsLike;
    }

    public int getHumidity() {
        return humidity;
    }

    public Double getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }


}
