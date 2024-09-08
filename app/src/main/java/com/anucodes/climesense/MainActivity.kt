package com.anucodes.climesense

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anucodes.climesense.ui.theme.ClimeSenseTheme
import com.anucodes.climesense.uicomponents.HomeScreen
import com.anucodes.climesense.uicomponents.OnBoardingScreen
import com.anucodes.climesense.uicomponents.ShowDetails
import com.anucodes.climesense.viewmodel.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var fusedLocation: FusedLocationProviderClient
    private var currLatitude: Double = 0.0
    private var currLongitude: Double = 0.0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocation = LocationServices.getFusedLocationProviderClient(this)
        val weatherViewModel = WeatherViewModel()


        enableEdgeToEdge()
        setContent {
            fusedLocation.lastLocation.addOnSuccessListener { location: Location?->
                if (location != null) {
                    currLatitude = location.latitude
                    currLongitude = location.longitude
                    Log.i("Location found: ", "(${currLatitude}, ${currLongitude}")
                }else{
                    currLatitude = 28.6358
                    currLongitude = 77.2245
                    Log.i("Location found: ", "(${currLatitude}, ${currLongitude}")
                }
            }

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
            }


            var place: String = "Lucknow"
            val address: List<Address>

            val geocoder: Geocoder = Geocoder(LocalContext.current, Locale.getDefault())
            try{
                address = geocoder.getFromLocation(currLatitude, currLongitude, 1)!!
                if (address.size != 0){
//                        place = address.get(0).locality
                }else{
                    place = "New Delhi"
                }

            }catch (e: IOException){
                Log.i("Error here:", "Failed to load location!!")
            }



            val navController = rememberNavController()
            NavHost(navController = navController,
                startDestination="on_boarding_screen"
            ){
                composable("on_boarding_screen") {
                    OnBoardingScreen(navController)
                }
                composable("home_screen") {
                    HomeScreen(navController, weatherViewModel, place)
                }
                composable("show_details") {
                    ShowDetails(weatherViewModel, place)
                }
            }

        }
    }
}
