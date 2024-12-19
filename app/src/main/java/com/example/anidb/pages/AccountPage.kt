package com.example.anidb.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.anidb.R
import com.example.anidb.Screens
import com.example.anidb.Utils.Bottom_App_Bar
import com.example.anidb.Utils.Show_SignOut_Feature
import com.example.anidb.Utils.Topbar
import com.example.anidb.items.WatchListItems
import com.example.anidb.viewModels.AccountViewModel
import com.example.anidb.viewModels.AuthState
import com.example.anidb.viewModels.AuthViewModel
import com.example.anidb.viewModels.WatchList
import com.google.firebase.auth.FirebaseAuth
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun AccountPage(authViewModel: AuthViewModel,navController: NavHostController){
    val authState = authViewModel.authstate.observeAsState()
    val accountViewModel = viewModel<AccountViewModel>()
    val userDetails by accountViewModel.data
    val auth = FirebaseAuth.getInstance()
    var showSignOut_Alert by remember {
        mutableStateOf(false)
    }
    val watchlist = accountViewModel.data.value?.watchList?: emptyList()
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate(Screens.LoginScreen.route)
            is AuthState.Authenticated -> {
                val email = auth.currentUser?.email?:""
                accountViewModel.getUser(email)
            }
            else -> Unit
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            Bottom_App_Bar(navController)
        }
    ) {
        innerPadding->
        Column(
            modifier = Modifier
                .background(colorResource(R.color.pageBack))
                .padding(bottom = innerPadding.calculateBottomPadding()
                )
        ) {
            Topbar(
                title = "Account",
                onBackClick = {navController.navigateUp()},
                hasEndButton = true,
                endButtonIcon = painterResource(R.drawable.signout_icon),
                onEndButtonClick = {showSignOut_Alert = true}
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text= "User Details:",
                    color = colorResource(R.color.orange),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(top=16.dp, bottom = 8.dp, start = 16.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Text(
                        text= "User Name: ",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top=16.dp, start = 16.dp)
                    )
                    Text(
                        text= userDetails?.name?:"Not Available",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top=16.dp, bottom = 8.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text= "Email: ",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text= userDetails?.emailID?:"Not Available",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "WatchList",
                    fontSize = 24.sp,
                    color = colorResource(R.color.orange),
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (watchlist.isNullOrEmpty()){
                        Text(
                            text = "Your watchlist is empty!!\nAdd some and see here whenever :)",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.fillMaxSize(),
                            textAlign = TextAlign.Center,
                        )
                    }else{
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp)
                        ) {
                        items(watchlist){
                                item ->
                            WatchListItems(
                                item,
                                {
                                    accountViewModel.removeWatch_List(item,auth.currentUser?.email?:"")
                                    accountViewModel.getUser(auth.currentUser?.email?:"")
                                }
                            )
                        }
                    }
                }
            }
        }
        if (showSignOut_Alert){
            Show_SignOut_Feature(
                onClick = {authViewModel.signOut()},
                onDismiss = {showSignOut_Alert = false}
            )
        }
    }
}