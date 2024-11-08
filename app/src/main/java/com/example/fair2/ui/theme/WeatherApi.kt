package com.example.fair2.ui.theme

import androidx.contentpager.content.Query
import retrofit2.http.GET


interface WeatherApi {
    @GET("current.json")
    suspend fun getWeatherData(
        @retrofit2.http.Query("key") key: String = "9758adcdd9f4497a868130048240811",
        @retrofit2.http.Query("q") city: String,
        @retrofit2.http.Query("aqi") aqi: String = "no"
    ):WeatherResponse
}