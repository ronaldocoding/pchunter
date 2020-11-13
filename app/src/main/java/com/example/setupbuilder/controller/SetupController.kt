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

    public fun listSetupsByTime(order: String): Task<QuerySnapshot> {
        var dir = Query.Direction.ASCENDING
        if(order.equals("desc"))
            dir = Query.Direction.DESCENDING
        return setupFirebase.whereEqualTo("userUid", userUid.toString()).orderBy("timestamp", dir).get()
    }

    public fun add(setup: Setup): Task<DocumentReference> {

        return setupFirebase.add(setup)
    }

    public fun addPart(partName:String, asin:String, setupName:String, context: Context){
        val data = hashMapOf(partName to asin)

        listSetupsByTime("cresc").addOnSuccessListener {
            documents->
            for(document in documents){
                if(document.data.get("name")?.equals(setupName)!!){
                    document.reference.set(data, SetOptions.merge()).addOnSuccessListener {
                        val intent = Intent(context, ViewSetupActivity::class.java)
                        intent.putExtra("name", setupName)
                        context.startActivity(intent)
                    }
                }
            }
        }


    }
    public fun deletePart(partName: String, setupName: String){
        val updates = hashMapOf<String, Any>(
            partName to FieldValue.delete()
        )

        listSetupsByTime("cresc").addOnSuccessListener {
                documents->
            for(document in documents){
                if(document.data.get("name")?.equals(setupName)!!){
                    document.reference.update(updates)
                }
            }
        }
    }


    public fun getTotalPrice(setupName: String, view : TextView){
        val keys = arrayOf(
            "CPU",
            "placa-mãe",
            "Memória RAM",
            "Placa de vídeo",
            "hd externo",
            "Monitor",
            "Teclado",
            "Mouse",
            "Headset",
            "Fonte de alimentação",
            "Gabinete"
        )
        val cPart = PartController()
        var total:Double = 0.0
        listSetupsByTime("cresc").addOnSuccessListener {
                documents->
            for(document in documents){
                if(document.data.get("name")?.equals(setupName)!!){
                    for(key in keys){
                        if(document.data.get(key)!==null){
                            cPart.listPartsByAsin(document.data.get(key).toString()).addOnSuccessListener {
                                documents->
                                    for(document in documents){
                                        var preco = document.data.get("preco").toString().toDouble()
                                        total=total+preco

                                    }
                                view.text="R$" + total
                            }
                        }

                    }

                }
            }
        }
    }

}