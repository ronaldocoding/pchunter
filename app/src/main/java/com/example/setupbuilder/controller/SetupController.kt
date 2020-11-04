package com.example.setupbuilder.controller

import com.example.setupbuilder.model.Setup
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class SetupController {
    val setupFirebase = FirebaseFirestore.getInstance().collection("setup")

    public fun listSetupsByTime(order: String): Task<QuerySnapshot> {
        var dir = Query.Direction.ASCENDING
        if(order.equals("desc"))
            dir = Query.Direction.DESCENDING
        return setupFirebase.orderBy("timestamp", dir).get()
    }

    public fun add(setup: Setup): Task<DocumentReference> {

        return setupFirebase.add(setup)
    }
}