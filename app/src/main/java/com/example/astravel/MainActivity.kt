package com.example.astravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.astravel.pages.Login
import com.example.astravel.pages.Profile
import com.example.astravel.pages.SignUp
import com.example.astravel.pages.Welcome
import com.example.astravel.ui.theme.AstravelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AstravelTheme {
                NavigationComponent()
            }
        }
    }
}

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    val authViewModel: SupabaseAuthViewModel = viewModel()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { Welcome(navController, authViewModel) }
        composable("login") { Login(navController, authViewModel) }
        composable("signup") { SignUp(navController, authViewModel) }
        composable("profile") { Profile(navController, authViewModel) }
    }
}