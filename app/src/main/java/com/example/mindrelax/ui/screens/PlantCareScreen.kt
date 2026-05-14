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
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Green Buddy", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
            Text("Level 3", color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .size(220.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                CutePlantIllustration()
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Plant Health", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                    Text("70%", color = MaterialTheme.colorScheme.primary)
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = 0.7f,
                    modifier = Modifier.fillMaxWidth().height(12.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                PlantStat("Water", "90%", Icons.Default.WaterDrop, MaterialTheme.colorScheme.primaryContainer)
                PlantStat("Sunlight", "80%", Icons.Default.WbSunny, MaterialTheme.colorScheme.secondaryContainer)
                PlantStat("Happiness", "95%", Icons.Default.Favorite, MaterialTheme.colorScheme.tertiaryContainer)
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                IconButton(onClick = {}, modifier = Modifier.size(56.dp).background(MaterialTheme.colorScheme.surface, CircleShape)) { Icon(Icons.Default.Home, null, tint = MaterialTheme.colorScheme.primary) }
                IconButton(onClick = {}, modifier = Modifier.size(56.dp).background(MaterialTheme.colorScheme.primary, CircleShape)) { Icon(Icons.Default.LocalFlorist, null, tint = MaterialTheme.colorScheme.onPrimary) }
                IconButton(onClick = {}, modifier = Modifier.size(56.dp).background(MaterialTheme.colorScheme.surface, CircleShape)) { Icon(Icons.Default.ShoppingBag, null, tint = MaterialTheme.colorScheme.primary) }
                IconButton(onClick = {}, modifier = Modifier.size(56.dp).background(MaterialTheme.colorScheme.surface, CircleShape)) { Icon(Icons.Default.Person, null, tint = MaterialTheme.colorScheme.primary) }
            }
        }
    }
}

@Composable
fun PlantStat(label: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(50.dp).background(color.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
    }
}

@Preview(showBackground = true)
@Composable
fun PlantCareScreenPreview() {
    PlantCareScreen()
}
