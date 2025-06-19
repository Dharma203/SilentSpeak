package com.example.kiy.pages.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun VideoTutorialPage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Text(
            text = "Video Tutorial",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
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

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
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

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    ContentSection(
                        title = "Grammar Dasar Bahasa Isyarat",
                        description = "Pelajari tata bahasa dasar bahasa isyarat yang membantu dalam menyusun kalimat dengan benar untuk komunikasi yang efektif.",
                        videoId = "0FcwzMq4iWg"
                    )
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    ContentSection(
                        title = "Dialog Sehari-hari",
                        description = "Contoh dialog dalam bahasa isyarat untuk aktivitas sehari-hari seperti berbelanja, bertanya arah, dan bersosialisasi.",
                        videoId = "NaafQwd0XEY"
                    )
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    ContentSection(
                        title = "Isyarat Emosi",
                        description = "Mengenal isyarat yang mengekspresikan berbagai emosi dalam komunikasi bahasa isyarat.",
                        videoId = "PqCs1HTaVeU"
                    )
                }
            }
        }
    }
}
