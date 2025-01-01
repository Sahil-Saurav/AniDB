package com.example.anidb

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.anidb.pages.AccountPage
import com.example.anidb.pages.CreateUserIDPage
import com.example.anidb.pages.DetailsPage
import com.example.anidb.pages.HomePage
import com.example.anidb.pages.LoginPage
import com.example.anidb.pages.SearchPage
import com.example.anidb.pages.SignUpPage
import com.example.anidb.pages.WelcomePage
import com.example.anidb.viewModels.AccountViewModel
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.AuthViewModel

@Composable
fun Navigation(viewModel: ApiViewModel,navController: NavHostController){
    val authViewModel = viewModel<AuthViewModel>()
    val accountViewModel = viewModel<AccountViewModel>()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.pageBack))
                .padding(innerPadding)
        ) {
            NavHost(navController = navController, startDestination = Screens.WelcomePage.route){
                composable(
                    route = Screens.HomeScreen.route,
                    enterTransition = { slideInHorizontally(initialOffsetX = {-it}) },
                    exitTransition = { slideOutHorizontally(targetOffsetX = {-it}) }
                    ) {
                    HomePage(viewModel,navController,accountViewModel)
                }
                composable(route = Screens.DetailsScreen.route) {
                    DetailsPage(viewModel,navController)
                }
                composable(
                    route = Screens.SearchScreen.route,
                    enterTransition = { slideInHorizontally(initialOffsetX = {-it})},
                    exitTransition = { slideOutHorizontally(targetOffsetX = {-it})}
                ) {
                    SearchPage(viewModel,accountViewModel,navController)
                }
                composable(
                    route = Screens.AccountScreen.route,
                    enterTransition = { slideInHorizontally(initialOffsetX = {-it})},
                    exitTransition = { slideOutHorizontally(targetOffsetX = {-it})}
                ) {
                    AccountPage(authViewModel,navController)
                }
                composable(
                    route = Screens.LoginScreen.route,
                    enterTransition = { slideInHorizontally(initialOffsetX = {-it})},
                    exitTransition = { slideOutHorizontally(targetOffsetX = {-it})}
                ) {
                    LoginPage(authViewModel,navController)
                }
                composable(
                    route = Screens.SignUpScreen.route,
                    enterTransition = { slideInHorizontally(initialOffsetX = {-it})},
                    exitTransition = { slideOutHorizontally(targetOffsetX = {-it})}
                ) {
                    SignUpPage(authViewModel,navController)
                }
                composable(
                    route = Screens.WelcomePage.route,
                    enterTransition = { slideInHorizontally(initialOffsetX = {-it})},
                    exitTransition = { slideOutHorizontally(targetOffsetX = {-it})}
                ) {
                    WelcomePage(authViewModel,navController)
                }
            }
        }
    }
}
