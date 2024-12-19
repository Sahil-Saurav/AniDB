package com.example.anidb.Utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.anidb.R
import com.example.anidb.Screens

@Composable
fun Bottom_App_Bar(navController: NavHostController){
    BottomAppBar(
        contentColor = colorResource(R.color.primary_blue),
        containerColor = colorResource(R.color.pageBack),
        modifier = Modifier
            .height(64.dp)
    ) {
        Column(
            modifier = Modifier
                .background(colorResource(R.color.pageBack))
        ) {
            HorizontalDivider(
                thickness = 2.dp,
                color = colorResource(R.color.primary_blue)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .clickable { navController.navigate(Screens.HomeScreen.route) }
                ) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "home")
                    Text("Home")
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .clickable { navController.navigate(Screens.SearchScreen.route) }
                ) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
                    Text("Search")
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screens.AccountScreen.route)
                        }
                ) {
                    Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "account")
                    Text("Account")
                }
            }
        }
    }
}