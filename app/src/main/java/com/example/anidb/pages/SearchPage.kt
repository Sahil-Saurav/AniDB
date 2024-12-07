package com.example.anidb.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.anidb.R
import com.example.anidb.Utils.Bottom_App_Bar
import com.example.anidb.fragments.SearchList
import com.example.anidb.fragments.Search_Top
import com.example.anidb.viewModels.ApiViewModel
import com.example.anidb.viewModels.SearchViewModel

@Composable
fun SearchPage(viewModel: ApiViewModel,navController: NavHostController){
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { Bottom_App_Bar(navController)}
    ) {
        innerpadding->
        Column(
            modifier = Modifier
                .background(colorResource(R.color.pageBack))
                .fillMaxSize()
                .padding(bottom = innerpadding.calculateBottomPadding())
        ) {
            Search_Top(viewModel,navController)
            Spacer(modifier = Modifier.height(16.dp))
            SearchList(viewModel,navController)
        }
    }
}