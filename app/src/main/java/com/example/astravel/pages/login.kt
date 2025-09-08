package com.example.astravel.pages


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.astravel.SupabaseAuthViewModel
import com.example.astravel.R
import com.example.astravel.data.model.UserState
import com.example.astravel.ui.theme.Poppins


@Composable
fun Login(
    navController: NavController,
    viewModel: SupabaseAuthViewModel
){
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var userPassword by rememberSaveable { mutableStateOf("") }
    var userEmail by rememberSaveable { mutableStateOf("") }
    val userState = viewModel.userState.value

    LaunchedEffect(userState) {
        if (userState is UserState.Success && userState.message == "Login Successful") {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit){ detectTapGestures { focusManager.clearFocus() } }
            .background(color = Color(0xFF031E2A))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Image(
                painter = painterResource(id = R.drawable.globe),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(height = 220.dp, width = 250.dp)
            )
            Text("Welcome Back!",
                fontFamily = Poppins,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text("Login to continue your account",
                fontFamily = Poppins,
                fontSize = 16.sp,
                color = Color(0xFFB0BEC5)
            )
            Spacer(modifier = Modifier.height(40.dp))
            OutlinedTextField(
                value = userEmail,
                onValueChange = { userEmail = it },
                label = { Text("Email") },
                placeholder = { Text("abc@gmail.com") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = Color(0xFF00BCD4)

                    ) },
                modifier = Modifier.size(height = 65.dp, width = 300.dp),
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
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = userPassword,
                onValueChange = {userPassword = it},
                label = { Text("Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon",
                        tint = Color(0xFF00BCD4)

                    )
                },
                modifier = Modifier.size(height = 65.dp, width = 300.dp),
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
            Spacer(modifier = Modifier.height(10.dp))
            Row() {
                Spacer(modifier = Modifier.width(150.dp))
                TextButton(
                    onClick = {},

                    ) {
                    Text("Forgot Password?",
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 18.sp,
                        color = Color(0xFF00BCD4)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if (userEmail.isNotBlank() && userPassword.isNotBlank()) {
                        viewModel.login(context, userEmail, userPassword)
                    }
                },
                modifier = Modifier
                    .width(320.dp)
                    .height(82.dp)
                    .padding(top = 16.dp,bottom = 16.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5EDFFF))

            ) {
                Text("Login",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    color = Color.White,
                )
            }
            if (userState is UserState.Loading) {
                Text("Processing...", color = Color.White, fontSize = 14.sp)
            }
            Button(onClick = { navController.navigate("signup") },
                modifier = Modifier
                    .width(320.dp)
                    .height(82.dp)
                    .padding(top = 16.dp,bottom = 16.dp)
                    .border(
                        width = 1.5.dp,
                        color = Color(0xFF5EDFFF),
                        shape = RoundedCornerShape(10.dp)
                    ),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF263238)),
            ) {
                Text("Sign Up",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    color = Color.White,
                )
            }
        }
    }
}