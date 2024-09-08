package com.anucodes.climesense.requests

import com.anucodes.climesense.model.CurrentWeather
import com.anucodes.climesense.model.weatherastronomy
import retrofit2.http.GET
import retrofit2.http.Query
import java.sql.Date

interface ApiInterface {

    @GET("forecast.json?")
    suspend fun getCurrentWeather(
        @Query("key")key: String,
        @Query("q")place: String,
        @Query("aqi")ind: String,
        @Query("days")days: String
    ): CurrentWeather

    @GET("astronomy.json?")
    suspend fun getAstro(
        @Query("key")key: String,
        @Query("q")place: String,
        @Query("dt")date: String
    ): weatherastronomy
}