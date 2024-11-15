package com.example.fair2

import androidx.annotation.DrawableRes
import com.example.fair2.ui.theme.WeatherResponse

data class WeatherUiDataModel(
    val condition: String,
    @DrawableRes
    val conditionIconResId: Int,
    val temperature: Double,
    val localTime: String,
    val windSpeed: Double,
    val airPressure: Double,
    val humidity: Int
)

fun WeatherResponse.toUiDataModel(): WeatherUiDataModel {

    return WeatherUiDataModel(
        condition = this.current.condition.text,
        conditionIconResId = this.current.condition.code.getConditionImage(),
        airPressure = this.current.pressure_mb.toDouble(),
        windSpeed = this.current.wind_kph.toDouble(),
        localTime = this.location.localtime.takeLast(5),
        humidity = this.current.humidity.toInt(),
        temperature = this.current.temp_c.toDouble()
    )
}

private fun Int.getConditionImage(): Int {
    return when(this) {
        1000 -> R.drawable.clear_day
        1006 -> R.drawable.cloudy
        1009 -> R.drawable.overcast
        1135 -> R.drawable.fog
        1204 -> R.drawable.sleet
        1207 -> R.drawable.heavy_sleet
        1213 -> R.drawable.snow
        1225 -> R.drawable.heavy_snow
        1240 -> R.drawable.showers
        1264 -> R.drawable.heavy_showers
        1279 -> R.drawable.thunderstorm_snow
        else -> R.drawable.thunderstorm_showers
    }
}