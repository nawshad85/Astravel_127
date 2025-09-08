package com.example.astravel.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.astravel.LoadingComponent
import com.example.astravel.R
import com.example.astravel.SupabaseAuthViewModel
import com.example.astravel.data.model.UserState
import com.example.learnauth.ui.theme.Poppins
import androidx.compose.ui.platform.LocalContext


@Composable
fun Welcome(
    navController: NavController,
    viewModel: SupabaseAuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val userState = viewModel.userState.value
    val navigated = remember { mutableStateOf(false) }

    // React to state changes safely (only once)
    LaunchedEffect(userState) {
        if (navigated.value) return@LaunchedEffect
        when (userState) {
            is UserState.Success -> {
                if (userState.message.contains("is logged in")) {
                    navigated.value = true
                    navController.navigate("profile") {
                        popUpTo("welcome") { inclusive = true }
                        launchSingleTop = true
                    }
                } else if (userState.message.contains("Login") || userState.message.contains("Sign Up")) {
                    // successful explicit auth elsewhere; ignore here
                } else {
                    navController.navigate("login")
                }
            }
            is UserState.Error -> {
                navigated.value = true
                navController.navigate("login")
            }
            UserState.Loading -> { /* no-op */ }
        }
    }

    // UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF031E2A))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(150.dp))
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(height = 250.dp, width = 360.dp)
            )
            Spacer(Modifier.height(40.dp))
            Text(
                "Escape the ordinary life",
                color = Color.White,
                fontSize = 26.sp,
                fontFamily = Poppins
            )
            Spacer(Modifier.height(20.dp))
            Text(
                "Discover great experiences around you and make you live interesting!",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = Poppins,
                    color = Color(214, 210, 210, 140)
                ),
                textAlign = TextAlign.Center,
                lineHeight = 21.sp
            )
            Spacer(Modifier.height(260.dp))
            Button(
                onClick = {
                    viewModel.isUserLoggedIn(context)
                },
                modifier = Modifier
                    .width(320.dp)
                    .height(82.dp)
                    .padding(top = 16.dp, bottom = 16.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5EDFFF))
            ) {
                Text(
                    "Get Started",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    color = Color.White,
                )
            }
        }
    }
}
