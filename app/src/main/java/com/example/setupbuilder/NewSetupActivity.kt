package com.example.setupbuilder

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.setupbuilder.model.Setup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.new_setup_activity.*

class NewSetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_setup_activity)

        var sName = setup_name.text.toString()

        setup_button.setOnClickListener {
            if(sName.isEmpty()){
                setup_name.setError("O campo não pode ficar vazio.")
            }else{
                var setup = Setup(sName)
                FirebaseFirestore.getInstance().collection("setup")
                    .add(setup).addOnSuccessListener {
                        Toast.makeText(this, "Adicionado.", Toast.LENGTH_LONG).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
            }
        }
    }
}