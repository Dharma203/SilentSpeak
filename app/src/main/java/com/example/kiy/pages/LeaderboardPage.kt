package com.example.kiy.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kiy.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

@Composable
fun LeaderboardPage(navController: NavHostController) {
    val db = FirebaseFirestore.getInstance()
    val users = remember { mutableStateListOf<UserData>() }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        db.collection("users")
            .orderBy("xp", Query.Direction.DESCENDING)
            .limit(10)  // get top 10 users
            .get()
            .addOnSuccessListener { result ->
                users.clear()
                for (doc in result.documents) {
                    val username = doc.getString("username") ?: "Unknown"
                    val xp = doc.getLong("xp")?.toInt() ?: 0
                    val avatarUrl = doc.getString("avatarUrl") ?: ""
                    users.add(UserData(username, xp, avatarUrl))
                }
                isLoading.value = false
            }
            .addOnFailureListener {
                isLoading.value = false
            }
    }

    if (isLoading.value) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFC800)) // Yellow background
    ) {
        // Top bar with back icon and title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() },
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Leaderboard",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = Color.Black,
            )
        }

        // You can customize the top 3 positions here (podium)
        // For demo, we just display top 3 from Firestore list if available
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            users.getOrNull(1)?.let { user -> LeaderboardPodiumItem(user, 2) } // 2nd place
            users.getOrNull(0)?.let { user -> LeaderboardPodiumItem(user, 1) } // 1st place
            users.getOrNull(2)?.let { user -> LeaderboardPodiumItem(user, 3) } // 3rd place
        }

        Spacer(modifier = Modifier.height(20.dp))

        // List for ranks 4-10
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            LazyColumn {
                itemsIndexed(users.drop(3)) { index, user ->
                    LeaderboardRow(rank = index + 4, user = user)
                }
            }
        }
    }
}

data class UserData(
    val username: String,
    val xp: Int,
    val avatarUrl: String
)

@Composable
fun LeaderboardPodiumItem(user: UserData, position: Int) {
    val podiumHeight = when (position) {
        1 -> 160.dp
        2 -> 120.dp
        3 -> 100.dp
        else -> 100.dp
    }
    val podiumColor = Color(0xFF9D9123)
    val iconRes = when (position) {
        1 -> R.drawable.ic_first
        2 -> R.drawable.ic_second
        3 -> R.drawable.ic_third
        else -> R.drawable.ic_third
    }
    Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.width(88.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(podiumHeight)
                .width(88.dp)
                .background(podiumColor, RoundedCornerShape(12.dp))
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "$position place icon",
                tint = Color.White,
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp)
            )
        }
        // For avatar: you can use Coil or other lib to load from URL, fallback to placeholder
        // Here a simple placeholder:
        Image(
            painter = painterResource(id = R.drawable.avatar1), // Replace or load real avatar
            contentDescription = "$position place avatar",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-40).dp)
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape)
        )
    }
}

@Composable
fun LeaderboardRow(rank: Int, user: UserData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = Color(0xFFF8F8F8),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$rank",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.width(32.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        // Avatar (Use Coil or other to load image from URL)
        Image(
            painter = painterResource(id = R.drawable.avatar1), // Placeholder, replace with image loader if avatarUrl available
            contentDescription = "User avatar",
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .border(1.5.dp, Color.Gray, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = user.username,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "XP Star",
            tint = Color(0xFFFBC400),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "${user.xp} XP",
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}
