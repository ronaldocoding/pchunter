package com.example.setupbuilder.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.setupbuilder.MenuActivity
import com.example.setupbuilder.model.UserModel
import org.w3c.dom.Text

class UserViewModel {
    private var mUser = UserModel()
    public fun SignIn(email:EditText, password:EditText, context:Context):Boolean{

        var result = false
        mUser.signIn(email.text.toString(), password.text.toString())
            .addOnSuccessListener {
                result = true
                Toast.makeText(context, "Entrou aqui sucesso", Toast.LENGTH_LONG).show()
                val intent = Intent(context, MenuActivity::class.java)
                context.startActivity(intent)
                return@addOnSuccessListener

            }
            .addOnFailureListener {
                if(it.message.toString().contains("password is invalid")){
                    password.setError("Senha incorreta.")
                }else
                    if(it.message.toString().contains("There is no user")){
                        email.setError("E-mail não cadastrado.")
                    }else
                        if(it.message.toString().contains("badly formatted")) {
                            email.setError("E-mail inválido.")
                        }else{
                            Toast.makeText(
                                context,
                                "Ocorreu um erro inesperado. Tente novamente.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                return@addOnFailureListener
            }
        return result
    }
}