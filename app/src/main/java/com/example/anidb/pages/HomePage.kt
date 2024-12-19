package com.example.anidb.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.anidb.R
import com.example.anidb.Utils.Bottom_App_Bar
import com.example.anidb.fragments.FilterChipsList
import com.example.anidb.fragments.Filters
import com.example.anidb.fragments.Recommendations
import com.example.anidb.viewModels.AccountViewModel
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.HomeViewModel

@Composable
fun HomePage(viewModel: ApiViewModel,navController: NavHostController,accountViewModel: AccountViewModel){
    val homeViewModel:HomeViewModel = viewModel()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { Bottom_App_Bar(navController)}
    ) {
        innerpadding->
        val verticalScrollState = rememberScrollState()
        val filterTitle = homeViewModel.label
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.pageBack))
                .verticalScroll(verticalScrollState)
                .padding(
                    top = 8.dp,
                    bottom = innerpadding.calculateBottomPadding(),
                    start = 8.dp,
                    end = 8.dp
                    )
        ) {
            TopText()
            Recommendations(viewModel = viewModel,navController=navController)
            Spacer(modifier = Modifier.height(8.dp))
            FilterChipsList(viewModel)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text=filterTitle.value,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White)
            Filters(viewModel, navController = navController)
        }
    }
}

@Composable
fun TopText(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 4.dp,
            )
    ) {
        Text(
            text = "What do you want to watch?",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Recommendations",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )

    }
}

