package com.anucodes.climesense.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anucodes.climesense.R
import com.anucodes.climesense.ui.theme.getStarted
import com.anucodes.climesense.ui.theme.mainScreenFont
import com.anucodes.climesense.viewmodel.WeatherViewModel

@Composable
fun OnBoardingScreen(
    navController: NavController
){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF1A2243),
                    Color(0xFF252C58),
                    Color(0xFF4D3D9B),
                    Color(0xFF7651AD),
                    Color(0xFF8E4BAD)
                )
            )
        ),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.weather),
                contentDescription = "Main app icon!",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.size(25.dp))

            Text(text = "ClimeSense",
                fontSize = 50.sp,
                fontFamily = mainScreenFont,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp),
                color = Color.White
            )

            Text(text = "Weather Report",
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontFamily = mainScreenFont,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
                    .padding(15.dp),
                onClick = {
                    navController.navigate("home_screen")
                },
                colors = ButtonColors(
                    containerColor = Color(0xFFDDB130),
                    contentColor = Color(0xFF362A84),
                    disabledContentColor = Color(0xFF362A84),
                    disabledContainerColor = Color(0xFFDDB130)
                )
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 20.sp,
                    fontFamily = getStarted,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}