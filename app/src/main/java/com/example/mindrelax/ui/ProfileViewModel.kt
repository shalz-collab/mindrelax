package com.example.mindrelax.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    var userName by mutableStateOf("Aravindhan VG")
    var userEmail by mutableStateOf("aravindhan@example.com")
    var userBio by mutableStateOf("On a journey to mindfulness.")
    var isLoading by mutableStateOf(false)

    init {
        fetchUserProfile()
    }

    fun fetchUserProfile() {
        val userId = auth.currentUser?.uid ?: return
        isLoading = true
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    userName = document.getString("name") ?: "Alex Johnson"
                    userEmail = document.getString("email") ?: auth.currentUser?.email ?: "alex.j@example.com"
                    userBio = document.getString("bio") ?: "On a journey to mindfulness."
                }
                isLoading = false
            }
            .addOnFailureListener {
                isLoading = false
            }
    }

    fun updateUserProfile(newName: String, newBio: String, onComplete: () -> Unit) {
        val userId = auth.currentUser?.uid ?: return
        val updates = mapOf(
            "name" to newName,
            "bio" to newBio
        )
        isLoading = true
        db.collection("users").document(userId).set(updates, com.google.firebase.firestore.SetOptions.merge())
            .addOnSuccessListener {
                userName = newName
                userBio = newBio
                isLoading = false
                onComplete()
            }
            .addOnFailureListener {
                isLoading = false
            }
    }
}
