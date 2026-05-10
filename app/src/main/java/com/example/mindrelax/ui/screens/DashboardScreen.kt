package com.example.mindrelax.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindrelax.ui.components.MindRelaxLogo

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Hi, Alex 👋", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Take a deep breath and\nlet's make today better.", color = Color.Gray)
            }
            Row {
                IconButton(onClick = {}) { Icon(Icons.Default.Notifications, null) }
                Box(
                    modifier = Modifier.size(40.dp).background(Color(0xFFE8F5E9), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, null, tint = Color(0xFF4CAF50))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Wellness Score", fontSize = 12.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.size(50.dp).background(Color.White, CircleShape).padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("82", fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Good", fontWeight = FontWeight.Bold)
                    }
                }
            }

            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Streak", fontSize = 12.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Whatshot, contentDescription = null, tint = Color(0xFFFF9800))
                        Text(" 7", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Text(" Days", fontSize = 14.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Today's Focus", fontWeight = FontWeight.Bold)
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Spa, null, tint = Color(0xFF4CAF50))
                Spacer(modifier = Modifier.width(16.dp))
                Text("Be kind to your mind", fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Quick Actions", fontWeight = FontWeight.Bold)
        val actions = listOf(
            ActionItem("Journal", Icons.Default.Book, Color(0xFFFFF3E0)),
            ActionItem("Meditate", Icons.Default.SelfImprovement, Color(0xFFE3F2FD)),
            ActionItem("Music", Icons.Default.MusicNote, Color(0xFFF3E5F5)),
            ActionItem("Chatbot", Icons.AutoMirrored.Filled.Chat, Color(0xFFE8F5E9)),
            ActionItem("Games", Icons.Default.Gamepad, Color(0xFFFFFDE7)),
            ActionItem("Plant", Icons.Default.LocalFlorist, Color(0xFFE0F2F1)),
            ActionItem("Community", Icons.Default.Groups, Color(0xFFFBE9E7)),
            ActionItem("More", Icons.Default.MoreHoriz, Color(0xFFF5F5F5))
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(actions) { action ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier.size(50.dp).background(action.color, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(action.icon, contentDescription = null, tint = Color.DarkGray)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(action.title, fontSize = 10.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}

data class ActionItem(val title: String, val icon: ImageVector, val color: Color)
