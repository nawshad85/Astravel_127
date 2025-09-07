package com.example.astravel.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.astravel.SupabaseAuthViewModel
import com.example.astravel.data.model.UserState
import com.example.astravel.LoadingComponent
import com.example.learnauth.ui.theme.Poppins
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf

@Composable
fun Profile(
    navController: NavHostController,
    authViewModel: SupabaseAuthViewModel
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val userState = authViewModel.userState.value
    val email = authViewModel.currentEmail

    if (userState is UserState.Loading) {
        LoadingComponent(); return
    }

    val primaryBlue = Color(0xFF031E2A)
    val accent = Color(0xFF5EDFFF)
    val gradientTop = Color(0xFF06354C)
    val gradientBottom = primaryBlue

    // Dynamic stats (placeholder values for logged-in state; zeros when logged out)
    val tripsValue = if (email != null) "12" else "0"
    val savedValue = if (email != null) "5" else "0"
    val reviewsValue = if (email != null) "9" else "0"

    // Profile editable fields
    val firstName = authViewModel.firstName
    val lastName = authViewModel.lastName
    val phone = authViewModel.phone
    val profileLoading = authViewModel.profileLoading
    val profileMessage = authViewModel.profileMessage

    // Load profile when email becomes available
    LaunchedEffect(email) {
        if (email != null && firstName.isEmpty() && lastName.isEmpty() && phone.isEmpty()) {
            authViewModel.loadProfile()
        }
    }

    var showLogoutConfirm = androidx.compose.runtime.remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit){ detectTapGestures { focusManager.clearFocus() } }
            .background(
                Brush.verticalGradient(
                    listOf(gradientTop, gradientBottom)
                )
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding() // added for keyboard insets like Login page
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            // Top Bar with title + logout icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Profile",
                    fontFamily = Poppins,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { showLogoutConfirm.value = true }) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout",
                        tint = Color(0xFF5EDFFF)
                    )
                }
            }

            // Avatar
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(color = Color(0xFF0A3D62), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                val initial = email?.firstOrNull()?.uppercaseChar() ?: 'U'
                Text(
                    text = initial.toString(),
                    fontFamily = Poppins,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = accent
                )
            }
            Spacer(Modifier.height(14.dp))
            Text(
                text = email ?: "No user",
                fontFamily = Poppins,
                fontSize = 18.sp,
                color = Color(0xFFB0BEC5)
            )
            Spacer(Modifier.height(28.dp))

            // Stats Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatCard(title = "Trips", value = tripsValue)
                StatCard(title = "Saved", value = savedValue)
                StatCard(title = "Reviews", value = reviewsValue)
            }
            Spacer(Modifier.height(28.dp))

            // Info / actions container
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFF0A3D62).copy(alpha = 0.65f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Account",
                        fontFamily = Poppins,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    ProfileRow(label = "Email", value = email ?: "-")
                    Spacer(Modifier.height(8.dp))
                    ProfileRow(label = "Status", value = if (email != null) "Active" else "Guest")
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = "Edit Profile",
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { authViewModel.onFirstNameChange(it) },
                        label = { Text("First Name") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF5EDFFF)) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF0A3D62),
                            unfocusedContainerColor = Color(0xFF06354C),
                            cursorColor = Color(0xFF5EDFFF),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedLabelColor = Color(0xFF5EDFFF),
                            unfocusedLabelColor = Color(0xFFB0BEC5),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { authViewModel.onLastNameChange(it) },
                        label = { Text("Last Name") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF5EDFFF)) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF0A3D62),
                            unfocusedContainerColor = Color(0xFF06354C),
                            cursorColor = Color(0xFF5EDFFF),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedLabelColor = Color(0xFF5EDFFF),
                            unfocusedLabelColor = Color(0xFFB0BEC5),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { authViewModel.onPhoneChange(it) },
                        label = { Text("Phone") },
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = Color(0xFF5EDFFF)) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF0A3D62),
                            unfocusedContainerColor = Color(0xFF06354C),
                            cursorColor = Color(0xFF5EDFFF),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedLabelColor = Color(0xFF5EDFFF),
                            unfocusedLabelColor = Color(0xFFB0BEC5),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )
                    Button(
                        onClick = { if (!profileLoading) authViewModel.saveProfile() },
                        enabled = email != null && !profileLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (profileLoading) Color(0xFF4AA9C7) else Color(0xFF5EDFFF),
                            disabledContainerColor = Color(0xFF2E5B6C)
                        )
                    ) {
                        if (profileLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(22.dp), strokeWidth = 2.5.dp, color = Color(0xFF012632))
                        } else {
                            Text(
                                text = "Save Profile",
                                fontFamily = Poppins,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF012632)
                            )
                        }
                    }
                    if (profileMessage != null) {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = profileMessage,
                            fontFamily = Poppins,
                            fontSize = 13.sp,
                            color = if (profileMessage.contains("failed", true)) Color(0xFFFF6B6B) else Color(0xFF5EDFFF)
                        )
                    }
                }
            }
            Spacer(Modifier.height(32.dp))

            // Remove bottom Logout button section
            if (userState is UserState.Error) {
                Spacer(Modifier.height(12.dp))
                Text(
                    text = userState.error,
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    color = Color(0xFFFF6B6B),
                    textAlign = TextAlign.Center
                )
            }
        }

        if (showLogoutConfirm.value) {
            AlertDialog(
                onDismissRequest = { showLogoutConfirm.value = false },
                title = { Text("Logout") },
                text = { Text("Are you sure you want to logout?") },
                confirmButton = {
                    TextButton(onClick = {
                        showLogoutConfirm.value = false
                        authViewModel.logout(context)
                    }) { Text("Yes") }
                },
                dismissButton = {
                    TextButton(onClick = { showLogoutConfirm.value = false }) { Text("Cancel") }
                },
                containerColor = Color(0xFF0A3D62),
                titleContentColor = Color.White,
                textContentColor = Color(0xFFB0BEC5)
            )
        }
    }

    // Redirect to login after logout success
    LaunchedEffect(userState) {
        if (userState is UserState.Success && userState.message == "Logout Successful") {
            navController.navigate("login") {
                popUpTo("profile") { inclusive = true }
                launchSingleTop = true
            }
        }
    }
}

@Composable
private fun StatCard(title: String, value: String){
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(90.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0E4966)),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontFamily = Poppins,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = title,
                fontFamily = Poppins,
                fontSize = 13.sp,
                color = Color(0xFFB0BEC5)
            )
        }
    }
}

@Composable
private fun ProfileRow(label: String, value: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontFamily = Poppins,
            fontSize = 16.sp,
            color = Color(0xFFB0BEC5)
        )
        Text(
            text = value,
            fontFamily = Poppins,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}
