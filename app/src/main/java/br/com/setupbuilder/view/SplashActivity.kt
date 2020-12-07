package br.com.setupbuilder.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.setupbuilder.controller.UserController

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = UserController()

        if (repository.isAuthenticated()) {
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}