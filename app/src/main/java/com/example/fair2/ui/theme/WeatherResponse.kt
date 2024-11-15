package com.example.fair2.ui.theme

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val location: WeatherLocationResponse,
    val current: CurrentWeatherResponse
)
data class WeatherLocationResponse(
    @SerializedName("localtime")
    val localtime: String
)
data class CurrentWeatherResponse(
    @SerializedName("temp_c") val temp_c : Number,
    val condition : WeatherConditionResponse,
    @SerializedName("wind_kph")val wind_kph : Number,
    @SerializedName("pressure_mb")val pressure_mb: Number,
    val humidity: Int
)
data class WeatherConditionResponse(
    val text: String,
    val code: Int
)