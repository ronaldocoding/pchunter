package com.example.setupbuilder.controller

import android.widget.Toast
import com.example.setupbuilder.model.Setup
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class PartController {
    val setupFirebase = FirebaseFirestore.getInstance().collection("part")

    public fun listPartsByTerm(term: String): Task<QuerySnapshot> {
        return setupFirebase.whereEqualTo("produto", term).get()
    }
    public fun listPartsByAsin(asin: String): Task<QuerySnapshot> {
        return setupFirebase.whereEqualTo("asin", asin).get()
    }
}