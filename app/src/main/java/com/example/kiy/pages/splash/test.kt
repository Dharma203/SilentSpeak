package com.example.kiy.pages.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kiy.R
import androidx.compose.ui.unit.sp

@Composable
fun SplashTest() {
    val creamBackground = Color(0xFFFCF8EB)
    val orangeButton = Color(0xFFF5A623)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(creamBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.3f))

        // Title text
        Text(
            text = "\"Ayo Mulai Tes\"",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.weight(0.4f))

        // Illustration
        Image(
            painter = painterResource(id = R.drawable.start_test_image),
            contentDescription = "Start test illustration",
            modifier = Modifier
                .size(300.dp)
                .padding(horizontal = 16.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.weight(0.3f))

        // Mulai button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = orangeButton),
            shape = RoundedCornerShape(28.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp,
                hoveredElevation = 6.dp,
                focusedElevation = 6.dp,
            )
        ) {
            Text(
                text = "Mulai",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(160.dp))
    }
}

@Preview
@Composable
fun SplashTestPreview() {
    SplashTest()
}