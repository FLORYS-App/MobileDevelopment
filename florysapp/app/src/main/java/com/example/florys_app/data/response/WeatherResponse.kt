package com.example.florys_app.data.response

data class WeatherResponse(
    val city: String,
    val weather: String,
    val temperature: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Int,
    val humidity: Int,
    val wind_speed: Float,
    val wind_direction: Int
)