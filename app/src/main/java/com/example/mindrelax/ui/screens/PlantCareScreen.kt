package com.example.mindrelax.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.mindrelax.ui.components.CutePlantIllustration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantCareScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Plant", fontWeight = FontWeight.Bold) },
                actions = { IconButton(onClick = {}) { Icon(Icons.Default.Edit, null) } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Green Buddy", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("Level 3", color = Color.Gray)

            Spacer(modifier = Modifier.height(40.dp))

            // Placeholder for plant illustration
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .background(Color(0xFFE8F5E9), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                CutePlantIllustration()
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Plant Health", fontWeight = FontWeight.Bold)
                    Text("70%", color = Color(0xFF4CAF50))
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = 0.7f,
                    modifier = Modifier.fillMaxWidth().height(12.dp),
                    color = Color(0xFF4CAF50),
                    trackColor = Color(0xFFE8F5E9)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                PlantStat("Water", "90%", Icons.Default.WaterDrop, Color(0xFFE3F2FD))
                PlantStat("Sunlight", "80%", Icons.Default.WbSunny, Color(0xFFFFF9C4))
                PlantStat("Happiness", "95%", Icons.Default.Favorite, Color(0xFFFBE9E7))
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(56.dp).background(Color(0xFFF5F5F5), CircleShape)
                ) { Icon(Icons.Default.Home, null) }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(56.dp).background(Color(0xFFF5F5F5), CircleShape)
                ) { Icon(Icons.Default.LocalFlorist, null) }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(56.dp).background(Color(0xFFF5F5F5), CircleShape)
                ) { Icon(Icons.Default.ShoppingBag, null) }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(56.dp).background(Color(0xFFF5F5F5), CircleShape)
                ) { Icon(Icons.Default.Person, null) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantCareScreenPreview() {
    PlantCareScreen()
}

@Composable
fun PlantStat(label: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(50.dp).background(color, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = Color.DarkGray, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, fontSize = 12.sp, color = Color.Gray)
        Text(value, fontWeight = FontWeight.Bold)
    }
}
