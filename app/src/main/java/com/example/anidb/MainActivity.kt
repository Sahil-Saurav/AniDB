package com.example.anidb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.anidb.ui.theme.AniDBTheme
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.ApiViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AniDBTheme(darkTheme = false) {
                val viewModel: ApiViewModel by viewModels {
                    ApiViewModelFactory(applicationContext)
                }
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(R.color.pageBack)
                ) {
                    Navigation(viewModel,navController)
                    //testApi(viewModel)
                }
            }
        }
    }
}
// ViewModelProvider.create(this)[ApiViewModel::class.java]