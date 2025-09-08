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
fun Mountain(
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
                    text = "Mountains",
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
                        .clickable { navController.navigate("bandarban") }
                ) {
                    AsyncImage(
                        model = "https://cosmosgroup.sgp1.digitaloceanspaces.com/news/y8eC0WBzPEEVyKIGGjcM3zKIgirEYLTLvioF3GaP.jpeg",
                        contentDescription = "Bandarban Hill Tracts",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .background(Color(0x80000000), shape = RoundedCornerShape(topEnd = 12.dp))
                    ) {
                        Text(
                            "Bandarban Hill Tracts",
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
                        .clickable { navController.navigate("rangamati") }
                ) {
                    AsyncImage(
                        model = "https://www.thefinancetoday.net/uploads/shares/Rangamati_hanging_Bridge-2019-12-24-12-47-02.jpg",
                        contentDescription = "Rangamati",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .background(Color(0x80000000), shape = RoundedCornerShape(topEnd = 12.dp))
                    ) {
                        Text(
                            "Rangamati",
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
