package com.example.mindrelax.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Rewards", fontWeight = FontWeight.Bold) })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1B5E20))
                ) {
                    Row(
                        modifier = Modifier.padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.EmojiEvents,
                            null,
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Column {
                            Text("850", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            Text("Total Points", fontSize = 14.sp, color = Color.White.copy(alpha = 0.7f))
                        }
                    }
                    
                    LinearProgressIndicator(
                        progress = 0.7f,
                        modifier = Modifier.fillMaxWidth().height(8.dp),
                        color = Color(0xFF81C784),
                        trackColor = Color(0xFF2E7D32)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Level 4", color = Color.White, fontSize = 12.sp)
                        Text("Next level in 150 XP", color = Color.White, fontSize = 12.sp)
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Achievements", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("View all", color = Color(0xFF4CAF50), fontSize = 12.sp)
                }
            }

            items(
                listOf(
                    Achievement("Meditation Master", "Complete 10 sessions", Icons.Default.Star, Color(0xFFE8F5E9)),
                    Achievement("Journal Streak", "Write 7 journal entries", Icons.Default.CheckCircle, Color(0xFFE3F2FD)),
                    Achievement("Helping Hand", "Help 5 community members", Icons.Default.EmojiEvents, Color(0xFFFFF3E0))
                )
            ) { achievement ->
                AchievementItem(achievement)
            }
        }
    }
}

@Composable
fun AchievementItem(achievement: Achievement) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(50.dp).background(achievement.color, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(achievement.icon, null, tint = Color(0xFF4CAF50))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(achievement.title, fontWeight = FontWeight.Bold)
                Text(achievement.description, fontSize = 12.sp, color = Color.Gray)
            }
            Icon(Icons.Default.Star, null, tint = Color(0xFFFFD700), modifier = Modifier.size(20.dp))
        }
    }
}

data class Achievement(val title: String, val description: String, val icon: ImageVector, val color: Color)

@Preview(showBackground = true)
@Composable
fun RewardsScreenPreview() {
    RewardsScreen()
}
