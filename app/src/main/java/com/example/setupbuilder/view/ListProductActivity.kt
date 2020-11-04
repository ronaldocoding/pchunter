package com.example.setupbuilder.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.`interface`.Callback
import com.example.setupbuilder.adapters.SetupRecyclerAdapter
import com.example.setupbuilder.controller.APIController
import com.example.setupbuilder.model.Part
import com.google.android.gms.common.util.ArrayUtils
import kotlinx.android.synthetic.main.list_product_activity.*

class ListProductActivity: AppCompatActivity(), Callback{
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<SetupRecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_product_activity)

        layoutManager = LinearLayoutManager(this)
        list_product.layoutManager = layoutManager
        adapter = SetupRecyclerAdapter(arrayListOf("Placa-Mãe Asus Prime B450M Gaming/BR"), arrayListOf(649.90), arrayListOf("https://images4.kabum.com.br/produtos/fotos/99504/placa-mae-asus-para-amd-am4-matx-prime-b450m-gaming-br-ddr4__1545143134_gg.jpg"))
        list_product.adapter = adapter
        val api = APIController()
        api.searchTerm("Placa-mãe", this, this)

    }

    override fun onRequestSuccess(produtos: ArrayList<Part>) {
        val nomes: ArrayList<String> = ArrayList();
        val precos: ArrayList<Double> = ArrayList();
        val img: ArrayList<String> = ArrayList();

        for (x in 0 until produtos.size){
            nomes.add(produtos.get(x).name)
            precos.add(produtos.get(x).price.toDouble())
            img.add(produtos.get(x).image)

        }
        adapter = SetupRecyclerAdapter(nomes,precos, img);
        list_product.adapter = adapter

        Toast.makeText(this, "tudo certo", Toast.LENGTH_LONG).show()
    }

    override fun onRequestError() {
        Toast.makeText(this, "tudo errado", Toast.LENGTH_LONG).show()
    }

}