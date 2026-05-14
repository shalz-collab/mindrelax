package com.example.mindrelax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.example.mindrelax.ui.ProfileViewModel
import com.example.mindrelax.ui.Screen
import com.example.mindrelax.ui.screens.*
import com.example.mindrelax.ui.theme.MINDRELAXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            var themeMode by remember { mutableStateOf("System Default") }
            val isDarkTheme = when (themeMode) {
                "Light" -> false
                "Dark" -> true
                else -> isSystemInDarkTheme()
            }

            MINDRELAXTheme(darkTheme = isDarkTheme) {
                MindRelaxApp(
                    themeMode = themeMode,
                    onThemeChange = { themeMode = it }
                )
            }
        }
    }
}

@Composable
fun MindRelaxApp(
    themeMode: String,
    onThemeChange: (String) -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val auth = FirebaseAuth.getInstance()
    val profileViewModel: ProfileViewModel = viewModel()

    val showBottomBar = currentDestination?.route in listOf(
        Screen.Dashboard.route,
        Screen.Journal.route,
        Screen.Meditation.route,
        Screen.Community.route,
        Screen.Profile.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ) {
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
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
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
                SplashScreen(onTimeout = { isLoggedIn ->
                    if (isLoggedIn) {
                        profileViewModel.fetchUserProfile()
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screen.Welcome.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
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
                    profileViewModel.fetchUserProfile()
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Dashboard.route) { 
                DashboardScreen(
                    onNavigate = { route -> navController.navigate(route) },
                    profileViewModel = profileViewModel
                ) 
            }
            composable(Screen.Journal.route) { JournalScreen() }
            composable(Screen.Meditation.route) { 
                MeditationScreen(onNavigate = { route -> navController.navigate(route) }) 
            }
            composable(Screen.Community.route) { CommunityScreen() }
            composable(Screen.Profile.route) { 
                ProfileScreen(
                    onNavigate = { route -> navController.navigate(route) },
                    profileViewModel = profileViewModel
                ) 
            }
            composable(Screen.Chatbot.route) { ChatScreen() }
            composable(Screen.MoodTracker.route) { MoodTrackerScreen(onBack = { navController.popBackStack() }) }
            composable(Screen.Rewards.route) { RewardsScreen() }
            composable(Screen.PlantCare.route) { PlantCareScreen() }
            composable(Screen.Settings.route) { 
                SettingsScreen(
                    selectedTheme = themeMode,
                    onThemeChange = onThemeChange,
                    onBack = { navController.popBackStack() }
                ) 
            }
            composable(Screen.MusicPlayer.route) { MusicPlayerScreen(onBack = { navController.popBackStack() }) }
            composable(Screen.EditProfile.route) { 
                EditProfileScreen(
                    onBack = { navController.popBackStack() },
                    profileViewModel = profileViewModel
                ) 
            }
            composable(Screen.Games.route) { GamesScreen(onBack = { navController.popBackStack() }) }
        }
    }
}

data class NavigationItem(val title: String, val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)
