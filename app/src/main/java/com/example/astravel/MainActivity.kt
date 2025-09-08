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
import com.example.astravel.categories.Beach
import com.example.astravel.categories.Camping
import com.example.astravel.categories.Forest
import com.example.astravel.categories.Historical
import com.example.astravel.categories.Mountain
import com.example.astravel.categories.TeaGarden
import com.example.astravel.pages.Favourites
import com.example.astravel.pages.Home
import com.example.astravel.pages.Login
import com.example.astravel.pages.Profile
import com.example.astravel.pages.SignUp
import com.example.astravel.pages.Welcome
import com.example.astravel.places.Bandarban
import com.example.astravel.places.Coxs
import com.example.astravel.places.Jaflang
import com.example.astravel.places.Kuakata
import com.example.astravel.places.Lalakhal
import com.example.astravel.places.Mahasthanghargh
import com.example.astravel.places.Rangamati
import com.example.astravel.places.Ratargul
import com.example.astravel.places.Sajek
import com.example.astravel.places.Sixtydome
import com.example.astravel.places.Sreemangal
import com.example.astravel.places.Sundarban
import com.example.astravel.ui.theme.AstravelTheme
import kotlin.random.Random

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
    NavHost(navController = navController, startDestination = "home") {
        composable("welcome") { Welcome(navController, authViewModel) }
        composable("login") { Login(navController, authViewModel) }
        composable("signup") { SignUp(navController, authViewModel) }
        composable("home") { Home(navController, authViewModel) }
        composable("favourites") { Favourites(navController, authViewModel) }
        composable("profile") { Profile(navController, authViewModel) }
        composable("bandarban") { Bandarban(navController, authViewModel) }
        composable("coxs") { Coxs(navController, authViewModel) }
        composable("jaflang") { Jaflang(navController, authViewModel) }
        composable("kuakata") { Kuakata(navController, authViewModel) }
        composable("lalakhal") { Lalakhal(navController, authViewModel) }
        composable("mahasthanghargh") { Mahasthanghargh(navController, authViewModel) }
        composable("rangamati") { Rangamati(navController, authViewModel) }
        composable("ratargul") { Ratargul(navController, authViewModel) }
        composable("sajek") { Sajek(navController, authViewModel) }
        composable("sixtydome") { Sixtydome(navController, authViewModel) }
        composable("sreemangal") { Sreemangal(navController, authViewModel) }
        composable("sundarban") { Sundarban(navController, authViewModel) }
        composable("beach") { Beach(navController, authViewModel) }
        composable("camping") { Camping(navController, authViewModel) }
        composable("forest") { Forest(navController, authViewModel) }
        composable("historical") { Historical(navController, authViewModel) }
        composable("mountain") { Mountain(navController, authViewModel) }
        composable("teagarden") { TeaGarden(navController, authViewModel) }

    }
}