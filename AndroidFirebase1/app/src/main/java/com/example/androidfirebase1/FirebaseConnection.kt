package com.example.androidfirebase1

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirebaseConnection {
    val firebaseAuth:FirebaseAuth by lazy{
        FirebaseAuth.getInstance()
    }
    val firestoreDB by lazy {
        Firebase.firestore
    }
    val refGlobal by lazy {
        firestoreDB.collection("users").document("${User.getEmail()}")
    }
}