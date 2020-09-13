package com.example.setupbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.setupbuilder.model.User
import kotlinx.android.synthetic.main.register_activity.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

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
        val name = name_register.text.toString()
        val password = password_register.text.toString()
        var valid = true

        if (email.isEmpty() || password.isEmpty()) {
            Patterns.EMAIL_ADDRESS

            if(email.isEmpty())
                email_register.setError("Esse campo não pode ficar vazio!")
            if(password.isEmpty())
                password_register.setError("Esse campo não pode ficar vazio!")
            if(name.isEmpty())
                name_register.setError("Esse campo não pode ficar vazio!")
            valid = false
        }

        if(!password.equals(password_register2.text.toString())){
            password_register2.setError("As senhas não coincidem")
            valid = false
        }

        if(password.length < 6){
            password_register.setError("A sua senha deve conter no mínimo 6 caracteres")
            valid = false
        }

        name.forEach {
            if(!it.isLetter() && !it.isWhitespace()){
                valid = false
                name_register.setError("Esse campo não permite números ou caracteres especiais")
            }
        }

        if(!valid)
            return

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                var id = it.result?.user?.uid
                val user = id?.let { it1 -> User(it1, name) }
                if (user != null) {
                    FirebaseFirestore.getInstance().collection("users")
                        .add(user)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Foi", Toast.LENGTH_LONG).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Falhou", Toast.LENGTH_LONG).show()
                        }
                }

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }.addOnFailureListener {
                if(it.message.toString().contains("already in use")){
                    email_register.setError("Esse e-mail já está sendo usado.")
                }else
                if(it.message.toString().contains("badly formatted")){
                    email_register.setError("Insira um e-mail válido.")
                }else
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }
    }
}