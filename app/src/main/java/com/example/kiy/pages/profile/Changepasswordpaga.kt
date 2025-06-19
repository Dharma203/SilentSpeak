package com.example.kiy.pages.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kiy.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ChangePasswordPage(navController: NavHostController) {
    var passwordLama by remember { mutableStateOf("") }
    var passwordBaru by remember { mutableStateOf("") }
    var konfirmasiPassword by remember { mutableStateOf("") }

    var showPasswordLama by remember { mutableStateOf(false) }
    var showPasswordBaru by remember { mutableStateOf(false) }
    var showKonfirmasiPassword by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf<String?>(null) }

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser ?: return

    val creamBackground = Color(0xFFFDF5E6)
    val orangeColor = Color(0xFFFF8C00)
    val redButton = Color(0xFFE53E3E)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(creamBackground)
            .padding(24.dp)
    ) {
//        Spacer(modifier = Modifier.height(40.dp))

        // Header with back button and title
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Ganti Kata Sandi",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = orangeColor
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Password Lama
        Column {
            Text(
                text = "Password Lama",
                fontSize = 16.sp,
                color = orangeColor,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            OutlinedTextField(
                value = passwordLama,
                onValueChange = { passwordLama = it },
                placeholder = {
                    Text(
                        text = "Password Lama",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = orangeColor,
                    unfocusedBorderColor = orangeColor,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = RoundedCornerShape(24.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock",
                        tint = Color.Black
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { showPasswordLama = !showPasswordLama }) {
                        Icon(
                            painter = painterResource(id = if (showPasswordLama) R.drawable.ic_visibility_off else R.drawable.ic_visibility_off),
                            contentDescription = "Toggle password visibility",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Gray,
                        )
                    }
                },
                visualTransformation = if (showPasswordLama) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Password Baru
        Column {
            Text(
                text = "Password Baru",
                fontSize = 16.sp,
                color = orangeColor,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            OutlinedTextField(
                value = passwordBaru,
                onValueChange = { passwordBaru = it },
                placeholder = {
                    Text(
                        text = "Password Baru",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = orangeColor,
                    unfocusedBorderColor = orangeColor,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = RoundedCornerShape(24.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock",
                        tint = Color.Black
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { showPasswordBaru = !showPasswordBaru }) {
                        Icon(
                            painter = painterResource(id = if (showPasswordBaru) R.drawable.ic_visibility_off else R.drawable.ic_visibility_off),
                            contentDescription = "Toggle password visibility",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                },
                visualTransformation = if (showPasswordBaru) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Konfirmasi Password
        Column {
            Text(
                text = "Konfirmasi Password",
                fontSize = 16.sp,
                color = orangeColor,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            OutlinedTextField(
                value = konfirmasiPassword,
                onValueChange = { konfirmasiPassword = it },
                placeholder = {
                    Text(
                        text = "Konfirmasi Password Baru",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = orangeColor,
                    unfocusedBorderColor = orangeColor,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                shape = RoundedCornerShape(24.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock",
                        tint = Color.Black
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { showKonfirmasiPassword = !showKonfirmasiPassword }) {
                        Icon(
                            painter = painterResource(id = if (showKonfirmasiPassword) R.drawable.ic_visibility_off else R.drawable.ic_visibility_off),
                            contentDescription = "Toggle password visibility",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                },
                visualTransformation = if (showKonfirmasiPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_gantisandi),
                contentDescription = "Change password illustration",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(200.dp),
                contentScale = ContentScale.Fit
            )
        }

//        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
        } else {
            Button(
                onClick = {
                    message = null
                    if (passwordLama.isBlank() || passwordBaru.isBlank() || konfirmasiPassword.isBlank()) {
                        message = "Semua kolom harus diisi"
                        return@Button
                    }
                    if (passwordBaru != konfirmasiPassword) {
                        message = "Password baru dan konfirmasi tidak cocok"
                        return@Button
                    }
                    if (passwordBaru.length < 6) {
                        message = "Password baru minimal 6 karakter"
                        return@Button
                    }

                    isLoading = true
                    // Re-authenticate user with current password
                    val credential = EmailAuthProvider.getCredential(user.email ?: "", passwordLama)
                    user.reauthenticate(credential)
                        .addOnSuccessListener {
                            // Update password on success
                            user.updatePassword(passwordBaru)
                                .addOnSuccessListener {
                                    isLoading = false
                                    message = "Password berhasil diubah"
                                    navController.popBackStack()
                                }
                                .addOnFailureListener { e ->
                                    isLoading = false
                                    message = "Gagal mengubah password: ${e.localizedMessage}"
                                }
                        }
                        .addOnFailureListener {
                            isLoading = false
                            message = "Password lama salah"
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = redButton),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Simpan",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        message?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it, color = if (it.contains("berhasil")) Color.Green else Color.Red, textAlign = TextAlign.Center)
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
