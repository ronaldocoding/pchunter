package com.example.setupbuilder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.android.synthetic.main.register_activity.*

class LoginActivity : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        button_login.setOnClickListener {
            signIn()
        }
        register_link.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        recovery_link.setOnClickListener {
            val intent = Intent(this, PasswordRecoveryActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signIn(){
        val email = email_login.text.toString()
        val password = password_login.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            if(email.isEmpty())
                email_login.setError("Esse campo não pode ficar vazio.")
            if(password.isEmpty())
                password_login.setError("Esse campo não pode ficar vazio.")

            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
            .addOnFailureListener {
                if(it.message.toString().contains("password is invalid")){
                    password_login.setError("Senha incorreta.")
                }else
                    if(it.message.toString().contains("There is no user")){
                    email_login.setError("E-mail não cadastrado.")
                    }else{
                        Toast.makeText(this, "Ocorreu um erro inesperado. Tente novamente.", Toast.LENGTH_LONG).show()
                    }
            }

    }
}