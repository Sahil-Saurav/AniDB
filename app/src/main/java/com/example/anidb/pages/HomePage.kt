package com.example.anidb.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ChipColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.anidb.R
import com.example.anidb.Utils.Bottom_App_Bar
import com.example.anidb.api.NetworkResponse
import com.example.anidb.fragments.FilterChipsList
import com.example.anidb.fragments.Filters
import com.example.anidb.fragments.Recommendations
import com.example.anidb.items.RecommendationItems
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.HomeViewModel

@Composable
fun HomePage(viewModel: ApiViewModel,navController: NavHostController){
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

