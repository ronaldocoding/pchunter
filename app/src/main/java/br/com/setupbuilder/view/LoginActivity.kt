package br.com.setupbuilder.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.setupbuilder.R
import br.com.setupbuilder.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.login_activity.*


class LoginActivity : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)



        button_login.setOnClickListener {
            progressBar.visibility = View.VISIBLE
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
        val vmUser = UserViewModel()
        if (email.isEmpty() || password.isEmpty()) {
            if(email.isEmpty())
                email_login.setError("Esse campo não pode ficar vazio.")
            if(password.isEmpty())
                password_login.setError("Esse campo não pode ficar vazio.")
            progressBar.visibility = View.INVISIBLE
            return
        }

        if(vmUser.SignIn(email_login, password_login, this)){
            progressBar.visibility = View.INVISIBLE
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }else{
            progressBar.visibility = View.INVISIBLE
        }

    }



}