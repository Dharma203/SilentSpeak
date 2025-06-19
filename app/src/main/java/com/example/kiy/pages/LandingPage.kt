package com.example.kiy.pages

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.room.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.kiy.R


// Room Database Setup
@Entity(tableName = "app_preferences")
data class AppPreference(
    @PrimaryKey val id: Int = 1,
    val hasSeenLandingPage: Boolean = false
)

@Dao
interface AppPreferenceDao {
    @Query("SELECT * FROM app_preferences WHERE id = 1")
    suspend fun getPreference(): AppPreference?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreference(preference: AppPreference)

    @Query("UPDATE app_preferences SET hasSeenLandingPage = :hasSeen WHERE id = 1")
    suspend fun updateLandingPageSeen(hasSeen: Boolean)
}

@Database(
    entities = [AppPreference::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appPreferenceDao(): AppPreferenceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// Landing Page Composable
@Composable
fun LandingPage(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F1E8))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top section with title
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Title
            Text(
                text = "Halo",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Subtitle
            Text(
                text = "Selamat Datang di Silent Speak App",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Center illustration
        Image(
            painter = painterResource(id = R.drawable.welcome_image),
            contentDescription = "Welcome illustration",
            modifier = Modifier.size(280.dp)
        )

        // Bottom section with buttons
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Login Button
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFB347)
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Masuk",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Register Button
            Button(
                onClick = onRegisterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9ACD32)
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Daftar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// Usage in your main activity or composable
@Composable
fun MainScreen(
    onNavigateToMain: () -> Unit
) {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val dao = db.appPreferenceDao()

    var hasSeenLandingPage by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    // Check landing page status
    LaunchedEffect(Unit) {
        val preference = dao.getPreference()
        hasSeenLandingPage = preference?.hasSeenLandingPage ?: false
        isLoading = false
    }

    if (isLoading) {
        // Show a loading indicator if needed
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading...")
        }
        return
    }

    if (!hasSeenLandingPage) {
        Scaffold { paddingValues ->
            LandingPage(
                onLoginClick = {
                    // Mark as seen and navigate
                    // This should ideally be in a ViewModel or a separate coroutine scope
                    // For simplicity, doing it directly here.
                    // Consider moving this logic to a ViewModel for better architecture.
                    GlobalScope.launch { dao.insertPreference(AppPreference(hasSeenLandingPage = true)) }
                    onNavigateToMain()
                },
                onRegisterClick = { /* Handle register navigation */ },
                modifier = Modifier.padding(paddingValues)
            )
        }
    } else {
        // Show your main app content
        onNavigateToMain()
    }
}

@Preview
@Composable
fun LandingPagePreview() {
    LandingPage(onLoginClick = {}, onRegisterClick = {})
}
