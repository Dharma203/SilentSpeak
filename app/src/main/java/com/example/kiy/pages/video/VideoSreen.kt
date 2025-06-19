package com.example.kiy.pages.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kiy.R
@Composable
fun VideoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF6E9))
    ) {
        // Header full width tanpa padding
        Header()

        // Konten dan card dengan padding kiri kanan
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                ContentSection(
                    title = "Pengenalan Bahasa Isyarat",
                    description = "Bahasa isyarat adalah cara berkomunikasi visual menggunakan gerakan tangan, ekspresi wajah, dan bahasa tubuh, umumnya digunakan oleh teman Tuli. Beberapa jenisnya antara lain BISINDO, SIBI, dan ASL. Tujuannya untuk menciptakan komunikasi yang inklusif dan saling memahami.",
                    videoId = "PbBzOeLUh4A"
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
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

            Card(
                modifier = Modifier
                    .fillMaxWidth()
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

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* TODO: aksi */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(24.dp) // sesuaikan bentuk button
                    ),
                shape = RoundedCornerShape(24.dp), // bentuk sudut tombol
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFC700),
                    contentColor = Color.White
                )
            ) {
                Text("Buka Kamus", fontWeight = FontWeight.Bold)
            }

        }
    }
}


@Composable
fun ContentSection(title: String, description: String, videoId: String) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .fillMaxWidth()
            .padding(bottom = 16.dp)

    ) {
        YouTubeVideoPlayer(
            videoId = videoId,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .aspectRatio(16f / 9f)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF2C94C))
            .height(56.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Kembali",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = "SIBI",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically) // ✅ Ini masih OK karena RowScope juga punya align
            )

            // Untuk image, jangan pakai .align() karena ini di Row,
            // Gunakan Spacer + Modifier.size dan atur posisinya lewat Arrangement atau Modifier.weight()
            Image(
                painter = painterResource(id = R.drawable.lines),
                contentDescription = "Dekorasi garis",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(200.dp)
            )
        }
    }
}

