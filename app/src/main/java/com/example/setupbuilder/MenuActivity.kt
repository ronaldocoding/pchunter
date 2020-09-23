package com.example.setupbuilder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.setupbuilder.fragment.AccountFragment
import com.example.setupbuilder.fragment.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.account_fragment.*
import kotlinx.android.synthetic.main.bottom_bar.*
import kotlinx.android.synthetic.main.register_activity.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_bar)

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        val homeFragment = HomeFragment()
        val accountFragment = AccountFragment()
        makeCurrentFragment(homeFragment)


        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> makeCurrentFragment(homeFragment)
                R.id.navigation_account -> makeCurrentFragment(accountFragment)
            }

            true
        }

        fab.setOnClickListener {
            val intent = Intent(this, NewSetupActivity::class.java)
            startActivity(intent)
        }

    }

    private fun makeCurrentFragment(frag : Fragment){
        try {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_wrapper, frag)
                addToBackStack(null)
                commit()
            }
        }catch(error:Exception){

        }

    }
}

