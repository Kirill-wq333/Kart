package com.example.fair2

import android.graphics.Paint
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.DeadObjectException
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fair2.ui.theme.Fair2Theme
import com.skydoves.cloudy.cloudy
import kotlinx.coroutines.delay
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Fair2Theme {
                WeatherContent(
                    temperature = "31",
                    nameWeather = "Облачно",
                )
//                BootScreen()
//                Error(
//                    link = "retrofit.NetworkException: host http://weather.com/ could not be resolved",
//                    error = "Ошибка"
//                )
            }
        }
    }
}


@Preview
@Composable
fun WeatherContentPreview(){
    Surface(color = Color.White) {
      WeatherContent(
          temperature = "31",
          nameWeather = "Облачно",
      )
    }
}

@Composable
fun WeatherContent(
    temperature: String,
    nameWeather: String
) {
    val selectedItem = remember {
        mutableStateOf("Самара")
    }
    Scaffold(
        topBar = {
           City(
               selectedItem = selectedItem.value,
               items = listOf("Самара", "Москва", "Владивосток"),
               onSelect = { selectedItem.value = it }
           )
        },

        bottomBar = {
            WeatherDetails(
                localTime = "8:14",
                windSpeed = 4.5,
                airPressure = 759,
                humidity = 42
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .blur(50.dp)
                    .padding(40.dp)
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(1f)
                    .background(
                        Color(0xFF9BB7F2).copy(0.7f),
                        shape = CircleShape
                    )
            )
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.partly_cloudy_day),
                    contentDescription = null
                )
                Text(
                    text = nameWeather,
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(listOf(Font(R.font.montserrat_medium)))
                )
                Row(
                    modifier = Modifier
                        .padding(27.dp, 0.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = temperature,
                        fontSize = 70.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(listOf(Font(R.font.montserrat_semibold)))
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ellipse_1),
                        contentDescription = null,
                    )
                }
            }
        }

    }
}
@Composable
fun DetailsTextBlock(
    title: String,
    subtitle: String
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun City(
    modifier: Modifier = Modifier,
    selectedItem: String,
    items: List<String>,
    onSelect: (String) -> Unit
){

    val isExpanded = remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp, 22.dp)
            .background(
                color = Color.Black.copy(0.05f),
                shape = RoundedCornerShape(12.dp)
            ),
        expanded = isExpanded.value,
        onExpandedChange = { isExpanded.value = it },
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 9.dp)
                    .menuAnchor(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedItem,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily(listOf(Font(R.font.montserrat_semibold))),
                    modifier = Modifier
                )
                ExposedDropdownMenuDefaults.TrailingIcon(
                    modifier = Modifier,
                    expanded = isExpanded.value
                )
            }
            ExposedDropdownMenu(
                expanded = isExpanded.value,
                onDismissRequest = { isExpanded.value = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            onSelect(item)
                            isExpanded.value = false
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun WeatherDetails(
    localTime: String,
    windSpeed: Double,
    airPressure: Int,
    humidity: Int,
){
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
            DetailsTextBlock(
                title = "Время",
                subtitle = "$localTime"
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

@Composable
fun BootScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            ),
            contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(R.drawable.circular_determinate_progress_indicator),
            contentDescription = null
        )
        Image(
            painter = painterResource(R.drawable.circular_indeterminate_progress_indicator),
            contentDescription = null
        )
    }
}


@Preview
@Composable
fun BootScreenPreview(){
    BootScreen()
}

@Composable
fun Error(
    link: String,
    error: String
){
      Box(
        modifier = Modifier
        .fillMaxSize()
        .background(
         color = Color.White
      ),
        contentAlignment = Alignment.Center
    ){
       Column(
           modifier = Modifier
               .padding(60.dp, 259.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
            Image(
                painter = painterResource(R.drawable.alert),
                contentDescription = null
            )
           Text(
               modifier = Modifier
                   .fillMaxWidth(),
               text = error,
               fontSize = 30.sp,
               textAlign = TextAlign.Center,
               fontWeight = FontWeight.SemiBold,
               fontFamily = FontFamily(listOf(Font(R.font.montserrat_semibold)))
           )
           Text(
               text = link,
               fontSize = 16.sp,
               textAlign = TextAlign.Center,
               fontWeight = FontWeight.Medium,
               fontFamily = FontFamily(listOf(Font(R.font.montserrat_medium)))
           )
       }
     }
}

@Preview
@Composable
fun ErrorPreview(){
    Error(
        link = "retrofit.NetworkException: host http://weather.com/ could not be resolved",
        error = "Ошибка"
    )
}