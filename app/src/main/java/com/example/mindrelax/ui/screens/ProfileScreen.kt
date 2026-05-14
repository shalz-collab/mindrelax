package com.example.mindrelax.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindrelax.ui.ProfileViewModel
import com.example.mindrelax.ui.Screen
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onNavigate: (String) -> Unit = {}, profileViewModel: ProfileViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = { IconButton(onClick = { onNavigate(Screen.Settings.route) }) { Icon(Icons.Default.Settings, null) } }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    modifier = Modifier.size(100.dp).background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, null, modifier = Modifier.size(60.dp), tint = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(profileViewModel.userName, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                Text("Mindful since Apr 2025", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
                
                Button(
                    onClick = { onNavigate(Screen.EditProfile.route) },
                    modifier = Modifier.padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer, contentColor = MaterialTheme.colorScheme.onSecondaryContainer),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Icon(Icons.Default.Edit, null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Edit Profile")
                }

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
                    ProfileMenuItem(Icons.Default.Person, "Account Settings", onClick = { onNavigate(Screen.Settings.route) })
                    ProfileMenuItem(Icons.Default.Notifications, "Notifications", onClick = { onNavigate(Screen.Settings.route) })
                    ProfileMenuItem(Icons.Default.Lock, "Privacy", onClick = { onNavigate(Screen.Settings.route) })
                    ProfileMenuItem(Icons.Default.Help, "Help & Support", onClick = { /* TODO */ })
                    ProfileMenuItem(Icons.AutoMirrored.Filled.Logout, "Log Out", isLast = true, onClick = { 
                        FirebaseAuth.getInstance().signOut()
                        onNavigate(Screen.Login.route) 
                    })
                }
            }
        }
    }
}

@Composable
fun ProfileStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
        Text(label, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun ProfileMenuItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, isLast: Boolean = false, onClick: () -> Unit = {}) {
    Column(modifier = Modifier.clickable { onClick() }) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, null, modifier = Modifier.size(24.dp), tint = MaterialTheme.colorScheme.outline)
            Spacer(modifier = Modifier.width(16.dp))
            Text(title, fontSize = 16.sp, modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.onSurface)
            Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.outline)
        }
        if (!isLast) {
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
