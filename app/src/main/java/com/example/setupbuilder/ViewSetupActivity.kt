package com.example.setupbuilder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.setupbuilder.fragment.HomeFragment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.account_fragment.*
import kotlinx.android.synthetic.main.new_setup_activity.*
import kotlinx.android.synthetic.main.view_setup_activity.*

class ViewSetupActivity : AppCompatActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_setup_activity)
        setupName.setText(intent.getStringExtra("setupName"))
        inputName.setText(intent.getStringExtra("setupName"))

        inputName.setOnKeyListener(View.OnKeyListener { view, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                pbEditSetup.visibility = View.VISIBLE
                val setups = FirebaseFirestore.getInstance().collection("setup")
                setups.get().addOnSuccessListener { documents ->
                    var error = false

                    if(inputName.text.toString().isEmpty()){
                        inputName.setError("Esse campo não pode ficar vazio")
                        error = true
                        pbEditSetup.visibility = View.INVISIBLE
                    }else {
                        for(document in documents){
                            if(document.data.get("name").toString().equals(inputName.text.toString()) &&
                                !setupName.text.toString().equals(inputName.text.toString())){
                                inputName.setError("Nome já existente")
                                error = true
                                pbEditSetup.visibility = View.INVISIBLE
                            }
                        }
                    }

                    if (!error){
                        FirebaseFirestore.getInstance().collection("setup").whereEqualTo("name", intent.getStringExtra("setupName")).get()
                            .addOnSuccessListener {documents ->
                                for (document in documents) {
                                    //Atualizar um campo
                                    setupName.setText(inputName.text.toString())
                                    document.reference.update("name", inputName.text.toString())
                                    inputName.visibility = View.GONE
                                    setupName.visibility = View.VISIBLE
                                    pbEditSetup.visibility = View.INVISIBLE
                                }
                            }.addOnFailureListener {
                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                                pbEditSetup.visibility = View.INVISIBLE
                            }
                    }
                }.addOnFailureListener {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        pbEditSetup.visibility = View.INVISIBLE
                    }

            }
            false
        })

        editName.setOnClickListener {
            inputName.visibility = View.VISIBLE
            setupName.visibility = View.GONE
        }
        deleteSetup.setOnClickListener {
            FirebaseFirestore.getInstance().collection("setup")
                .whereEqualTo("name", intent.getStringExtra("setupName")).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        document.reference.delete().addOnSuccessListener {
                            startActivity(Intent(this, MenuActivity::class.java))
                        }.addOnFailureListener {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
        }
    }
}



