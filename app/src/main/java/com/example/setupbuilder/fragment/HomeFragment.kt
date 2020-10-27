package com.example.setupbuilder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.adapters.SetupRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.collections.ArrayList as Array

class HomeFragment : Fragment() {

    private var layoutManager:RecyclerView.LayoutManager?=null
    private var adapter:RecyclerView.Adapter<SetupRecyclerAdapter.ViewHolder>?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        var names = loadList()
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        adapter = SetupRecyclerAdapter("Setup")
        recyclerView.adapter = adapter

//        lista.setOnItemClickListener { parent, view, position, id ->
//            context?.let { it1 ->
//                val intent = Intent(context, ViewSetupActivity::class.java)
//                intent.putExtra("setupName", names.get(id.toInt()).toString())
//                startActivity(intent)
//
//            }
//        }


    }
    fun loadList ():Array<String>{

        val user = FirebaseAuth.getInstance().currentUser
        val names = arrayListOf<String>()
        var setupAdapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_expandable_list_item_1) }
//        lista.adapter = setupAdapter
        setupAdapter?.clear()
        FirebaseFirestore.getInstance().collection("setup")
            .orderBy("timestamp", Query.Direction.ASCENDING).get()
            .addOnSuccessListener {documents ->

                for (document in documents) {
                    val equal = user?.uid?.equals(document.data.get("userUid").toString())
                    if(equal!!){
                        names+= document.data.get("name").toString()
                        setupAdapter?.add(document.data.get("name").toString())
                    }

                }
            }
        return names
    }
}
