package com.example.setupbuilder.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class SetupRepository {
    public fun listSetups (): Task<QuerySnapshot> {
        return FirebaseFirestore.getInstance().collection("setup").get()
    }
}