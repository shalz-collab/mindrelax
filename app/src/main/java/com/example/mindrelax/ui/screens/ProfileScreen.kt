package com.example.mindrelax.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = { IconButton(onClick = {}) { Icon(Icons.Default.Settings, null) } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    modifier = Modifier.size(100.dp).background(Color(0xFFE8F5E9), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, null, modifier = Modifier.size(60.dp), tint = Color(0xFF4CAF50))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Alex Johnson", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Mindful since Apr 2025", color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    ProfileStat("24", "Journals")
                    ProfileStat("7", "Streak")
                    ProfileStat("850", "Points")
                }
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    ProfileMenuItem(Icons.Default.Person, "Account Settings")
                    ProfileMenuItem(Icons.Default.Notifications, "Notifications")
                    ProfileMenuItem(Icons.Default.Lock, "Privacy")
                    ProfileMenuItem(Icons.Default.Help, "Help & Support")
                    ProfileMenuItem(Icons.AutoMirrored.Filled.Logout, "Log Out", isLast = true)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

@Composable
fun ProfileStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun ProfileMenuItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, isLast: Boolean = false) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, null, modifier = Modifier.size(24.dp), tint = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Text(title, fontSize = 16.sp, modifier = Modifier.weight(1f))
            Icon(Icons.Default.ChevronRight, null, tint = Color.Gray)
        }
        if (!isLast) {
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
        }
    }
}
