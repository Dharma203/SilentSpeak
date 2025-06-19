package com.example.kiy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kiy.pages.AlphabetDictionaryContent
import com.example.kiy.pages.CommunityPage2
import com.example.kiy.pages.DashboardPage
import com.example.kiy.pages.LeaderboardPage
import com.example.kiy.pages.auth.LoginPage
import com.example.kiy.pages.auth.SignupPage
import com.example.kiy.pages.profile.ChangePasswordPage
import com.example.kiy.pages.profile.EditProfilePage
import com.example.kiy.pages.profile.ProfilePage
import com.example.kiy.pages.test.QuizPage
import com.example.kiy.pages.video.VideoTutorialPage
import com.example.kiy.ui.theme.KIYTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KIYTheme {
                SilenSpeakApp()
//                LoginPage()
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Community : Screen("community")
    object Practice : Screen("practice")
    object Profile : Screen("profile")
    object AlphabetDictionary : Screen("alphabet_dictionary")
    object Leaderboard : Screen("leaderboard")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Quiz : Screen("quiz")
    object EditProfile : Screen("edit_profile")
    object ChangePassword : Screen("change_password")
    object SIBI : Screen("sibi")
}

@Composable
fun NavGraph(navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()
    val startDestination = if (auth.currentUser != null) {
        Screen.Home.route
    } else {
        Screen.Login.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            DashboardPage(navController = navController)
        }
        composable(Screen.Community.route) {
            CommunityPage2()
        }
        composable(Screen.Practice.route) {
            QuizPage(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfilePage(navController)
        }
        composable(Screen.Leaderboard.route) {
            LeaderboardPage(navController) // Uncomment when LeaderboardPage is implemented
        }
        composable(Screen.AlphabetDictionary.route) {
            AlphabetDictionaryContent()
        }
        composable(Screen.Login.route) {
            LoginPage(onLoginSuccess = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            }
            , navController = navController)
        }
        composable(Screen.Signup.route) {
            SignupPage(onSignupSuccess = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
            , navController = navController)
        }
        composable(Screen.EditProfile.route) {
            EditProfilePage(navController)
        }
        composable(Screen.ChangePassword.route) {
            ChangePasswordPage(navController)
        }
        composable(Screen.SIBI.route) {
            VideoTutorialPage(navController)
        }

    }
}


@Composable
fun SilenSpeakApp(){
    MainScreen(navController = rememberNavController())
}

