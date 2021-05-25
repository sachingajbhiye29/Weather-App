package com.sachin.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {
                                                                // |
                                                                // |
                                                                // V
    // weather is the endpoint of the url --> openweather.com/2/weather

    @GET("weather")
    Call<WeatherInfo> getWeatherData(@Query("q") String city, @Query("appid") String apiKey);
}
