package com.anucodes.climesense.model

data class Forecast(
    val forecastday: List<Forecastday>
)

data class Forecastday(
    val date: String,
    val day: Day
)

data class Day(
    val avgtemp_c: Double,
    val condition: Condition,
    val maxtemp_c: Double,
    val mintemp_c: Double
)