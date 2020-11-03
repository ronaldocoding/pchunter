package com.example.setupbuilder.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.setupbuilder.R
import com.example.setupbuilder.controller.UserController
import kotlinx.android.synthetic.main.register_activity.*

//import com.google.firebase.auth.FirebaseA

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        button_register.setOnClickListener {
            progressBarRegister.visibility = View.VISIBLE
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
        var valid = true
        val repository = UserController()

        if (email.isEmpty() || password.isEmpty()) {
            Patterns.EMAIL_ADDRESS

            if (email.isEmpty())
                email_register.setError("Esse campo não pode ficar vazio!")
            if (password.isEmpty())
                password_register.setError("Esse campo não pode ficar vazio!")

            valid = false
        }

        if (!password.equals(password_register2.text.toString())) {
            password_register2.setError("As senhas não coincidem")
            valid = false
        }

        if (password.length < 6) {
            password_register.setError("A sua senha deve conter no mínimo 6 caracteres")
            valid = false
        }


        if (!valid) {
            progressBarRegister.visibility = View.INVISIBLE
            return
        }

        repository.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    progressBarRegister.visibility = View.INVISIBLE
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                }
            }.addOnFailureListener {
                progressBarRegister.visibility = View.INVISIBLE
                if (it.message.toString().contains("already in use")) {
                    email_register.setError("Esse e-mail já está sendo usado.")
                } else
                    if (it.message.toString().contains("badly formatted")) {
                        email_register.setError("Insira um e-mail válido.")
                    } else
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
    }
}