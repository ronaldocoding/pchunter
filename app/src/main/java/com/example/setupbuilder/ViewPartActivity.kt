package com.example.setupbuilder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.view_part_activity.*

class ViewPartActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_part_activity)
        compName.text = intent.getStringExtra("nome")
    }
}