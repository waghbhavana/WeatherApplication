package com.bhavanawagh.weatherapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bhavanawagh.weatherapplication.data.model.WeatherResponse
import com.bhavanawagh.weatherapplication.util.NetworkResponse


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun WeatherScreen(viewModel: WeatherViewModel) {

    var city by remember {
        mutableStateOf("")
    }
    val weatherResult = viewModel.weatherResponse.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = city,
                onValueChange = {
                    city = it
                },
                label = {
                    Text(text = "Search for any location")
                }
            )
            IconButton(onClick = {
                viewModel.getLatLonByCityName(city)
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")

            }
        }
        when (val result = weatherResult.value) {
            is NetworkResponse.Error -> Text(text = result.message)
            is NetworkResponse.Loading -> CircularProgressIndicator()
            is NetworkResponse.Success -> {
                // println("response is lat: ${result.data.toString()}")
                //Text(text = result.data.toString())
                weatherDetails(result.data)
            }

            else -> {}
        }

    }


}

//If would have time I can display more details about weather here
// as information got from API response. For now I kept is simple
@Composable
fun weatherDetails(data: WeatherResponse) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = data.weather[0].main, fontSize = 30.sp)
            Spacer(modifier = Modifier.width(8.dp))

        }
        AsyncImage(
            modifier = Modifier.size(160.dp),
            model = "https://openweathermap.org/img/wn/" + data.weather[0].icon + "@2x.png",
            contentDescription = null,
        )
        Text(text = data.weather[0].description, fontSize = 20.sp)

    }

}
