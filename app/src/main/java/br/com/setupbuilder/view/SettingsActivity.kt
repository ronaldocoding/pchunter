package br.com.setupbuilder.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.setupbuilder.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_fragment)
    }
}