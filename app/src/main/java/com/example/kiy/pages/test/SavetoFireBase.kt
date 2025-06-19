package com.example.kiy.pages.test

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

val auth = FirebaseAuth.getInstance()
val firestore = FirebaseFirestore.getInstance()

fun saveQuizResultToFirestore(score: Int, xp: Int, stars: Int) {
    val uid = auth.currentUser?.uid
    if (uid == null) {
        // User not logged in, handle this case
        return
    }

    val userDocRef = firestore.collection("users").document(uid)

    // You can either update specific fields or merge with existing data
    val updateData = mapOf(
        "lastQuizScore" to score,
        "xp" to xp,
        "stars" to stars,
        "lastQuizDate" to FieldValue.serverTimestamp()
    )

    userDocRef.set(updateData, SetOptions.merge())
        .addOnSuccessListener {
            // Successfully saved
        }
        .addOnFailureListener { e ->
            // Handle failure
        }
}
