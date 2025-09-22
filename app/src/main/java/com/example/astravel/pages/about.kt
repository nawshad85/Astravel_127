package com.example.astravel.pages

import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.astravel.SupabaseAuthViewModel
import com.example.astravel.ui.theme.Poppins
import java.util.Calendar
import android.content.Intent
import androidx.core.net.toUri
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.astravel.R

@Suppress("UNUSED_PARAMETER")
@Composable
fun About(
    navController: NavHostController,
    authViewModel: SupabaseAuthViewModel
) {
    val context = LocalContext.current
    val primaryBlue = Color(0xFF031E2A)
    val gradientTop = Color(0xFF06354C)
    val gradientBottom = primaryBlue
    val cardBg = Color(0xFF0A3D62).copy(alpha = 0.65f)
    val accent = Color(0xFF5EDFFF)

    // App version info
    val pm = context.packageManager
    val pkg = context.packageName
    val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        pm.getPackageInfo(pkg, PackageManager.PackageInfoFlags.of(0))
    } else {
        @Suppress("DEPRECATION")
        pm.getPackageInfo(pkg, 0)
    }
    val versionName = packageInfo.versionName ?: "-"
    val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        packageInfo.longVersionCode.toString()
    } else {
        @Suppress("DEPRECATION")
        packageInfo.versionCode.toString()
    }

    // Helper to open links
    fun openUrl(url: String) {
        runCatching {
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            context.startActivity(intent)
        }
    }

    var showCoffeeDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
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
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF5EDFFF)
                    )
                }
                Text(
                    text = "About",
                    fontFamily = Poppins,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Astravel",
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Version $versionName ($versionCode)",
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        color = Color(0xFFB0BEC5)
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Discover places, plan trips, and save your favourites. Astravel is a learning/demo app built with Jetpack Compose.",
                        fontFamily = Poppins,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Credits",
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Built with Jetpack Compose, Material 3, and Supabase.",
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        color = Color(0xFFB0BEC5)
                    )
                }
            }

            // Connect / Links
            Spacer(Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Connect",
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(10.dp))
                    // GitHub
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .padding(vertical = 4.dp)
                            .clickable { openUrl("https://github.com/nawshad85") },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_github),
                            contentDescription = "GitHub",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("GitHub", fontFamily = Poppins, fontSize = 16.sp, color = Color.White)
                            Text("github.com/nawshad85", fontFamily = Poppins, fontSize = 12.sp, color = Color(0xFFB0BEC5))
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    // Facebook
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .padding(vertical = 4.dp)
                            .clickable { openUrl("https://web.facebook.com/sami.nawshad") },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_facebook),
                            contentDescription = "Facebook",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Facebook", fontFamily = Poppins, fontSize = 16.sp, color = Color.White)
                            Text("web.facebook.com/sami.nawshad", fontFamily = Poppins, fontSize = 12.sp, color = Color(0xFFB0BEC5))
                        }
                    }
                }
            }

            // Buy Me a Coffee (placeholder)
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { showCoffeeDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accent)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("☕", fontFamily = Poppins, fontSize = 18.sp, color = Color(0xFF012632))
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Buy Me a Coffee",
                        fontFamily = Poppins,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF012632)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
            val year = Calendar.getInstance().get(Calendar.YEAR)
            Text(
                text = "© $year Astravel",
                fontFamily = Poppins,
                fontSize = 12.sp,
                color = Color(0xFF90A4AE),
                textAlign = TextAlign.Center
            )
        }

        if (showCoffeeDialog) {
            AlertDialog(
                onDismissRequest = { showCoffeeDialog = false },
                title = { Text("Coming soon") },
                text = { Text("Donations are not enabled yet. Stay tuned!") },
                confirmButton = {
                    TextButton(onClick = { showCoffeeDialog = false }) {
                        Text("OK")
                    }
                },
                containerColor = Color(0xFF0A3D62),
                titleContentColor = Color.White,
                textContentColor = Color(0xFFB0BEC5)
            )
        }
    }
}
