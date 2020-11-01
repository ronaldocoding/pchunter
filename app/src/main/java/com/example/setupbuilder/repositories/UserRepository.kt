package com.example.setupbuilder.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class UserRepository {
    private val user = FirebaseAuth.getInstance().currentUser

    public fun getUser (): FirebaseUser? {
        return user
    }
}