package com.example.anidb

sealed class Screens(val route:String){
    object HomeScreen:Screens("home_screen")
    object DetailsScreen:Screens("details_screen")
    object SearchScreen:Screens("search_screen")
    object LoginScreen:Screens("login_screen")
    object SignUpScreen:Screens("signup_screen")
    object AccountScreen:Screens("account_screen")
    object WelcomePage:Screens("welcome_screen")
}