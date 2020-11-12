package com.example.setupbuilder.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.adapters.SetupRecyclerAdapter
import com.example.setupbuilder.controller.PartController
import kotlinx.android.synthetic.main.list_product_activity.*

class ListProductActivity: AppCompatActivity(){
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<SetupRecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_product_activity)

        layoutManager = LinearLayoutManager(this)
        list_product.layoutManager = layoutManager
        adapter = SetupRecyclerAdapter(arrayListOf(), arrayListOf(), arrayListOf())
        list_product.adapter = adapter
        val controller = PartController()
        val nomes = ArrayList<String>()
        val precos = ArrayList<String>()
        val imgs = ArrayList<String>()
        controller.listPartsByTerm(intent.getStringExtra("peca").toString()).addOnSuccessListener { response ->

            for(element in response){
                nomes.add(element.data.get("nome").toString())
                precos.add(element.data.get("preco").toString())
                imgs.add(element.data.get("img").toString())
            }


            adapter = SetupRecyclerAdapter(nomes, precos, imgs)
            list_product.adapter = adapter
        }.addOnFailureListener {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }

    }


}