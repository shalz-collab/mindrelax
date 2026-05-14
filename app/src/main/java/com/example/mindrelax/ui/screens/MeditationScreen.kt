package com.example.mindrelax.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindrelax.ui.Screen

@Composable
fun MeditationScreen(onNavigate: (String) -> Unit = {}) {
    var selectedTab by remember { mutableStateOf("Meditation") }
    val context = LocalContext.current

    fun openSpotify(playlistUri: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(playlistUri)
        intent.putExtra(Intent.EXTRA_REFERRER, Uri.parse("android-app://" + context.packageName))
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            // Spotify not installed, open web link
            val webUrl = if (playlistUri.startsWith("spotify:playlist:")) {
                "https://open.spotify.com/playlist/" + playlistUri.substringAfter("playlist:")
            } else {
                "https://open.spotify.com"
            }
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
            context.startActivity(webIntent)
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(25.dp))
                .padding(4.dp)
        ) {
            Button(
                onClick = { selectedTab = "Meditation" },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTab == "Meditation") MaterialTheme.colorScheme.primary else Color.Transparent,
                    contentColor = if (selectedTab == "Meditation") MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                ),
                shape = RoundedCornerShape(21.dp),
                elevation = null
            ) {
                Text("Meditation")
            }
            Button(
                onClick = { selectedTab = "Music" },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedTab == "Music") MaterialTheme.colorScheme.primary else Color.Transparent,
                    contentColor = if (selectedTab == "Music") MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                ),
                shape = RoundedCornerShape(21.dp),
                elevation = null
            ) {
                Text("Music")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Categories", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground)
            Text("View all", color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        val categories = listOf("Sleep", "Anxiety", "Focus", "Stress", "Morning")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(categories) { cat ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.size(60.dp).background(MaterialTheme.colorScheme.primaryContainer, CircleShape), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.PlayArrow, null, tint = MaterialTheme.colorScheme.onPrimaryContainer, modifier = Modifier.size(24.dp))
                    }
                    Text(cat, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(if (selectedTab == "Meditation") "Popular Sessions" else "Relaxing Tracks", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground)
            Text("View all", color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        val items = if (selectedTab == "Meditation") {
            listOf(
                Session("Calm Mind", "10 min", MaterialTheme.colorScheme.primaryContainer, "spotify:playlist:37i9dQZF1DWZqd5YICuS9s"),
                Session("Deep Sleep", "20 min", MaterialTheme.colorScheme.secondaryContainer, "spotify:playlist:37i9dQZF1DWZdZovm98S3Q"),
                Session("Morning Motivation", "5 min", MaterialTheme.colorScheme.tertiaryContainer, "spotify:playlist:37i9dQZF1DX8U70A7pYI92")
            )
        } else {
            listOf(
                Session("Ocean Waves", "15 min", MaterialTheme.colorScheme.primaryContainer, "spotify:playlist:37i9dQZF1DX4PP3R6Ytu8v"),
                Session("Forest Rain", "30 min", MaterialTheme.colorScheme.secondaryContainer, "spotify:playlist:37i9dQZF1DWXLeA8hPj9yF"),
                Session("Deep Space", "45 min", MaterialTheme.colorScheme.tertiaryContainer, "spotify:playlist:37i9dQZF1DX1n9whBbri96")
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { session ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { openSpotify(session.spotifyUri) },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(60.dp).background(session.color.copy(alpha = 0.5f), RoundedCornerShape(12.dp)))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(session.title, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                            Text(session.duration, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        IconButton(
                            onClick = { openSpotify(session.spotifyUri) }, 
                            modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                        ) {
                            Icon(Icons.Default.PlayArrow, null, tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeditationScreenPreview() {
    MeditationScreen()
}

data class Session(val title: String, val duration: String, val color: Color, val spotifyUri: String)
