package br.com.setupbuilder.controller

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class PartController {
    val setupFirebase = FirebaseFirestore.getInstance().collection("part")

    public fun listPartsByTerm(term: String, order: String): Task<QuerySnapshot> {
        if(order == "desc")
            return setupFirebase.whereEqualTo("produto", term).orderBy("preco", Query.Direction.DESCENDING).get()
        else
            return setupFirebase.whereEqualTo("produto", term).orderBy("preco", Query.Direction.ASCENDING).get()
    }
    public fun listPartsByAsin(asin: String): Task<QuerySnapshot> {
        return setupFirebase.whereEqualTo("asin", asin).get()
    }
}