package com.example.astravel.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.astravel.SupabaseAuthViewModel
import com.example.astravel.ui.theme.Poppins
import com.example.astravel.ui.theme.PoppinsLight
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.verticalScroll
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
            modifier = Modifier.verticalScroll(rememberScrollState()).imePadding()
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
                    .background(color = Color(0xFF0E4966),
                        shape = CircleShape)
                    .clickable { navController.navigate("profile")
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
            OutlinedTextField(
                value = searchItem,
                onValueChange = {searchItem = it},
                placeholder = { Text("Cox's Bazar") },
                trailingIcon ={
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
                modifier = Modifier.height(75.dp).fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 20.dp),
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
            Spacer(modifier = Modifier.height(25.dp))
            Row(modifier = Modifier.horizontalScroll(rememberScrollState()).imePadding()) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(start = 20.dp)
                ) {
                    Text("Forest")
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(start = 20.dp)
                ) {
                    Text("Beach")
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(start = 20.dp)
                ) {
                    Text("Mountains")
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(start = 20.dp)
                ) {
                    Text("Camping")
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(start = 20.dp)
                ) {
                    Text("Tea Garden")
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(start = 20.dp)
                ) {
                    Text("Historical Sites")
                }

            }//the category row
            Spacer(modifier = Modifier.height(20.dp))
            Text("Popular Experiences", fontFamily = PoppinsLight, fontSize = 24.sp, color = Color.White, modifier = Modifier.padding(start = 20.dp))
            Row(modifier = Modifier.horizontalScroll(rememberScrollState()).imePadding()){
                val imageShape = RoundedCornerShape(16.dp)
                // Pair of (Name, Image URL)
                val experiences = listOf(
                    "Kuakata" to "https://travelsetu.com/apps/uploads/new_destinations_photos/destination/2024/06/29/86d551148963bcf5031d454511d1d5f3_1000x1000.jpg",
                    "Sajek Valley" to "https://images.pexels.com/photos/15286/pexels-photo.jpg",
                    "Cox's Bazar" to "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg"
                )
                experiences.forEach { (title, url) ->
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
                            .clip(imageShape)
                            .background(Color.Gray, shape = imageShape)
                            .clickable(onClick = {})
                    ) {
                        AsyncImage(
                            model = url,
                            contentDescription = title,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        // Title overlay
                        Box(
                            modifier = Modifier
                                .align(androidx.compose.ui.Alignment.BottomStart)
                                .background(Color(0x80000000), shape = RoundedCornerShape(topEnd = 12.dp))
                        ) {
                            Text(
                                title,
                                fontFamily = PoppinsLight,
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }//the popular experience row

        }
    }
}
