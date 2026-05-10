package com.example.mindrelax.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MeditationScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5), RoundedCornerShape(25.dp))
                .padding(4.dp)
        ) {
            Button(
                onClick = {},
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                shape = RoundedCornerShape(21.dp)
            ) {
                Text("Meditation")
            }
            TextButton(
                onClick = {},
                modifier = Modifier.weight(1f)
            ) {
                Text("Music", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Categories", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("View all", color = Color(0xFF4CAF50), fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        val categories = listOf("Sleep", "Anxiety", "Focus", "Stress", "Morning")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(categories) { cat ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.size(60.dp).background(Color(0xFFFFF3E0), CircleShape), contentAlignment = Alignment.Center) {
                        // Icon placeholder
                    }
                    Text(cat, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Popular Sessions", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("View all", color = Color(0xFF4CAF50), fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        val sessions = listOf(
            Session("Calm Mind", "10 min", Color(0xFFE3F2FD)),
            Session("Deep Sleep", "20 min", Color(0xFFEDE7F6)),
            Session("Morning Motivation", "5 min", Color(0xFFFFF9C4))
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(sessions) { session ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(60.dp).background(session.color, RoundedCornerShape(12.dp)))
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(session.title, fontWeight = FontWeight.Bold)
                            Text(session.duration, fontSize = 12.sp, color = Color.Gray)
                        }
                        IconButton(onClick = {}, modifier = Modifier.background(Color(0xFFF5F5F5), CircleShape)) {
                            Icon(Icons.Default.PlayArrow, null, tint = Color(0xFF4CAF50))
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

data class Session(val title: String, val duration: String, val color: Color)
