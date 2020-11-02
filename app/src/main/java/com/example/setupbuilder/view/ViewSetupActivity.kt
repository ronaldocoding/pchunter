package com.example.setupbuilder.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.adapters.ComponentRecyclerAdapter
import kotlinx.android.synthetic.main.view_setup_activity.*

class ViewSetupActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<ComponentRecyclerAdapter.ViewHolder>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_setup_activity)

        layoutManager = LinearLayoutManager(this)
        recyclerViewComp.layoutManager = layoutManager

        adapter = ComponentRecyclerAdapter()
        recyclerViewComp.adapter = adapter

//        setupName.setText(intent.getStringExtra("setupName"))
//        inputName.setText(intent.getStringExtra("setupName"))
//
//        inputName.setOnKeyListener(View.OnKeyListener { view, keyCode, event ->
//            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
//                pbEditSetup.visibility = View.VISIBLE
//                val setups = FirebaseFirestore.getInstance().collection("setup")
//                setups.get().addOnSuccessListener { documents ->
//                    var error = false
//
//                    if(inputName.text.toString().isEmpty()){
//                        inputName.setError("Esse campo não pode ficar vazio")
//                        error = true
//                        pbEditSetup.visibility = View.INVISIBLE
//                    }else {
//                        for(document in documents){
//                            if(document.data.get("name").toString().equals(inputName.text.toString()) &&
//                                !setupName.text.toString().equals(inputName.text.toString())){
//                                inputName.setError("Nome já existente")
//                                error = true
//                                pbEditSetup.visibility = View.INVISIBLE
//                            }
//                        }
//                    }
//
//                    if (!error){
//                        FirebaseFirestore.getInstance().collection("setup").whereEqualTo("name", intent.getStringExtra("setupName")).get()
//                            .addOnSuccessListener {documents ->
//                                for (document in documents) {
//                                    //Atualizar um campo
//                                    setupName.setText(inputName.text.toString())
//                                    document.reference.update("name", inputName.text.toString())
//                                    inputName.visibility = View.GONE
//                                    setupName.visibility = View.VISIBLE
//                                    pbEditSetup.visibility = View.INVISIBLE
//                                }
//                            }.addOnFailureListener {
//                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                                pbEditSetup.visibility = View.INVISIBLE
//                            }
//                    }
//                }.addOnFailureListener {
//                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                        pbEditSetup.visibility = View.INVISIBLE
//                    }
//
//            }
//            false
//        })
//
//        editName.setOnClickListener {
//            inputName.visibility = View.VISIBLE
//            setupName.visibility = View.GONE
//        }
//        deleteSetup.setOnClickListener {
//            FirebaseFirestore.getInstance().collection("setup")
//                .whereEqualTo("name", intent.getStringExtra("setupName")).get()
//                .addOnSuccessListener { documents ->
//                    for (document in documents) {
//                        document.reference.delete().addOnSuccessListener {
//                            startActivity(Intent(this, MenuActivity::class.java))
//                        }.addOnFailureListener {
//                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                        }
//                    }
//                }
//        }
    }
}



