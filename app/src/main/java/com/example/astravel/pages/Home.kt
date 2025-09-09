package com.example.astravel.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.astravel.SupabaseAuthViewModel
import com.example.astravel.ui.theme.Poppins
import com.example.astravel.ui.theme.PoppinsLight
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.compose.ui.draw.clip

@Composable
fun Home(
    navController: NavController,
    viewModel: SupabaseAuthViewModel,
) {
    val email = viewModel.currentEmail
    val focusManager = LocalFocusManager.current

    LaunchedEffect(email) {
        if (email != null && viewModel.lastName.isBlank() && viewModel.firstName.isBlank()) {
            viewModel.loadProfile()
        }
    }

    val isLoadingProfile = viewModel.profileLoading
    val greetingName = when {
        viewModel.lastName.isNotBlank() -> viewModel.lastName
        viewModel.firstName.isNotBlank() -> viewModel.firstName
        email != null && isLoadingProfile -> "Loading..."
        email != null -> email.substringBefore('@')
        else -> "Traveler"
    }
    var searchItem by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF031E2A))
            .pointerInput(Unit) { detectTapGestures(onTap = { focusManager.clearFocus() }) }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(bottom = 35.dp)
        ){
            Spacer(modifier = Modifier.height(40.dp))
            Row(){
                Column(
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                ) {
                    Text("Hello, $greetingName", fontFamily = PoppinsLight, fontSize = 16.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Let's Travel", fontFamily = Poppins, fontSize = 30.sp, color = Color.White)
                }
                Spacer(modifier = Modifier.width(130.dp))
                Box(modifier = Modifier
                    .size(75.dp)
                    .background(
                        color = Color(0xFF0E4966),
                        shape = CircleShape
                    )
                    .clickable {
                        navController.navigate("profile")
                    }
                ){
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(18.dp)
                    )
                }
            }
            Row(

            ) {
                Spacer(modifier = Modifier.width(20.dp))
                Box(modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = Color(0xFF0E4966),
                        shape = CircleShape
                    )
                    .align(Alignment.Bottom)
                    .clickable { navController.navigate("favourites") }
                ){
                    Icon(
                        Icons.Filled.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(15.dp)
                    )
                }


                OutlinedTextField(
                    value = searchItem,
                    onValueChange = { searchItem = it },
                    placeholder = { Text("Cox's Bazar") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            tint = Color(0xFF00BCD4),
                            modifier = Modifier.clickable(
                                onClick = {

                                }
                            )

                        )
                    },
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF0A3D62),
                        unfocusedContainerColor = Color(0xFF21618C),
                        cursorColor = Color(0xFF00BCD4),
                        focusedIndicatorColor = Color(0xFF00BCD4),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color(0xFF00BCD4),
                        unfocusedLabelColor = Color(0xFFB0BEC5),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .imePadding()) {
                Button(onClick = { navController.navigate("forest") }, modifier = Modifier.padding(start = 20.dp)) { Text("Forest") }
                Button(onClick = { navController.navigate("beach")}, modifier = Modifier.padding(start = 20.dp)) { Text("Beach") }
                Button(onClick = { navController.navigate("mountain") }, modifier = Modifier.padding(start = 20.dp)) { Text("Mountains") }
                Button(onClick = { navController.navigate("camping") }, modifier = Modifier.padding(start = 20.dp)) { Text("Camping") }
                Button(onClick = { navController.navigate("teagarden") }, modifier = Modifier.padding(start = 20.dp)) { Text("Tea Garden") }
                Button(onClick = { navController.navigate("historical") }, modifier = Modifier.padding(start = 20.dp, end = 20.dp)) { Text("Historical Sites") }
            }//the category row
            Spacer(modifier = Modifier.height(20.dp))
            Text("Popular Experiences", fontFamily = PoppinsLight, fontSize = 24.sp, color = Color.White, modifier = Modifier.padding(start = 20.dp))
            Row(modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .imePadding()){
                val imageShape = RoundedCornerShape(16.dp)
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
                        .clip(imageShape)
                        .background(Color.Gray, shape = imageShape)
                        .clickable(onClick = { navController.navigate("kuakata") })
                ) {
                    AsyncImage(
                        model = "https://tbbd-flight.s3.ap-southeast-1.amazonaws.com/blogWr-i2RxgD9A_AW5c2ZC_YkuR6blqICRf.png",
                        contentDescription = "Kuakata",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(androidx.compose.ui.Alignment.BottomStart)
                            .background(
                                Color(0x80000000),
                                shape = RoundedCornerShape(topEnd = 12.dp)
                            )
                    ) {
                        Text("Kuakata", fontFamily = PoppinsLight, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp))
                    }
                }
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
                        .clip(imageShape)
                        .background(Color.Gray, shape = imageShape)
                        .clickable(onClick = { navController.navigate("sajek") })
                ) {
                    AsyncImage(
                        model = "https://huntingworldbeauty.com/wp-content/uploads/2024/12/sajek-valley-0.1-1024x683.jpeg.webp",
                        contentDescription = "Sajek Valley",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(androidx.compose.ui.Alignment.BottomStart)
                            .background(
                                Color(0x80000000),
                                shape = RoundedCornerShape(topEnd = 12.dp)
                            )
                    ) {
                        Text("Sajek Valley", fontFamily = PoppinsLight, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp))
                    }
                }
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
                        .clip(imageShape)
                        .background(Color.Gray, shape = imageShape)
                        .clickable(onClick = { navController.navigate("coxs") })
                ) {
                    AsyncImage(
                        model = "https://i.pinimg.com/736x/b6/2d/23/b62d23f4ac85b860a28326936459fafa.jpg",
                        contentDescription = "Cox's Bazar",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(androidx.compose.ui.Alignment.BottomStart)
                            .background(
                                Color(0x80000000),
                                shape = RoundedCornerShape(topEnd = 12.dp)
                            )
                    ) {
                        Text("Cox's Bazar", fontFamily = PoppinsLight, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp))
                    }
                }
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
                        .clip(imageShape)
                        .background(Color.Gray, shape = imageShape)
                        .clickable(onClick = { navController.navigate("lalakhal") })
                ) {
                    AsyncImage(
                        model = "https://live.staticflickr.com/8614/16191761737_28b57c8a9e_b.jpg",
                        contentDescription = "Lalakhal",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(androidx.compose.ui.Alignment.BottomStart)
                            .background(
                                Color(0x80000000),
                                shape = RoundedCornerShape(topEnd = 12.dp)
                            )
                    ) {
                        Text("Lalakhal", fontFamily = PoppinsLight, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp))
                    }
                }
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
                        .clip(imageShape)
                        .background(Color.Gray, shape = imageShape)
                        .clickable(onClick = { navController.navigate("bandarban") })
                ) {
                    AsyncImage(
                        model = "https://cosmosgroup.sgp1.digitaloceanspaces.com/news/y8eC0WBzPEEVyKIGGjcM3zKIgirEYLTLvioF3GaP.jpeg",
                        contentDescription = "Bandarban",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(androidx.compose.ui.Alignment.BottomStart)
                            .background(
                                Color(0x80000000),
                                shape = RoundedCornerShape(topEnd = 12.dp)
                            )
                    ) {
                        Text("Bandarban", fontFamily = PoppinsLight, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp))
                    }
                }
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 20.dp)
                        .clip(imageShape)
                        .background(Color.Gray, shape = imageShape)
                        .clickable(onClick = { navController.navigate("sundarban") })
                ) {
                    AsyncImage(
                        model = "https://cosmosgroup.sgp1.digitaloceanspaces.com/news/details/6568412_Sundarban_river_mangrove_forest.jpg",
                        contentDescription = "Sundarbans",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(androidx.compose.ui.Alignment.BottomStart)
                            .background(
                                Color(0x80000000),
                                shape = RoundedCornerShape(topEnd = 12.dp)
                            )
                    ) {
                        Text("Sundarbans", fontFamily = PoppinsLight, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp))
                    }
                }
            }
            Text("Featured", fontFamily = PoppinsLight, fontSize = 24.sp, color = Color.White, modifier = Modifier.padding(start = 20.dp))
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .imePadding()
            ){
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 20.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Gray, shape = RoundedCornerShape(16.dp))
                        .clickable(onClick = { navController.navigate("bandarban") })
                ) {
                    AsyncImage(
                        model = "https://cosmosgroup.sgp1.digitaloceanspaces.com/news/y8eC0WBzPEEVyKIGGjcM3zKIgirEYLTLvioF3GaP.jpeg",
                        contentDescription = "Bandarban",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(androidx.compose.ui.Alignment.BottomStart)
                            .background(
                                Color(0x80000000),
                                shape = RoundedCornerShape(topEnd = 12.dp)
                            )
                    ) { Text("Bandarban", fontFamily = PoppinsLight, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) }
                }
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 20.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Gray, shape = RoundedCornerShape(16.dp))
                        .clickable(onClick = { navController.navigate("lalakhal") })
                ) {
                    AsyncImage(
                        model = "https://live.staticflickr.com/8614/16191761737_28b57c8a9e_b.jpg",
                        contentDescription = "Lalakhal",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(androidx.compose.ui.Alignment.BottomStart)
                            .background(
                                Color(0x80000000),
                                shape = RoundedCornerShape(topEnd = 12.dp)
                            )
                    ) { Text("Lalakhal", fontFamily = PoppinsLight, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) }
                }
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 20.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Gray, shape = RoundedCornerShape(16.dp))
                        .clickable(onClick = { navController.navigate("ratargul") })
                ) {
                    AsyncImage(
                        model = "https://www.travelandexplorebd.com/storage/app/public/posts/April2020/Ratargul%20Swamp%20Forest,%20Sylhet2.jpg",
                        contentDescription = "Ratargul",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(androidx.compose.ui.Alignment.BottomStart)
                            .background(
                                Color(0x80000000),
                                shape = RoundedCornerShape(topEnd = 12.dp)
                            )
                    ) { Text("Ratargul", fontFamily = PoppinsLight, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) }
                }
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 20.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Gray, shape = RoundedCornerShape(16.dp))
                        .clickable(onClick = { navController.navigate("rangamati") })
                ) {
                    AsyncImage(
                        model = "https://www.thefinancetoday.net/uploads/shares/Rangamati_hanging_Bridge-2019-12-24-12-47-02.jpg",
                        contentDescription = "Rangamati Lake",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .align(androidx.compose.ui.Alignment.BottomStart)
                            .background(
                                Color(0x80000000),
                                shape = RoundedCornerShape(topEnd = 12.dp)
                            )
                    ) { Text("Rangamati Lake", fontFamily = PoppinsLight, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) }
                }
            }
        }
    }
}