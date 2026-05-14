package com.example.mindrelax.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindrelax.ui.components.LandscapeIllustration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen() {
    val context = LocalContext.current
    
    fun openWhatsApp() {
        val phoneNumber = "+1234567890" 
        val url = "https://api.whatsapp.com/send?phone=$phoneNumber"
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Search, null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Search in community", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
                    }
                },
                actions = {
                    IconButton(onClick = { openWhatsApp() }) {
                        Icon(Icons.Default.Chat, contentDescription = "WhatsApp", tint = Color(0xFF25D366))
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { openWhatsApp() },
                containerColor = Color(0xFF25D366),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Groups, contentDescription = "Join Community")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).background(MaterialTheme.colorScheme.background)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("For You", "Following", "New").forEach { tab ->
                    val isSelected = tab == "For You"
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = tab,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        if (isSelected) {
                            Box(modifier = Modifier.width(40.dp).height(2.dp).background(MaterialTheme.colorScheme.primary))
                        }
                    }
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    CommunityPostItem(
                        userName = "Sam Lee",
                        time = "2h ago",
                        content = "Just finished my meditation session. Feeling so calm and relaxed. 🧘‍♂️",
                        likes = 24,
                        comments = 12
                    )
                }
                item {
                    CommunityPostItem(
                        userName = "Jamie Roy",
                        time = "5h ago",
                        content = "Remember: it's okay to take small steps every day. You matter! ❤️",
                        likes = 56,
                        comments = 8
                    )
                }
            }
        }
    }
}

@Composable
fun CommunityPostItem(userName: String, time: String, content: String, likes: Int, comments: Int) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(40.dp).background(MaterialTheme.colorScheme.primaryContainer, CircleShape), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, null, tint = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(userName, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Text(time, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {}) { Icon(Icons.Default.MoreHoriz, null, tint = MaterialTheme.colorScheme.outline) }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(content, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
            
            Spacer(modifier = Modifier.height(12.dp))
            LandscapeIllustration(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.FavoriteBorder, null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.outline)
                Text(" $likes", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.width(16.dp))
                Icon(Icons.Default.ChatBubbleOutline, null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.outline)
                Text(" $comments", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.BookmarkBorder, null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.outline)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommunityScreenPreview() {
    CommunityScreen()
}
