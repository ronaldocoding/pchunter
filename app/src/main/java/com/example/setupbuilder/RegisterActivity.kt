package com.example.setupbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.register_activity.*
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseA

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        button_register.setOnClickListener {
            createUser()
        }

        textLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createUser() {
        val email = email_register.text.toString()
        val password = password_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this,
                "email e senha devem ser informados",
                Toast.LENGTH_LONG).show()
            return
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                var id = it.result?.user?.uid
                Log.i("Teste", "UserID é: " + id)
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }.addOnFailureListener {
            Log.e("Teste", it.message, it)
        }
    }

}