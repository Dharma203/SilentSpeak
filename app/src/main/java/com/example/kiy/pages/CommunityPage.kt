package com.example.kiy.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kiy.R
@Composable
fun CommunityPage() {
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
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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

        // Horizontal scroll row with circular avatars
        val avatarImages = listOf(
            painterResource(id = R.drawable.ic_launcher_background),
            painterResource(id = R.drawable.ic_launcher_background),
            painterResource(id = R.drawable.ic_launcher_background)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(avatarImages.size) { index ->
                Image(
                    painter = avatarImages[index],
                    contentDescription = "Avatar $index",
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.Black, CircleShape)
                )
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

        // Two white cards placeholders
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(72.dp)
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            )
            Box(
                modifier = Modifier
                    .height(72.dp)
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            )
        }
    }
}

@Preview
@Composable
fun PreviewKomunitasContent() {
    CommunityPage()
}
