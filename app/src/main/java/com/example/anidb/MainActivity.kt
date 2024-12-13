package com.example.anidb

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.anidb.Utils.Connection_Failure_Toast
import com.example.anidb.Utils.Connectivity_Manager
import com.example.anidb.pages.HomePage
import com.example.anidb.pages.LoginPage
import com.example.anidb.ui.theme.AniDBTheme
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.ApiViewModelFactory
import com.google.android.gms.common.api.Api

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
                }
            }
        }
    }
}
// ViewModelProvider.create(this)[ApiViewModel::class.java]