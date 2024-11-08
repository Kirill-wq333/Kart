package com.example.fair2.ui.theme

data class WeatherResponse(
    val location: WeatherLocationResponse,
    val current: CurrentWeatherResponse
)
data class WeatherLocationResponse(
    val localtime: String
)
data class CurrentWeatherResponse(
    val temp_c : Number,
    val condition : WeatherConditionResponse,
    val wind_kph : Number,
    val pressure_mb: Number,
    val humidity: Int
)
data class WeatherConditionResponse(
    val text: String
)