package com.example.setupbuilder.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.setupbuilder.R
import com.example.setupbuilder.model.Setup
import com.example.setupbuilder.controller.SetupController
import com.example.setupbuilder.controller.UserController
import com.google.firebase.firestore.FieldValue.serverTimestamp
import kotlinx.android.synthetic.main.new_setup_activity.*

class NewSetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_setup_activity)
        var sName = setup_name.text
        var user = UserController().getUser();
        var setups = SetupController()

        setup_button.setOnClickListener {
            if(sName.isEmpty()){

                setup_name.setError("Este campo não pode ficar vazio")
            }else{
                var i = 0
                setups.listSetupsByTime("cresc").addOnSuccessListener { documents ->
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
                                sName.toString(), serverTimestamp(), it1, 0.0
                            )
                        }

                        if (setup != null) {
                            setups.add(setup).addOnSuccessListener {
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