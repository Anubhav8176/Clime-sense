package com.anucodes.climesense.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anucodes.climesense.model.CurrentWeather
import com.anucodes.climesense.model.weatherastronomy
import com.anucodes.climesense.requests.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val API_KEY = "65370aac83d8458981b180030243108"

    private val _astronomy = MutableStateFlow<weatherastronomy?>(null)
    val astronomy: StateFlow<weatherastronomy?> = _astronomy.asStateFlow()

    private val _currentWeather = MutableStateFlow<CurrentWeather?>(null)
    val currentWeather: StateFlow<CurrentWeather?> = _currentWeather.asStateFlow()

    fun getWeather(place: String){
        viewModelScope.launch {
            try {
                val currentWeather = RetrofitInstance.api.getCurrentWeather(API_KEY, place, "yes", "4")
                _currentWeather.value = currentWeather
            }catch (e: Exception){
                Log.i("Error encountered: ", e.toString())
            }
        }
    }


    fun getAstronomy(
        place: String,
        date: String
    ){
        viewModelScope.launch {
            try {
                val astro = RetrofitInstance.api.getAstro(API_KEY, place, date)
                _astronomy.value = astro
            }catch (e: Exception){
                Log.i("Error encountered: ", e.toString())
            }
        }
    }
}