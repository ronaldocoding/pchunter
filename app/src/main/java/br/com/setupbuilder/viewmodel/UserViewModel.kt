package br.com.setupbuilder.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import br.com.setupbuilder.controller.UserController
import br.com.setupbuilder.view.MenuActivity

class UserViewModel {
    private var mUser = UserController()
    public fun SignIn(email:EditText, password:EditText, context:Context):Boolean{

        var result = false
        mUser.signIn(email.text.toString(), password.text.toString())
            .addOnSuccessListener {
                result = true
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