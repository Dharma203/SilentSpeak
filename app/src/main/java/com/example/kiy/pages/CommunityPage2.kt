package com.example.kiy.pages

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*

@Composable
fun CommunityPage2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF9F4E9)) // beige background
            .padding(bottom = 64.dp) // leave space for bottom nav if any
    ) {
        // Top header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFF9C928)) // yellow top bar
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .padding(vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(28.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Komunitas",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(2f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f)) // to center title
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // "Cari Teman" label
        Text(
            text = "Cari Teman",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Search bar
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(40.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF9A8B2D)), // olive green background
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.padding(start = 12.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Cari Temanmu",
                color = Color(0xFFE5E1B5), // lighter text color
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Horizontal scroll row with circular avatars (simplified avatars)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(3) { index ->
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Black, CircleShape)
                        .background(Color(0xFFFFDBB5)), // peach color for face
                    contentAlignment = Alignment.Center
                ) {
                    // Simple face drawing
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val center = Offset(size.width / 2, size.height / 2)
                        val radius = size.minDimension / 2

                        // Face background
                        drawCircle(
                            color = androidx.compose.ui.graphics.Color(0xFFFFDBB5),
                            radius = radius * 0.9f,
                            center = center
                        )

                        // Eyes
                        drawCircle(
                            color = androidx.compose.ui.graphics.Color.Black,
                            radius = 3.dp.toPx(),
                            center = Offset(center.x - 12.dp.toPx(), center.y - 8.dp.toPx())
                        )
                        drawCircle(
                            color = androidx.compose.ui.graphics.Color.Black,
                            radius = 3.dp.toPx(),
                            center = Offset(center.x + 12.dp.toPx(), center.y - 8.dp.toPx())
                        )

                        // Mouth
                        drawArc(
                            color = androidx.compose.ui.graphics.Color.Black,
                            startAngle = 0f,
                            sweepAngle = 180f,
                            useCenter = false,
                            topLeft = Offset(center.x - 8.dp.toPx(), center.y + 2.dp.toPx()),
                            size = Size(16.dp.toPx(), 12.dp.toPx()),
                            style = Stroke(width = 2.dp.toPx())
                        )

                        // Cheeks (different for each avatar)
                        when (index) {
                            0 -> {
                                // Pink cheeks
                                drawCircle(
                                    color = androidx.compose.ui.graphics.Color(0xFFFF9999),
                                    radius = 6.dp.toPx(),
                                    center = Offset(center.x - 18.dp.toPx(), center.y + 2.dp.toPx())
                                )
                                drawCircle(
                                    color = androidx.compose.ui.graphics.Color(0xFFFF9999),
                                    radius = 6.dp.toPx(),
                                    center = Offset(center.x + 18.dp.toPx(), center.y + 2.dp.toPx())
                                )
                            }
                            1 -> {
                                // Small dots on cheeks
                                drawCircle(
                                    color = androidx.compose.ui.graphics.Color.Black,
                                    radius = 1.5.dp.toPx(),
                                    center = Offset(center.x - 15.dp.toPx(), center.y + 5.dp.toPx())
                                )
                                drawCircle(
                                    color = androidx.compose.ui.graphics.Color.Black,
                                    radius = 1.5.dp.toPx(),
                                    center = Offset(center.x + 15.dp.toPx(), center.y + 5.dp.toPx())
                                )
                            }
                            2 -> {
                                // Different expression - winking
                                drawLine(
                                    color = androidx.compose.ui.graphics.Color.Black,
                                    start = Offset(center.x - 15.dp.toPx(), center.y - 8.dp.toPx()),
                                    end = Offset(center.x - 9.dp.toPx(), center.y - 8.dp.toPx()),
                                    strokeWidth = 2.dp.toPx()
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // "Kabar Komunitas" label
        Text(
            text = "Kabar Komunitas",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Community news cards with sample content
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(5) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Small circular avatar for the post
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFDBB5))
                                .border(1.dp, Color.Gray, CircleShape)
                        ) {
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                val center = Offset(size.width / 2, size.height / 2)

                                // Simple face
                                drawCircle(
                                    color = androidx.compose.ui.graphics.Color.Black,
                                    radius = 2.dp.toPx(),
                                    center = Offset(center.x - 6.dp.toPx(), center.y - 4.dp.toPx())
                                )
                                drawCircle(
                                    color = androidx.compose.ui.graphics.Color.Black,
                                    radius = 2.dp.toPx(),
                                    center = Offset(center.x + 6.dp.toPx(), center.y - 4.dp.toPx())
                                )
                                drawArc(
                                    color = androidx.compose.ui.graphics.Color.Black,
                                    startAngle = 0f,
                                    sweepAngle = 180f,
                                    useCenter = false,
                                    topLeft = Offset(center.x - 6.dp.toPx(), center.y + 2.dp.toPx()),
                                    size = Size(12.dp.toPx(), 8.dp.toPx()),
                                    style = Stroke(width = 1.5.dp.toPx())
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = when (index) {
                                    0 -> "Sarah membagikan tips kesehatan"
                                    1 -> "Andi bergabung dengan komunitas"
                                    2 -> "Lisa mengadakan diskusi menarik"
                                    3 -> "Budi berbagi pengalaman baru"
                                    else -> "Maria mengundang teman-teman"
                                },
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                color = Color.Black,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${index + 1} jam yang lalu",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}