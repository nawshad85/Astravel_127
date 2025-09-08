package com.example.astravel.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun Favourites(
    navController: NavController,
    viewModel: SupabaseAuthViewModel
) {
    val dummy = listOf("Paris Trip", "Mountain Hike", "Beach Resort")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF031E2A)) // Add background color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Favourites",
                fontFamily = Poppins,
                fontSize = 34.sp,
                color = Color.White
            )
            Spacer(Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(dummy) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF0E4966))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(item, fontFamily = Poppins, fontSize = 18.sp, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
