package com.example.setupbuilder

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.recovery_activity.*

class PasswordRecoveryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recovery_activity)

        send_email.setOnClickListener {
            var email = email_recovery.text.toString()
            if(email.isEmpty()){
                email_recovery.setError("Esse campo não pode ficar vazio.")
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(
                    email
                ).addOnSuccessListener {
                    Toast.makeText(this, "E-mail de recuperação de senha enviado.", Toast.LENGTH_LONG).show()
                }.addOnFailureListener{
                    if(it.message.toString().contains("badly formatted")){
                        email_recovery.setError("Insira um email válido.")
                    }else
                    if(it.message.toString().contains("There is no user")){
                        email_recovery.setError("Insira um email cadastrado.")
                    }else {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }

        }
    }
}
