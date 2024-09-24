package com.bhavanawagh.weatherapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.bhavanawagh.weatherapplication.ui.screens.WeatherScreen
import com.bhavanawagh.weatherapplication.ui.screens.WeatherViewModel
import com.bhavanawagh.weatherapplication.ui.theme.WeatherApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setUpViewModel()
                  WeatherScreen(viewModel)
                }
            }
        }
    }

    private fun setUpViewModel(){
        viewModel=ViewModelProvider(this)[WeatherViewModel::class.java]
    }
}

