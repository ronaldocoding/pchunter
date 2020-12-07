package br.com.setupbuilder.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.setupbuilder.R
import br.com.setupbuilder.fragment.AccountFragment
import br.com.setupbuilder.fragment.BenchmarkFragment
import br.com.setupbuilder.fragment.HomeFragment
import br.com.setupbuilder.fragment.PartFragment
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.bottom_bar.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_bar)

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        val homeFragment = HomeFragment()
        val accountFragment = AccountFragment()
        val benchmarkFragment = BenchmarkFragment()
        val partFragment = PartFragment()
        makeCurrentFragment(homeFragment)


        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> makeCurrentFragment(homeFragment)
                R.id.navigation_account -> makeCurrentFragment(accountFragment)
                R.id.benchmark_part -> makeCurrentFragment(benchmarkFragment)
                R.id.navigation_part -> makeCurrentFragment(partFragment)
            }

            true
        }

//        fab.setOnClickListener {
//            val intent = Intent(this, NewSetupActivity::class.java)
//            startActivity(intent)
//        }

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

