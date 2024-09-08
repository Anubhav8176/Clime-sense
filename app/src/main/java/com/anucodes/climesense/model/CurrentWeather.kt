package com.anucodes.climesense.model

data class CurrentWeather(
    val current: Current,
    val location: Location,
    val forecast: Forecast
)


data class Current(
    val air_quality: AirQuality,
    val cloud: Int,
    val condition: Condition,
    val humidity: Int,
    val is_day: Int,
    val temp_c: Double,
    val wind_dir: String,
    val wind_kph: Double
)


data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)

data class AirQuality(
    val co: Double,
    val no2: Double,
    val o3: Double,
    val pm10: Double,
    val pm2_5: Double,
    val so2: Double
)

data class Location(
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String,
    val region: String,
    val tz_id: String
)