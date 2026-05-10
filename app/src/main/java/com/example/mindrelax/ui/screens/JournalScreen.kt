package com.example.mindrelax.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Journal", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = {}) { Icon(Icons.Default.Search, null) }
                    IconButton(onClick = {}) { Icon(Icons.Default.FilterList, null) }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = Color(0xFF2E7D32),
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("All", "Favorites", "Locked").forEach { tab ->
                    val isSelected = tab == "All"
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Color(0xFF2E7D32) else Color.Transparent,
                            contentColor = if (isSelected) Color.White else Color.Gray
                        ),
                        border = if (!isSelected) ButtonDefaults.outlinedButtonBorder else null,
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        Text(tab)
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { Text("Today", fontWeight = FontWeight.Bold, color = Color.Gray) }
                item {
                    JournalEntryItem(
                        title = "Grateful for today",
                        time = "10:30 AM",
                        content = "Today was a beautiful day. Feeling grateful for...",
                        icon = "😊"
                    )
                }

                item { Text("Yesterday", fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier.padding(top = 8.dp)) }
                item {
                    JournalEntryItem(
                        title = "A productive day",
                        time = "May 25, 9:15 PM",
                        content = "Finished my tasks and learned something new.",
                        icon = "💪"
                    )
                }

                item { Text("May 24", fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier.padding(top = 8.dp)) }
                item {
                    JournalEntryItem(
                        title = "Feeling a bit anxious",
                        time = "May 24, 8:40 PM",
                        content = "It's okay to not be okay.",
                        icon = "😟"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JournalScreenPreview() {
    JournalScreen()
}

@Composable
fun JournalEntryItem(title: String, time: String, content: String, icon: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(icon, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(title, fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.BookmarkBorder, null, tint = Color.Gray, modifier = Modifier.size(18.dp))
                }
                Text(time, fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(content, fontSize = 14.sp, color = Color.DarkGray)
            }
        }
    }
}
