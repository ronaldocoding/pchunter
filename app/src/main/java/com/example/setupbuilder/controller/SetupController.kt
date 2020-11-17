package com.example.setupbuilder.controller

import android.content.Context
import android.content.Intent
import android.widget.TextView
import com.example.setupbuilder.model.Setup
import com.example.setupbuilder.view.ViewSetupActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

class SetupController {
    val userUid = UserController().getUID()
    val setupFirebase = FirebaseFirestore.getInstance().collection("setup")
    val partController = PartController()

    public fun listSetupsByTime(order: String): Task<QuerySnapshot> {
        var dir = Query.Direction.ASCENDING
        if(order.equals("desc"))
            dir = Query.Direction.DESCENDING
        return setupFirebase.whereEqualTo("userUid", userUid.toString()).orderBy("timestamp", dir).get()
    }
    public fun listSetupsByPrice(order: String): Task<QuerySnapshot> {
        var dir = Query.Direction.ASCENDING
        if(order.equals("desc"))
            dir = Query.Direction.DESCENDING
        return setupFirebase.whereEqualTo("userUid", userUid.toString()).orderBy("preco", dir).get()
    }

    public fun add(setup: Setup): Task<DocumentReference> {
        return setupFirebase.add(setup)
    }

    public fun addPart(partName:String, asin:String, precoNew:Double, setupName:String, context: Context){
        val data = hashMapOf(partName to asin, "preco" to 0.0)
        var preco = 0.0
        var total = 0.0
        listSetupsByTime("cresc").addOnSuccessListener {
            documents->
            for(document in documents){
                //verifica o nome do setup
                if(document.data.get("name")?.equals(setupName)!!){
                    //atualiza passando um objeto {peça:id}"

                    total = document.data.get("preco") as Double

                    if(document.data.get(partName) != null){
                        partController.listPartsByAsin(document.data.get(partName).toString()).addOnSuccessListener { response ->
                            for (r in response){
                                preco = r.data.get("preco") as Double

                            }
                        }
                    }

                    data.put("preco", total-preco+precoNew)
                    document.reference.set(data, SetOptions.merge()).addOnSuccessListener {
                        val intent = Intent(context, ViewSetupActivity::class.java)
                        intent.putExtra("name", setupName)
                        context.startActivity(intent)
                    }
                }
            }
        }


    }
    public fun deletePart(partName: String,  preco: Double, setupName: String){
        val updates = hashMapOf<String, Any>(
            partName to FieldValue.delete()
        )

        listSetupsByTime("cresc").addOnSuccessListener {
                documents->
            for(document in documents){
                if(document.data.get("name")?.equals(setupName)!!){
                    updates.put("preco", document.data.get("preco") as Double - preco)
                    document.reference.update(updates)
                }
            }
        }
    }


    public fun getByName(setupName: String): Task<QuerySnapshot> {
        return setupFirebase.whereEqualTo("name", setupName).whereEqualTo("userUid", userUid.toString()).get()

    }


}