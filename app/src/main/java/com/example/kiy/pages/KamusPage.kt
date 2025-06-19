package com.example.kiy.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kiy.R
@Composable
fun AlphabetDictionaryContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
        // Header Section
        HeaderSection(onBackClick = onBackClick)

        // Alphabet Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(26) { index ->
                val letter = ('A' + index).toString()
                AlphabetCard(
                    letter = letter,
                    imageRes = getDrawableResourceForLetter(letter)
                )
            }
        }
    }
}

@Composable
private fun HeaderSection(onBackClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDD835)),
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Kamus Alfabet",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
private fun AlphabetCard(
    letter: String,
    imageRes: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(100.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Sign for letter $letter",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = letter,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

private fun getDrawableResourceForLetter(letter: String): Int {
    return when (letter) {
        "A" -> R.drawable.hand_a
        "B" -> R.drawable.hand_b
        "C" -> R.drawable.hand_c
        "D" -> R.drawable.hand_d
        "E" -> R.drawable.hand_e
        "F" -> R.drawable.hand_f
        "G" -> R.drawable.hand_g
        "H" -> R.drawable.hand_h
        "I" -> R.drawable.hand_i
        "J" -> R.drawable.hand_j
        "K" -> R.drawable.hand_k
        "L" -> R.drawable.hand_l
        "M" -> R.drawable.hand_m
        "N" -> R.drawable.hand_n
        "O" -> R.drawable.hand_o
        "P" -> R.drawable.hand_p
        "Q" -> R.drawable.hand_q
        "R" -> R.drawable.hand_r
        "S" -> R.drawable.hand_s
        "T" -> R.drawable.hand_t
        "U" -> R.drawable.hand_u
        "V" -> R.drawable.hand_v
        "W" -> R.drawable.hand_w
        "X" -> R.drawable.hand_x
        "Y" -> R.drawable.hand_y
        "Z" -> R.drawable.hand_z
        else -> R.drawable.hand_a // fallback
    }
}