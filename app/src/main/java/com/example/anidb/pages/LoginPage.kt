package com.example.anidb.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.anidb.R
import com.example.anidb.Screens
import com.example.anidb.viewModels.AuthState
import com.example.anidb.viewModels.AuthViewModel

@Composable
fun LoginPage(authViewModel: AuthViewModel,navController: NavHostController){
    var email by remember {
        mutableStateOf("")
    }
    var pass by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val authState = authViewModel.authstate.observeAsState()
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> {
                navController.navigate(Screens.HomeScreen.route)
                Toast.makeText(context,"Logged In Successfully",Toast.LENGTH_SHORT).show()
            }
            is AuthState.Error -> {Toast.makeText(context, (authState.value as AuthState.Error).message,Toast.LENGTH_LONG).show()}
            else -> Unit
        }
    }

    Surface(
        color = colorResource(R.color.pageBack),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement =Arrangement.Top,
            modifier = Modifier
                .padding(start = 32.dp, top = 64.dp),

        ) {
            Text(
                text = "Login",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.Transparent)
        ) {
            Image(
                painter = painterResource(R.drawable.login_page_image),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(200.dp)
            )
            Card(
                colors = CardColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Unspecified,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Unspecified
                )
            ) {
                Text(
                    text="Email:",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = email,
                    onValueChange = {email=it},
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text="Password:",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = pass,
                    onValueChange = {pass=it},
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        authViewModel.signIn(email,pass)
                    },
                    modifier = Modifier
                        .width(280.dp),
                    enabled = authState.value != AuthState.Loading
                ) {
                    Text(
                        text="Login",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .width(280.dp)
                ) {
                    Text(
                        text = "Don't have an account?",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Text(
                        text = " Sign up",
                        fontSize = 16.sp,
                        color = colorResource(R.color.primary_blue),
                        modifier = Modifier
                            .clickable { navController.navigate(Screens.SignUpScreen.route) }
                    )

                }
            }
        }
    }
}