package com.example.fair2.ui.theme

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("current.json")
    suspend fun getWeatherData(
        @Query("key") key: String = "9758adcdd9f4497a868130048240811",
        @Query("q") city: String,
        @Query("lang") language: String = "ru"
    ):WeatherResponse
}