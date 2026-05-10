package com.example.mindrelax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mindrelax.ui.Screen
import com.example.mindrelax.ui.screens.*
import com.example.mindrelax.ui.theme.MINDRELAXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MINDRELAXTheme {
                MindRelaxApp()
            }
        }
    }
}

@Composable
fun MindRelaxApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = currentDestination?.route in listOf(
        Screen.Dashboard.route,
        Screen.Journal.route,
        Screen.Meditation.route,
        Screen.Community.route,
        Screen.Profile.route,
        Screen.Chatbot.route,
        Screen.Rewards.route,
        Screen.PlantCare.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(containerColor = Color.White) {
                    val items = listOf(
                        NavigationItem("Home", Screen.Dashboard.route, Icons.Default.Home),
                        NavigationItem("Journal", Screen.Journal.route, Icons.Default.Book),
                        NavigationItem("Tools", Screen.Meditation.route, Icons.Default.SelfImprovement),
                        NavigationItem("Community", Screen.Community.route, Icons.Default.Groups),
                        NavigationItem("Profile", Screen.Profile.route, Icons.Default.Person)
                    )
                    items.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF2E7D32),
                                selectedTextColor = Color(0xFF2E7D32),
                                indicatorColor = Color(0xFFE8F5E9)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(onTimeout = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Welcome.route) {
                WelcomeScreen(onNext = {
                    navController.navigate(Screen.Login.route)
                })
            }
            composable(Screen.Login.route) {
                LoginScreen(onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Dashboard.route) { DashboardScreen() }
            composable(Screen.Journal.route) { JournalScreen() }
            composable(Screen.Meditation.route) { MeditationScreen() }
            composable(Screen.Community.route) { CommunityScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.Chatbot.route) { ChatScreen() }
            composable(Screen.MoodTracker.route) { MoodTrackerScreen(onBack = { navController.popBackStack() }) }
            composable(Screen.Rewards.route) { RewardsScreen() }
            composable(Screen.PlantCare.route) { PlantCareScreen() }
        }
    }
}

data class NavigationItem(val title: String, val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)
