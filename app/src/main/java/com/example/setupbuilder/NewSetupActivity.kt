package com.example.setupbuilder

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.setupbuilder.model.Setup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.new_setup_activity.*
import java.sql.Timestamp
import java.time.LocalDateTime

class NewSetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_setup_activity)
        val timestamp = Timestamp(System.currentTimeMillis())
        var sName = setup_name.text
        val user = FirebaseAuth.getInstance().currentUser
        setup_button.setOnClickListener {
            if(sName.isEmpty()){

                setup_name.setError("Este campo não pode ficar vazio")
            }else{

                var i = 0
                val setups = FirebaseFirestore.getInstance().collection("setup")
                setups.get().addOnSuccessListener { documents ->
                    var equal = false

                    for(document in documents){
                        if(document.data.get("name").toString().equals(sName.toString())){
                            setup_name.setError("Nome já existente")
                            equal = true
                        }
                        i++
                    }

                    if (!equal){
                        var setup = user?.uid?.let { it1 ->
                            Setup(
                                sName.toString(), serverTimestamp(), it1
                            )
                        }

                        if (setup != null) {
                            setups
                                .add(setup).addOnSuccessListener {
                                    Toast.makeText(this, "Adicionado.", Toast.LENGTH_LONG).show()

                                    val intent = Intent(this, MenuActivity::class.java)
                                    intent.putExtra("SetupName", "1")
                                    startActivity(intent)
                                }.addOnFailureListener {
                                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                                }
                        }
                    }
                }

            }
        }
    }
}