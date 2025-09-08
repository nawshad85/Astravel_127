package com.example.astravel.places

import android.R
import android.R.attr.bottom
import android.graphics.drawable.shapes.RoundRectShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Bottom
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment.BottomLeft
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.astravel.SupabaseAuthViewModel
import com.example.astravel.ui.theme.Poppins
import com.example.astravel.ui.theme.PoppinsLight

@Composable
fun Sreemangal(
    navController: NavHostController,
    authViewModel: SupabaseAuthViewModel
) { // added onBack parameter with default
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF031E2A))
            .padding(bottom = 30.dp)
    ){
        Column(

        ) {
            Box(
                modifier = Modifier
                    .height(350.dp)
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 55.dp, bottom = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Gray, shape = RoundedCornerShape(20.dp))
            ) {
                AsyncImage(
                    model = "https://teabazarbd.com/wp-content/uploads/2023/02/best-tea-of-sreemangal-tea-bazar-bd.webp",
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Back button overlay
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp)
                        .size(36.dp)
                        .background(Color(0x55000000), RoundedCornerShape(10.dp))
                        .padding(6.dp)
                        .align(Alignment.TopStart)
                        .clickable { navController.popBackStack() }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                ) {
                    Text(
                        "Sreemangal",
                        fontFamily = Poppins,
                        fontSize = 26.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 20.dp, bottom = 20.dp)
                    )
                    Text(
                        "4.5⭐",
                        fontFamily = Poppins,
                        fontSize = 26.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 120.dp, bottom = 10.dp)
                    )
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                            .background(
                                color = Color(0xFF263238),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                    ){
                        Image(
                            painter = painterResource(id = com.example.astravel.R.drawable.difficultyicon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .align(Alignment.Center)
                        )
                    }
                    Text(
                        "Difficulty",
                        fontFamily = PoppinsLight,
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                    Text(
                        "Medium",
                        fontFamily = Poppins,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                            .background(
                                color = Color(0xFF263238),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                    ){
                        Image(
                            painter = painterResource(id = com.example.astravel.R.drawable.timer),
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .align(Alignment.Center)
                        )
                    }
                    Text(
                        "Time Needed",
                        fontFamily = PoppinsLight,
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                    Text(
                        "2-3 days",
                        fontFamily = Poppins,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                            .background(
                                color = Color(0xFF263238),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                    ){
                        Image(
                            painter = painterResource(id = com.example.astravel.R.drawable.cost),
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .align(Alignment.Center)
                        )
                    }
                    Text(
                        "Ticket",
                        fontFamily = PoppinsLight,
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                    Text(
                        "2500 BDT",
                        fontFamily = Poppins,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
                thickness = 1.dp,
                color = Color.Gray
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .imePadding()
            ) {
                Text(
                    "About",
                    fontFamily = Poppins,
                    fontSize = 22.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 30.dp, top = 20.dp, bottom = 10.dp)
                )
                Text(
                    "Sreemangal, located in the Sylhet district, is famously known as the “Tea Capital of Bangladesh” and is celebrated for its rolling tea gardens, lush forests, and tranquil countryside. Visitors can stroll through expansive tea estates, enjoy fresh tea tasting, and explore Lawachara National Park, home to rare wildlife like the hoolock gibbon. The area also offers opportunities to visit tribal villages and experience local culture. Best time to visit is October to March for pleasant weather and vibrant greenery. Travelers should carry essentials, respect the environment, and hire local guides to enhance their scenic and cultural experience.\n",
                    fontFamily = PoppinsLight,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 30.dp, end = 30.dp)
                )
                Row(
                    modifier = Modifier.padding(start = 30.dp, end = 30.dp).align(Alignment.CenterHorizontally),
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .width(140.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(width = 1.dp, color = Color(0xFF5EDFFF), shape = RoundedCornerShape(20.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF031E2A))
                    ) {
                        Image(
                            painter = painterResource(id = com.example.astravel.R.drawable.sitemap),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Site Map",
                            fontFamily = Poppins,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .width(140.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5EDFFF))
                    ) {
                        Text(
                            "Start",
                            fontFamily = Poppins,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }

                }
            }
        }
    }
}
