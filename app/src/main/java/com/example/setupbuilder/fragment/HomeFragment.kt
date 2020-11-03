package com.example.setupbuilder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.adapters.SetupRecyclerAdapter
import com.example.setupbuilder.controller.UserController
import com.google.firebase.firestore.FirebaseFirestore
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
        val mUser = UserController()
        var names = Array<String>()

        FirebaseFirestore.getInstance().collection("setup").get().addOnSuccessListener {
            documents->
            for(document in documents){
                val uid = document.get("userUid").toString()

                if(uid.equals(mUser.getUID())){
                    names.add(document.get("name").toString())
                }
            }
            layoutManager = LinearLayoutManager(context)
            recyclerView.layoutManager = layoutManager
            adapter = SetupRecyclerAdapter(names)
            recyclerView.adapter = adapter
        }



    }

}
