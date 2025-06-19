package com.example.kiy.pages.splash

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kiy.R
import androidx.compose.ui.unit.sp

@Composable
fun splashKamus() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F1E8))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.lines),
            contentDescription = "Decorative lines",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopEnd)
                .offset(x = 30.dp, y = -20.dp),// Optional: adjust offset if you want it a bit inset/outset
            tint = Color(0xFF8B6914) // Adjust color as needed
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopEnd)
                .padding(horizontal = 20.dp)
        ) {


            // Header with back arrow and title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF8B6914),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { /* Handle back navigation */ }
                )

                Text(
                    text = "Kamus",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFA89C29),
                    modifier = Modifier
                        .weight(1f),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(24.dp))
            }

            // Just two category buttons with spacing
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF5A623),
                        contentColor = Color.Black
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 8.dp,
                        hoveredElevation = 6.dp,
                        focusedElevation = 6.dp
                    )
                ) {
                    Text(text = "Alfabet", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 8.dp,
                        hoveredElevation = 6.dp,
                        focusedElevation = 6.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF9CB43),
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Angka", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        Icon(
            painter = painterResource(id = R.drawable.lines),
            contentDescription = "Decorative lines",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomStart)
                .offset(x = -50.dp, y = 50.dp)
                .rotate(
                    degrees = 100f // Rotate to match the design
                ),// Optional: adjust offset if you want it a bit inset/outset
            tint = Color(0xFF8B6914) // Adjust color as needed
        )
    }
}


@Preview
@Composable
fun PreviewSplashKamus() {
    splashKamus()
}