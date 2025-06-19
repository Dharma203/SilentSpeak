// XPManager.kt
package com.example.kiy.pages.test

import android.content.Context
import androidx.core.content.edit
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore

class XPManager(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("USER_PROGRESS", Context.MODE_PRIVATE)
    private val xpKey = "user_xp"
    private val starsKey = "user_stars"

    private val firestore = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

    fun getXP(): Int = sharedPreferences.getInt(xpKey, 0)
    fun setXP(xp: Int) = sharedPreferences.edit { putInt(xpKey, xp) }
    fun getStars(): Int = sharedPreferences.getInt(starsKey, 0)
    fun setStars(stars: Int) = sharedPreferences.edit { putInt(starsKey, stars) }

    // Firestore sync on app start
    fun syncFromFirestore(onComplete: () -> Unit) {
        val userId = auth.currentUser?.uid ?: return onComplete()
        firestore.collection("users").document(userId).get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    val xp = doc.getLong("xp")?.toInt() ?: 0
                    val stars = doc.getLong("stars")?.toInt() ?: 0
                    setXP(xp)
                    setStars(stars)
                }
                onComplete()
            }
            .addOnFailureListener {
                onComplete()
            }
    }

    // Update XP locally and in Firestore
    fun addXP(xpToAdd: Int) {
        val newXP = getXP() + xpToAdd
        setXP(newXP)
        updateFirestore("xp", newXP)
    }

    // Update Stars locally and in Firestore
    fun addStars(starsToAdd: Int) {
        val newStars = getStars() + starsToAdd
        setStars(newStars)
        updateFirestore("stars", newStars)
    }

    private fun updateFirestore(field: String, value: Int) {
        val userId = auth.currentUser?.uid ?: return
        val data = hashMapOf(field to value)
        firestore.collection("users").document(userId)
            .set(data, SetOptions.merge())
    }
}