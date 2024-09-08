package com.anucodes.climesense.uicomponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DensitySmall
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anucodes.climesense.R
import com.anucodes.climesense.ui.theme.mainScreenFont
import com.anucodes.climesense.viewmodel.WeatherViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    weatherViewModel: WeatherViewModel,
    place: String
){



    val currentWeather by weatherViewModel.currentWeather.collectAsState()

    LaunchedEffect(key1 = Unit) {
        weatherViewModel.getWeather(place)
    }

    var search by remember { mutableStateOf("") }
    var searching by remember { mutableStateOf(false) }

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

        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.weight(1f))

            DockedSearchBar(
                query = search,
                onQueryChange = {
                    search = it
                },
                onSearch = {

                },
                active = searching,
                onActiveChange = {

                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search")
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                if (search.isNotEmpty()){
                                    search = ""
                                }else{
                                    searching = false
                                }
                            },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Cancel Search!"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clickable {
                        searching = true
                    }
                    .size(45.dp)

            ) {

            }



            Image(
                painter = painterResource(id = R.drawable.weather),
                contentDescription = "Just a Image!",
                modifier = Modifier
                    .width(150.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "${currentWeather?.forecast?.forecastday?.get(0)?.day?.avgtemp_c}°C",
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                color = Color.White,
                fontFamily = mainScreenFont,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Temperature",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = mainScreenFont,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Row (
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ){
                Text(
                    text = "Max: ${currentWeather?.forecast?.forecastday?.get(0)?.day?.maxtemp_c}°C",
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.White,
                    fontFamily = mainScreenFont
                )
                Spacer(modifier = Modifier.size(25.dp))
                Text(
                    text = "Min: ${currentWeather?.forecast?.forecastday?.get(0)?.day?.mintemp_c}°C",
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.White,
                    fontFamily = mainScreenFont
                )
            }

            Image(
                painter = painterResource(id = R.drawable.snow),
                contentDescription = "Middle Icon!",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
            )
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(
                        brush = Brush.linearGradient(
                            0.3f to Color(0xFF7651AD),
                            0.6f to Color(0xFF4D3D9B),
                            1.0f to Color(0xFF1A2243)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
            ){
                Card (
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(20.dp, 10.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 30.dp,
                        draggedElevation = 50.dp
                    )
                ){
                    Row(
                        modifier = Modifier
                            .background(Color.Transparent),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Today",
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            color = Color.White,
                            fontFamily = mainScreenFont
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${currentWeather?.forecast?.forecastday?.get(0)?.date}",
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            color = Color.White,
                            fontFamily = mainScreenFont
                        )
                    }
                }
            }
            Canvas(modifier = Modifier.fillMaxWidth()) {
                drawLine(
                    color = Color.White,
                    start = Offset(0.0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                Color(0xFF1A2243),
                                Color(0xFF4D3D9B)
                            )
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
            ){
                Card (
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 30.dp,
                        draggedElevation = 50.dp
                    ),
                    modifier = Modifier.fillMaxWidth()
                ){
                    Row(
                        modifier = Modifier
                            .background(Color.Transparent),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(modifier = Modifier.weight(1f))
                        for (i in 0..2){
                            EachColumnWeather(
                                weatherViewModel = weatherViewModel,
                                index = i
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .background(
                        Color.Transparent
                    ),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.elevatedCardElevation(10.dp),
                onClick = {
                    navController.navigate("show_details")
                }
            ){
                Column (
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        imageVector = Icons.Default.DensitySmall,
                        contentDescription = "Show more",
                        modifier = Modifier
                            .size(20.dp),
                        tint = Color.White
                    )
                    Text(
                        text = "More Details",
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontFamily = mainScreenFont
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Composable
fun EachColumnWeather(weatherViewModel: WeatherViewModel, index: Int){

    val forecast by weatherViewModel.currentWeather.collectAsState()

    Column (
        modifier = Modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "${forecast?.forecast?.forecastday?.get(index)?.day?.avgtemp_c}°C",
            fontSize = 10.sp,
            fontFamily = mainScreenFont,
            color = Color.White
        )
        Image(
            painter = painterResource(id = R.drawable.weather),
            contentDescription = "Just a Image!",
            modifier = Modifier
                .height(65.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "${forecast?.forecast?.forecastday?.get(index)?.date}",
            fontSize = 10.sp,
            fontFamily = mainScreenFont,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}