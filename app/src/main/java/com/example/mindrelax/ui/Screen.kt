package com.example.mindrelax.ui

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object MoodTracker : Screen("mood_tracker")
    object Journal : Screen("journal")
    object Meditation : Screen("meditation")
    object Chatbot : Screen("chatbot")
    object Community : Screen("community")
    object Rewards : Screen("rewards")
    object PlantCare : Screen("plant_care")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object MusicPlayer : Screen("music_player")
    object EditProfile : Screen("edit_profile")
    object Games : Screen("games")
}
