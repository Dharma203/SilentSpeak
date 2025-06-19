package com.example.kiy.pages

// AndroidX Compose Foundation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

// AndroidX Compose Material3
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text

// AndroidX Compose Runtime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

// AndroidX Compose UI
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kiy.R
import com.example.kiy.Screen
import com.example.kiy.pages.search.LanguageSearchBar
import com.example.kiy.pages.video.ContentSection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun DashboardPage(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var searchQuery by remember { mutableStateOf("") }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8)), // Use a light background color
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header Section
        item { HeaderSection() }

        // Language Selection
        item {
            LanguageSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                modifier = Modifier.padding(horizontal = 0.dp)
            )
        }

        // Feature Grid
        item {
            FeatureGrid(navController = navController)
        // <-- PENTING: gunakan navController dari parameter, bukan bikin baru
        }

        // Video Tutorial Section
        item { VideoTutorialSection() }

        // Community News Section
        item { CommunityNewsSection() }
    }
}

@Composable
private fun HeaderSection() {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val user = auth.currentUser

    var username by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(user?.uid) {
        if (user != null) {
            firestore.collection("users").document(user.uid).get()
                .addOnSuccessListener { document ->
                    username = document.getString("namaPengguna") ?: "User"
                    isLoading = false
                }
                .addOnFailureListener {
                    username = "User"
                    isLoading = false
                }
        } else {
            username = "User"
            isLoading = false
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFC700)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (isLoading) "Loading..." else "Selamat Pagi, ${username ?: "User"}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Apakah kamu sudah\nsiap belajar bahasa isyarat?",
                    fontSize = 14.sp,
                    color = Color.Black.copy(alpha = 0.7f),
                    lineHeight = 18.sp
                )
            }

            Image(
                painter = painterResource(id = com.example.kiy.R.drawable.avatar2), // Replace as needed
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(80.dp)
                    .border(2.dp, Color.Black, CircleShape)
            )
        }
    }
}


@Composable
fun FeatureGrid(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll( rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        FeatureCard(
            title = "Latihan",
            iconRes = R.drawable.ic_latihan,
            backgroundColor = Color(0xFF8BC34A),
            modifier = Modifier
//                .weight(1f)
                .width(100.dp),
            onClick = { navController.navigate(Screen.Practice.route) }
        )
//        FeatureCard(
//            title = "Tes",
//            iconRes = R.drawable.ic_tes,
//            backgroundColor = Color(0xFF8BC34A),
//            modifier = Modifier
////                .weight(1f)
//                .width(100.dp),
//
//            onClick = { navController.navigate(Screen.Community.route) }
//        )
        FeatureCard(
            title = "Kamus",
            iconRes = R.drawable.ic_kamus,
            backgroundColor = Color(0xFF8BC34A),
            modifier = Modifier
                .width(100.dp),

            onClick = { navController.navigate(Screen.AlphabetDictionary.route) }
        )
        FeatureCard(
            title = "Video Tutorial",
            iconRes = R.drawable.ic_vidio_tutorial,
            backgroundColor = Color(0xFF8BC34A),
            modifier = Modifier
//                .weight(1f),
                .width(100.dp),

            onClick = { navController.navigate(Screen.SIBI.route) }
        )
    }
}


@Composable
fun FeatureCard(
    title: String,
    iconRes: Int,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .size(
                width = 200.dp,
                height = 120.dp
            )
            .clickable { onClick() }
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                tint = Color.Unspecified,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, style = MaterialTheme.typography.labelMedium, color = Color.White, textAlign = TextAlign.Center)
        }
    }
}


@Composable
private fun VideoTutorialSection() {
    Column {
        Text(
            text = "Video Tutorial",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .width(280.dp) // Adjusted width for better display in LazyRow
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    ContentSection(
                        title = "Spelling Finger",
                        description = "Spelling finger adalah metode mengeja kata menggunakan gerakan jari untuk setiap huruf alfabet. Biasanya digunakan untuk nama atau kata yang belum memiliki isyarat khusus.",
                        videoId = "03kWuwWQwu0"
                    )
                }
            }
            // You can add more video items here if needed
            item {
                Card(
                    modifier = Modifier
                        .width(280.dp) // Adjusted width for better display in LazyRow
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    ContentSection(
                        title = "Kosa Kata Dasar",
                        description = "Kosakata dasar mencakup isyarat untuk kata-kata umum seperti salam, angka, warna, anggota keluarga, dan aktivitas sehari-hari yang penting dalam komunikasi awal.",
                        videoId = "arAzoJ5aFZ4"
                    )
                }
            }
        }
    }
}

@Composable
private fun VideoThumbnail(
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .clickable { },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Video tutorial",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun CommunityNewsSection() {
    Column {
        Text(
            text = "Kabar Komunitas",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                CommunityCard(
                    imageRes = R.drawable.komunitas,
                    modifier = Modifier.width(200.dp)
                )
            }
            item {
                CommunityCard(
                    imageRes = R.drawable.komunitas,
                    modifier = Modifier.width(200.dp)
                )
            }
            item {
                CommunityCard(
                    imageRes = R.drawable.komunitas,
                    modifier = Modifier.width(200.dp)
                )
            }
        }
    }
}

@Composable
private fun CommunityCard(
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(120.dp)
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDD835)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Community news",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
