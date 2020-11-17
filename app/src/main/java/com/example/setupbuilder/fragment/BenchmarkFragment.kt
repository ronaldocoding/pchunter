package com.example.setupbuilder.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.adapters.PartRecyclerAdapter
import com.example.setupbuilder.view.CpuBenchmarkActivity
import com.example.setupbuilder.view.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.benchmark_fragment.*
import kotlinx.android.synthetic.main.part_fragment.*
import kotlin.collections.ArrayList as Array

class BenchmarkFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.benchmark_fragment, container, false)
    }



    override fun onStart() {
        super.onStart()

        cpu_benchmark_button.setOnClickListener {
            val intent = Intent(context, CpuBenchmarkActivity::class.java)
            startActivity(intent)
        }
    }
}
