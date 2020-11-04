package com.example.setupbuilder.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.adapters.SetupRecyclerAdapter
import kotlinx.android.synthetic.main.list_product_activity.*

class ListProductActivity: AppCompatActivity(){
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<SetupRecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_product_activity)

        layoutManager = LinearLayoutManager(this)
        list_product.layoutManager = layoutManager
        adapter = SetupRecyclerAdapter(arrayListOf("Placa-Mãe Asus Prime B450M Gaming/BR"), arrayListOf(649.90), arrayListOf("https://images4.kabum.com.br/produtos/fotos/99504/placa-mae-asus-para-amd-am4-matx-prime-b450m-gaming-br-ddr4__1545143134_gg.jpg"))
        list_product.adapter = adapter

    }

}