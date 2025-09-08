package com.example.astravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.astravel.pages.Favourites
import com.example.astravel.pages.Home
import com.example.astravel.pages.Login
import com.example.astravel.pages.Profile
import com.example.astravel.pages.SignUp
import com.example.astravel.pages.Welcome
import com.example.astravel.places.Bandarban
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
        composable("home") { Home(navController, authViewModel) }
        composable("favourites") { Favourites(navController, authViewModel) }
        composable("profile") { Profile(navController, authViewModel) }
        composable("bandarban") { Bandarban(navController, authViewModel) }
    }
}