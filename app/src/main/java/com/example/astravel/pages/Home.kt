package com.example.astravel.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.astravel.SupabaseAuthViewModel
import com.example.learnauth.ui.theme.Poppins

@Composable
fun Home(
    navController: NavController,
    viewModel: SupabaseAuthViewModel
) {
    val email = viewModel.currentEmail

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF031E2A)) // Add background color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Home",
                fontFamily = Poppins,
                fontSize = 34.sp,
                color = Color.White
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = if (email != null) "Welcome back" else "Browse as guest",
                fontFamily = Poppins,
                fontSize = 16.sp,
                color = Color(0xFFB0BEC5)
            )
            Spacer(Modifier.height(40.dp))
            Button(
                onClick = { navController.navigate("profile") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5EDFFF))
            ) { Text("Go to Profile", fontFamily = Poppins, color = Color(0xFF012632)) }
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("favourites") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF21618C))
            ) { Text("View Favourites", fontFamily = Poppins, color = Color.White) }
        }
    }
}
