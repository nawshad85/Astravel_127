package com.example.astravel.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.astravel.SupabaseAuthViewModel
import com.example.astravel.ui.theme.Poppins
import com.example.astravel.ui.theme.PoppinsLight

@Composable
fun Camping(
    navController: androidx.navigation.NavHostController,
    viewModel: SupabaseAuthViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF031E2A))
    ) {
        Column(

        ) {
            Row(

            ){
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 57.dp)
                        .size(36.dp)
                        .background(Color(0x55000000), RoundedCornerShape(10.dp))
                        .padding(6.dp)
                        .clickable { navController.popBackStack() }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Camping Sites",
                    fontFamily = Poppins,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 58.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .imePadding()
                    .padding(top = 52.dp)
            ) {
                // Cox's Bazar card
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 20.dp, bottom = 10.dp, end = 20.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Gray, shape = RoundedCornerShape(16.dp))
                        .clickable { navController.navigate("sajek") }
                ) {
                    AsyncImage(
                        model = "https://huntingworldbeauty.com/wp-content/uploads/2024/12/sajek-valley-0.1-1024x683.jpeg.webp",
                        contentDescription = "Sajek Valley",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .background(Color(0x80000000), shape = RoundedCornerShape(topEnd = 12.dp))
                    ) {
                        Text(
                            "Sajek Valley",
                            fontFamily = PoppinsLight,
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
                // Kuakata card
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 10.dp, bottom = 20.dp, end = 20.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Gray, shape = RoundedCornerShape(16.dp))
                        .clickable { navController.navigate("lalakhal") }
                ) {
                    AsyncImage(
                        model = "https://live.staticflickr.com/8614/16191761737_28b57c8a9e_b.jpg",
                        contentDescription = "Lalakhal",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .background(Color(0x80000000), shape = RoundedCornerShape(topEnd = 12.dp))
                    ) {
                        Text(
                            "Lalakhal",
                            fontFamily = PoppinsLight,
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}