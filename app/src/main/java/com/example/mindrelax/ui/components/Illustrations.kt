package com.example.mindrelax.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp

@Composable
fun MindRelaxLogo(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        // Central Leaf
        Leaf(Modifier.size(40.dp, 70.dp), Color(0xFF2E7D32))
        
        // Left Leaves
        Leaf(Modifier.size(35.dp, 60.dp).offset(x = (-25).dp, y = 10.dp).rotate(-30f), Color(0xFF4CAF50))
        Leaf(Modifier.size(30.dp, 50.dp).offset(x = (-45).dp, y = 25.dp).rotate(-60f), Color(0xFF81C784))
        
        // Right Leaves
        Leaf(Modifier.size(35.dp, 60.dp).offset(x = 25.dp, y = 10.dp).rotate(30f), Color(0xFF4CAF50))
        Leaf(Modifier.size(30.dp, 50.dp).offset(x = 45.dp, y = 25.dp).rotate(60f), Color(0xFF81C784))
    }
}

@Composable
fun Leaf(modifier: Modifier = Modifier, color: Color) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(topStartPercent = 100, bottomEndPercent = 100))
            .background(color)
    )
}

@Composable
fun MeditationIllustration(modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.Bottom) {
        StylizedPerson(Color(0xFF81C784))
        StylizedPerson(Color(0xFF64B5F6))
    }
}

@Composable
fun StylizedPerson(color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Head
        Box(modifier = Modifier.size(25.dp).background(Color(0xFFFFD180), CircleShape))
        Spacer(modifier = Modifier.height(4.dp))
        // Body (Seated)
        Box(
            modifier = Modifier
                .size(60.dp, 40.dp)
                .background(color, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        )
        // Legs (Crossed)
        Row {
            Box(modifier = Modifier.size(40.dp, 15.dp).background(color, RoundedCornerShape(10.dp)))
            Spacer(modifier = Modifier.width((-10).dp))
            Box(modifier = Modifier.size(40.dp, 15.dp).background(color, RoundedCornerShape(10.dp)))
        }
    }
}

@Composable
fun CutePlantIllustration(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        // Leaves
        Box(contentAlignment = Alignment.BottomCenter) {
            Leaf(Modifier.size(30.dp, 50.dp).rotate(-20f).offset(x = (-20).dp), Color(0xFF4CAF50))
            Leaf(Modifier.size(30.dp, 50.dp).rotate(20f).offset(x = 20.dp), Color(0xFF2E7D32))
            Leaf(Modifier.size(25.dp, 45.dp).offset(y = (-10).dp), Color(0xFF81C784))
        }
        
        // Pot
        Box(
            modifier = Modifier
                .size(80.dp, 60.dp)
                .background(Color(0xFFBDBDBD), RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
            contentAlignment = Alignment.Center
        ) {
            // Face
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    Box(modifier = Modifier.size(6.dp).background(Color.Black, CircleShape))
                    Box(modifier = Modifier.size(6.dp).background(Color.Black, CircleShape))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Canvas(modifier = Modifier.size(20.dp, 10.dp)) {
                    drawArc(
                        color = Color.Black,
                        startAngle = 0f,
                        sweepAngle = 180f,
                        useCenter = false,
                        style = Fill
                    )
                }
            }
        }
    }
}

@Composable
fun LandscapeIllustration(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.clip(RoundedCornerShape(12.dp))) {
        // Sky
        drawRect(Color(0xFFE3F2FD))
        // Mountains
        val path = Path().apply {
            moveTo(0f, size.height)
            lineTo(size.width * 0.3f, size.height * 0.4f)
            lineTo(size.width * 0.6f, size.height)
            close()
        }
        drawPath(path, Color(0xFF90CAF9))
        
        val path2 = Path().apply {
            moveTo(size.width * 0.4f, size.height)
            lineTo(size.width * 0.75f, size.height * 0.5f)
            lineTo(size.width, size.height)
            close()
        }
        drawPath(path2, Color(0xFF64B5F6))
        
        // Water
        drawRect(
            color = Color(0xFF2196F3),
            topLeft = Offset(0f, size.height * 0.8f),
            size = Size(size.width, size.height * 0.2f)
        )
    }
}
