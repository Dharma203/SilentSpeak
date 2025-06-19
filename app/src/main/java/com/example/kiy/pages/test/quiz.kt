package com.example.kiy.pages.test
// QuizPage.kt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kiy.R
import com.example.kiy.Screen
import com.example.kiy.pages.test.XPManager

@Composable
fun QuizPage(
    navToHome: () -> Unit = {},
    navToNextQuiz: () -> Unit = {},
    navController: NavHostController
) {
    val context = LocalContext.current
    val xpManager = remember { XPManager(context) }

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var xp by remember { mutableIntStateOf(0) }
    var stars by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableIntStateOf(-1) }
    var showResult by remember { mutableStateOf(false) }
    var isAnswered by remember { mutableStateOf(false) }
    var quizCompleted by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    val allLetters = ('A'..'Z').toList()
    val questions = remember(currentQuestionIndex == 0 && !quizCompleted) {
        allLetters.shuffled().take(6).map { letter ->
            val incorrectOptions = allLetters.filter { it != letter }.shuffled().take(3)
            val allOptions = listOf("hand_$letter") + incorrectOptions.map { "hand_$it" }
            val shuffledOptions = allOptions.shuffled()
            Question(
                letter.toString(),
                "hand_$letter",
                shuffledOptions
            )
        }
    }

    val currentQuestion = questions.getOrNull(currentQuestionIndex)

    LaunchedEffect(Unit) {
        xpManager.syncFromFirestore {
            xp = xpManager.getXP()
            stars = xpManager.getStars()
            isLoading = false
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (quizCompleted) {
        saveQuizResultToFirestore(score, score * 50, score * 5)
        QuizResultPage(
            score = score,
            totalQuestions = questions.size,
            xp = score * 50,
            stars = score * 5,
            onRetry = {
                currentQuestionIndex = 0
                score = 0
                selectedOption = -1
                showResult = false
                isAnswered = false
                quizCompleted = false
            },
            onHome = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            },
            onNext = navToNextQuiz
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5DC))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header with XP and Stars
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$xp XP",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$stars",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = "⭐",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            LinearProgressIndicator(
                progress = (currentQuestionIndex + 1) / questions.size.toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Color(0xFFFF9500),
                trackColor = Color.LightGray,
            )

            Spacer(modifier = Modifier.height(32.dp))

            currentQuestion?.let { question ->
                Text(
                    text = "Gambar mana yang menunjukkan huruf \"${question.letter}\"",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(question.options.size) { index ->
                        val option = question.options[index]
                        val isSelected = selectedOption == index
                        val isCorrect = option == question.correctAnswer

                        val borderColor = when {
                            !isAnswered -> Color.LightGray
                            isSelected && isCorrect -> Color.Green
                            isSelected && !isCorrect -> Color.Red
                            !isSelected && isCorrect -> Color.Green
                            else -> Color.LightGray
                        }

                        Card(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .border(
                                    width = if (isAnswered && (isSelected || isCorrect)) 3.dp else 1.dp,
                                    color = borderColor,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable(enabled = !isAnswered) {
                                    selectedOption = index
                                    isAnswered = true
                                    showResult = true
                                    if (isCorrect) {
                                        score++
                                        xpManager.addXP(50)
                                        xpManager.addStars(5)
                                        xp = xpManager.getXP()
                                        stars = xpManager.getStars()
                                    }
                                },
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = getDrawableResId(option)),
                                    contentDescription = "Hand gesture option",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        if (isAnswered) {
                            if (currentQuestionIndex < questions.size - 1) {
                                currentQuestionIndex++
                                selectedOption = -1
                                isAnswered = false
                                showResult = false
                            } else {
                                quizCompleted = true
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9500)),
                    shape = RoundedCornerShape(28.dp),
                    enabled = isAnswered
                ) {
                    Text(
                        text = if (currentQuestionIndex < questions.size - 1) "Lanjut" else "Selesai",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                if (showResult) {
                    Spacer(modifier = Modifier.height(16.dp))
                    val isCorrect = question.options[selectedOption] == question.correctAnswer
                    Text(
                        text = if (isCorrect) "Benar! ✓" else "Salah! ✗",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isCorrect) Color.Green else Color.Red,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

data class Question(
    val letter: String,
    val correctAnswer: String,
    val options: List<String>
)



@Composable
fun getDrawableResId(imageName: String): Int {
    return when (imageName) {
        "hand_A" -> R.drawable.hand_a
        "hand_B" -> R.drawable.hand_b
        "hand_C" -> R.drawable.hand_c
        "hand_D" -> R.drawable.hand_d
        "hand_E" -> R.drawable.hand_e
        "hand_F" -> R.drawable.hand_f
        "hand_G" -> R.drawable.hand_g
        "hand_H" -> R.drawable.hand_h
        "hand_I" -> R.drawable.hand_i
        "hand_J" -> R.drawable.hand_j
        "hand_K" -> R.drawable.hand_k
        "hand_L" -> R.drawable.hand_l
        "hand_M" -> R.drawable.hand_m
        "hand_N" -> R.drawable.hand_n
        "hand_O" -> R.drawable.hand_o
        "hand_P" -> R.drawable.hand_p
        "hand_Q" -> R.drawable.hand_q
        "hand_R" -> R.drawable.hand_r
        "hand_S" -> R.drawable.hand_s
        "hand_T" -> R.drawable.hand_t
        "hand_U" -> R.drawable.hand_u
        "hand_V" -> R.drawable.hand_v
        "hand_W" -> R.drawable.hand_w
        "hand_X" -> R.drawable.hand_x
        "hand_Y" -> R.drawable.hand_y
        "hand_Z" -> R.drawable.hand_z
        else -> R.drawable.hand_a // default fallback
    }
}


