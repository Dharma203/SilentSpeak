package com.example.silentspeak

import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.silentspeak.pages.HomePage
import com.example.silentspeak.pages.LeaderboardPage
import com.example.silentspeak.ui.theme.SilentSpeakTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SilentSpeakTheme {
//                val navController = rememberNavController()
//                SilentSpeakNavGraph(navController = navController)
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
//    object Settings : Screen("settings")
//    object Login : Screen("login")
//    object Register : Screen("register")
    object LeaderBoard : Screen("leaderboard")
}
//
@Composable
fun SilentSpeakNavGraph(navController: NavHostController) {
////    val currentuser = FirebaseAuth.getInstance().currentUser
////    val startdestination = if (currentuser != null) Screen.Home.route else Screen.Login.route
    val startDestination = Screen.Home.route
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.Home.route) { HomePage() }
        composable(Screen.LeaderBoard.route) { LeaderboardPage() }
    }
}

@Composable
fun App(){
    MainScreen()

}