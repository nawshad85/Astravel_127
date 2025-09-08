package com.example.astravel.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.astravel.R
import com.example.astravel.SupabaseAuthViewModel
import com.example.astravel.data.model.UserState
import com.example.astravel.ui.theme.Poppins

@Composable
fun SignUp(
    navController: NavController,
    viewModel: SupabaseAuthViewModel
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var localError by remember { mutableStateOf<String?>(null) }

    val userState = viewModel.userState.value

    LaunchedEffect(userState) {
        if (userState is UserState.Success && userState.message == "Sign Up Successful") {
            navController.navigate("profile") {
                popUpTo("signup") { inclusive = true }
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
            Spacer(modifier = Modifier.height(80.dp))
            Image(
                painter = painterResource(id = R.drawable.globe),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(height = 200.dp, width = 230.dp)
            )
            Text(
                "Create Account",
                fontFamily = Poppins,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Join and explore the world",
                fontFamily = Poppins,
                fontSize = 16.sp,
                color = Color(0xFFB0BEC5)
            )
            Spacer(modifier = Modifier.height(40.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text("abc@gmail.com") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
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
            Spacer(modifier = Modifier.height(18.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
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
            Spacer(modifier = Modifier.height(18.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Confirm Password Icon",
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
            Spacer(modifier = Modifier.height(22.dp))
            Button(
                onClick = {
                    localError = null
                    if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                        localError = "All fields required"
                    } else if (password != confirmPassword) {
                        localError = "Passwords do not match"
                    } else {
                        viewModel.signUp(context, email, password)
                    }
                },
                modifier = Modifier
                    .width(320.dp)
                    .height(82.dp)
                    .padding(top = 8.dp, bottom = 8.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5EDFFF))
            ) {
                Text(
                    "Sign Up",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
            if (localError != null) {
                Text(localError!!, color = Color.Red, fontSize = 14.sp)
            } else if (userState is UserState.Error) {
                Text(userState.error, color = Color.Red, fontSize = 14.sp)
            } else if (userState is UserState.Loading) {
                Text("Processing...", color = Color.White, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            TextButton(onClick = { navController.popBackStack() }) {
                Text(
                    "Already have an account? Login",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    color = Color(0xFF00BCD4)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
