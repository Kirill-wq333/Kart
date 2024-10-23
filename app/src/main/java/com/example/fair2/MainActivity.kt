package com.example.fair2

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.DeadObjectException
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fair2.ui.theme.Fair2Theme
import kotlinx.coroutines.delay
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Fair2Theme {
                WeatherContent(
                    localTime = "8:14",
                    windSpeed = 4.5,
                    airPressure = 759,
                    humidity = 42
                )
            }
        }
    }
}

@Preview
@Composable
fun WeatherContentPreview(){
    Surface(color = Color.White) {
      WeatherContent(
          localTime = "8:14",
          windSpeed = 4.5,
          airPressure = 759,
          humidity = 42
      )
    }
}


fun getCurrentTime(): String {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(Date())
}


@Composable
fun WeatherContent(
    localTime: String,
    windSpeed: Double,
    airPressure: Int,
    humidity: Int
){

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(24.dp, 22.dp)
                    .width(327.dp)
                    .height(40.dp)
                    .background(
                        color = Color.Black.copy(0.05f),
                        shape = RoundedCornerShape(12.dp)
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                    Text(
                        modifier = Modifier
                            .padding(10.dp, 9.dp),
                        text = "Москва",
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(listOf(Font(R.font.montserrat_medium)))
                    )
                Icon(
                    modifier = Modifier
                        .padding(10.dp, 8.dp)
                        .size(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.chevron_down),
                    contentDescription = null

                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 44.dp)
                .background(
                    color = Color.Black.copy(0.05f),
                    shape = RoundedCornerShape(12.dp)
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(15.dp, 6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DetailsTextBlock2(
                        title = "Время"
                    )
                    DetailsTextBlock(
                        title = "Ск. ветра",
                        subtitle = "$windSpeed М/С"
                    )
                    DetailsTextBlock(
                        title = "Давление",
                        subtitle = "$airPressure мм."
                    )
                    DetailsTextBlock(
                        title = "Влажность",
                        subtitle = "$humidity %"
                    )
                }
            }
        }
    ) {

    }

}
@Composable
fun DetailsTextBlock(
    title: String, // Заголовок
    subtitle: String // Подзаголовок
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black.copy(0.7f),
            fontFamily = FontFamily(listOf(Font(R.font.montserrat_semibold)))
        )
        Text(
            text = subtitle,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            fontFamily = FontFamily(listOf(Font(R.font.montserrat_medium)))
        )
    }
}

@Composable
fun DetailsTextBlock2(
    title: String
) {
    var currentTime by remember {
        mutableStateOf(getCurrentTime())
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Обновляем каждую секунду
            currentTime = getCurrentTime()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black.copy(0.7f),
            fontFamily = FontFamily(listOf(Font(R.font.montserrat_semibold)))
        )
        Text(
            text = currentTime,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            fontFamily = FontFamily(listOf(Font(R.font.montserrat_medium)))
        )
    }
}