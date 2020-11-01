package com.example.setupbuilder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.setupbuilder.model.UserModel

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mUser = UserModel()

        if(mUser.isAuthenticated()) {
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