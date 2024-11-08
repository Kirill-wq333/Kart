package com.example.fair2.ui.theme

data class WeatherResponse(
    val location: WeatherLocationResponse,
    val current: CurrentWeatherResponse
)
data class WeatherLocationResponse(
    val f: Int
)
data class CurrentWeatherResponse(
val double: Double
)