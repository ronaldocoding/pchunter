package com.example.setupbuilder.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.setupbuilder.NewSetupActivity
import com.example.setupbuilder.R
import com.example.setupbuilder.ViewSetupActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.account_fragment.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList as Array

class HomeFragment : Fragment() {

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

        lista.setOnItemClickListener { parent, view, position, id ->
            context?.let { it1 ->
                val intent = Intent(context, ViewSetupActivity::class.java)
                intent.putExtra("setupName", names.get(id.toInt()).toString())
                startActivity(intent)
//                MaterialAlertDialogBuilder(it1)
//                    .setTitle("Exclusão de ${names.get(id.toInt())}")
//                    .setMessage("Deseja excluir este Setup?")
//                    .setNeutralButton("Não") { dialog, which ->
//                        // Respond to neutral button press
//                    }
//                    .setPositiveButton("Sim") { dialog, which ->
//                        FirebaseFirestore.getInstance().collection("setup").whereEqualTo("name", names.get(id.toInt())).get()
//                            .addOnSuccessListener {documents ->
//                                for (document in documents) {
//                                    document.reference.delete().addOnSuccessListener {
//                                            Toast.makeText(context, "Excluido", Toast.LENGTH_LONG).show()
//                                        names = loadList()
//                                    }.addOnFailureListener {
//                                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
//                                    }
//                                }
//                            }
//                    }
//                    .show()
            }
        }


    }
    fun loadList ():Array<String>{
        val names = arrayListOf<String>()
        var setupAdapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_expandable_list_item_1) }
        lista.adapter = setupAdapter
        setupAdapter?.clear()
        FirebaseFirestore.getInstance().collection("setup").orderBy("timestamp", Query.Direction.ASCENDING).get()
            .addOnSuccessListener {documents ->
                for (document in documents) {
                    names+= document.data.get("name").toString()
                    setupAdapter?.add(document.data.get("name").toString())
                }
            }
        return names
    }
}
