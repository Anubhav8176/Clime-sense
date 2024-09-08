package com.anucodes.climesense.uicomponents

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.BrightnessLow
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anucodes.climesense.R
import com.anucodes.climesense.ui.theme.mainScreenFont
import com.anucodes.climesense.viewmodel.WeatherViewModel
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowDetails(
    weatherViewModel: WeatherViewModel,
    place: String
){

    val currentWeather by weatherViewModel.currentWeather.collectAsState()
    val astronomy by weatherViewModel.astronomy.collectAsState()
    val date = LocalDate.now().toString()

    LaunchedEffect(key1 = Unit) {
        weatherViewModel.getWeather(place)
        weatherViewModel.getAstronomy(place,date)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                0.1f to Color(0xFF1A2243),
                0.3f to Color(0xFF252C58),
                0.6f to Color(0xFF4D3D9B),
                0.8f to Color(0xFF7651AD),
                1.0f to Color(0xFF8E4BAD)
            )
        ),
        contentAlignment = Alignment.Center
    ){

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            currentWeather?.location?.let {
                Text(
                    text = it.name,
                    fontFamily = mainScreenFont,
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            currentWeather?.location?.let {
                Text(
                    text = "${it.region}, ${it.country}",
                    fontFamily = mainScreenFont,
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Row {
                Text(
                    text = "Temp:${currentWeather?.current?.temp_c} °C",
                    fontSize = 20.sp,
                    fontFamily = mainScreenFont,
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(25.dp))
                Text(
                    text = "Humidity: ${currentWeather?.current?.humidity}g/m³",
                    fontSize = 20.sp,
                    fontFamily = mainScreenFont,
                    color = Color.White
                )
            }

            if (currentWeather?.current?.is_day == 0){
                Image(
                    painter = painterResource(id = R.drawable.weather),
                    contentDescription = "Middle Icon!",
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "Night",
                    fontSize = 20.sp,
                    fontFamily = mainScreenFont,
                    color = Color.White
                )
            }else{
                Image(
                    painter = painterResource(id = R.drawable.weather),
                    contentDescription = "Middle Icon!",
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "Day",
                    fontSize = 20.sp,
                    fontFamily = mainScreenFont,
                    color = Color.White
                )
            }


            Row {
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .background(
                            brush = Brush.linearGradient(
                                0.2f to Color(0xFF4D3D9B),
                                0.8f to Color(0xFF7651AD),
                                1.0f to Color(0xFF8E4BAD)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .border(2.dp, Color.White, shape = RoundedCornerShape(16.dp))

                ){
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Row (
                            modifier = Modifier.padding(10.dp, 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Absolute.Center
                        ){
                            Icon(
                                imageVector = Icons.Filled.BrightnessLow,
                                contentDescription ="Sun",
                                tint = Color.White,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = "Sunrise",
                                fontSize = 15.sp,
                                fontFamily = mainScreenFont,
                                color = Color.White
                            )
                        }
                        astronomy?.astronomy?.astro?.let {
                            Text(
                                text = it.sunrise,
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = mainScreenFont,
                                modifier = Modifier.padding(20.dp)
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .background(
                            brush = Brush.linearGradient(
                                0.2f to Color(0xFF4D3D9B),
                                0.8f to Color(0xFF7651AD),
                                1.0f to Color(0xFF8E4BAD)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .border(2.dp, Color.White, shape = RoundedCornerShape(16.dp))

                ){
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Row (
                            modifier = Modifier.padding(10.dp, 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Absolute.Center
                        ){
                            Icon(
                                imageVector = Icons.Filled.Brightness2,
                                contentDescription ="Sun",
                                tint = Color.White,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = "Sunset",
                                fontSize = 15.sp,
                                fontFamily = mainScreenFont,
                                color = Color.White
                            )
                        }
                        astronomy?.astronomy?.astro?.sunset?.let {
                            Text(
                                text = it,
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = mainScreenFont,
                                modifier = Modifier.padding(20.dp)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(
                        brush = Brush.linearGradient(
                            0.2f to Color(0xFF4D3D9B),
                            0.8f to Color(0xFF7651AD),
                            1.0f to Color(0xFF8E4BAD)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )

            ){
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row (
                        modifier = Modifier.padding(10.dp, 5.dp)
                    ){
                        Icon(
                            modifier = Modifier
                                .size(18.dp),
                            imageVector = Icons.Outlined.Air,
                            contentDescription = "Air Quality!!",
                            tint = Color.White,
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            text = "Air Quality",
                            fontSize = 15.sp,
                            color = Color.White,
                            fontFamily = mainScreenFont,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text =" CO: ${currentWeather?.current?.air_quality?.co} O₃: ${currentWeather?.current?.air_quality?.o3}",
                        fontFamily = mainScreenFont,
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(10.dp, 5.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(15.dp)
                        .background(
                            brush = Brush.linearGradient(
                                0.2f to Color(0xFF4D3D9B),
                                0.8f to Color(0xFF7651AD),
                                1.0f to Color(0xFF8E4BAD)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .border(2.dp, Color.White, shape = RoundedCornerShape(16.dp))

                ){
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Row (
                            modifier = Modifier.padding(10.dp, 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Absolute.Center
                        ){
                            Icon(
                                imageVector = Icons.Filled.BrightnessLow,
                                contentDescription ="Sun",
                                tint = Color.White,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = "Wind dir.",
                                fontSize = 15.sp,
                                fontFamily = mainScreenFont,
                                color = Color.White
                            )
                        }
                        Text(
                            text = "${currentWeather?.current?.wind_dir}",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontFamily = mainScreenFont,
                            modifier = Modifier
                                .padding(20.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .background(
                            brush = Brush.linearGradient(
                                0.2f to Color(0xFF4D3D9B),
                                0.8f to Color(0xFF7651AD),
                                1.0f to Color(0xFF8E4BAD)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .border(2.dp, Color.White, shape = RoundedCornerShape(16.dp))

                ){
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Row (
                            modifier = Modifier.padding(5.dp, 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Absolute.Center
                        ){
                            Icon(
                                imageVector = Icons.Filled.Brightness2,
                                contentDescription ="Sun",
                                tint = Color.White,
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = "Wind Speed",
                                fontSize = 15.sp,
                                fontFamily = mainScreenFont,
                                color = Color.White
                            )
                        }
                        Text(
                            text = "${currentWeather?.current?.wind_kph} km/h",
                            fontSize = 15.sp,
                            color = Color.White,
                            fontFamily = mainScreenFont,
                            modifier = Modifier
                                .padding(20.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }


    }
}