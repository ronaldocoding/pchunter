package com.example.setupbuilder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var user = FirebaseAuth.getInstance().currentUser

        if(user != null) {
            Toast.makeText(this, "User ${user.email}", Toast.LENGTH_LONG).show()
                    var intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
        } else {
            var intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
        }

    }
}