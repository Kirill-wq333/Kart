package com.example.fair2.ui.theme

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current.json")
    suspend fun getWeatherData(
        @Query("key") key: String = "",
        @Query("q") city: String,
        @Query("aqi") aqi: String = "no"
    ):WeatherResponse
}