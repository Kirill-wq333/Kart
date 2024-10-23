package com.example.fair2

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.DeadObjectException
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
                val selectedItem = remember {
                    mutableStateOf("Самара")
                }
                WeatherContent(
                    localTime = "8:14",
                    windSpeed = 4.5,
                    airPressure = 759,
                    humidity = 42,
                    selectedItem = selectedItem.value,
                    items = listOf("Самара", "Москва", "Владивосток"),
                    onSelect = { selectedItem.value = it }
                )
            }
        }
    }
}


@Preview
@Composable
fun WeatherContentPreview(){
    Surface(color = Color.White) {
        val selectedItem = remember {
            mutableStateOf("Самара")
        }

      WeatherContent(
          localTime = "8:14",
          windSpeed = 4.5,
          airPressure = 759,
          humidity = 42,
          selectedItem = selectedItem.value,
          items = listOf("Самара", "Москва", "Владивосток"),
          onSelect = { selectedItem.value = it }
      )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherContent(
    modifier: Modifier = Modifier,
    localTime: String,
    windSpeed: Double,
    airPressure: Int,
    humidity: Int,
    selectedItem: String,
    items: List<String>,
    onSelect: (String) -> Unit
){


    Scaffold(
        topBar = {
            val isExpanded = remember {
                mutableStateOf(false)
            }
            ExposedDropdownMenuBox(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(24.dp,22.dp)
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
    ) {
Box(
    modifier = Modifier
        .padding(it)
        .fillMaxSize()
        .background(color = Color.Black),

){
Column() {  }
}
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

