package com.example.kiy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

// Screen routes

data class BottomNavItem(
    val title: String,
    val selectedIcon: Int, // Changed to Int for drawable resource ID
    val unselectedIcon: Int, // Changed to Int for drawable resource ID
    val screen: Screen
)


// Bottom navigation items
val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        selectedIcon = R.drawable.ic_home, // Use drawable resource
        unselectedIcon = R.drawable.ic_home, // Use drawable resource (can be different for unselected)
        screen = Screen.Home
    ),
    BottomNavItem(
        title = "Komunitas",
        selectedIcon = R.drawable.ic_group, // Use drawable resource
        unselectedIcon = R.drawable.ic_group, // Use drawable resource (can be different for unselected)
        screen = Screen.Community
    ),
    BottomNavItem(
        title = "Games",
        selectedIcon = R.drawable.ic_leaderboard, // Use drawable resource
        unselectedIcon = R.drawable.ic_leaderboard, // Use drawable resource (can be different for unselected)
        screen = Screen.Leaderboard
    ),
    BottomNavItem(
        title = "Profile",
        selectedIcon = R.drawable.ic_profile, // Use drawable resource
        unselectedIcon = R.drawable.ic_profile, // Use drawable resource (can be different for unselected)
        screen = Screen.Profile
    )
)

@Composable
fun MainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // List of routes where BottomNav should be hidden
    val bottomNavHiddenRoutes = listOf(
        Screen.Login.route,
        Screen.Signup.route,
        Screen.Practice.route,
    )

    Scaffold(
        bottomBar = {
            if (currentRoute !in bottomNavHiddenRoutes) {
                NavigationBar(
                    containerColor = Color(0xFFFFC700),
                    contentColor = Color.Black,
                    tonalElevation = 4.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.screen.route,
                            onClick = {
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(
                                        id = if (currentRoute == item.screen.route) item.selectedIcon else item.unselectedIcon
                                    ),
                                    contentDescription = item.title,
                                    tint = if (currentRoute == item.screen.route) Color.Black else Color.Black.copy(alpha = 0.6f),
                                    modifier = Modifier.size(28.dp)
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,
                                unselectedIconColor = Color.Black.copy(alpha = 0.6f),
                                selectedTextColor = Color.Black,
                                unselectedTextColor = Color.Black.copy(alpha = 0.6f),
                                indicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavGraph(navController = navController)
        }
    }
}