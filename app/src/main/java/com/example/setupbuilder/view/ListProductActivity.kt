package com.example.setupbuilder.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.adapters.SetupRecyclerAdapter
import com.example.setupbuilder.controller.PartController
import com.example.setupbuilder.controller.SetupController
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.list_product_activity.*
import kotlinx.android.synthetic.main.part_filter_dialog.view.*
import kotlinx.android.synthetic.main.setup_filter_dialog.view.*

class ListProductActivity: AppCompatActivity(){
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<SetupRecyclerAdapter.ViewHolder>? = null
    val controller = PartController()
    val setupController = SetupController()
    val nomes = ArrayList<String>()
    val precos = ArrayList<String>()
    val imgs = ArrayList<String>()
    val id = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_product_activity)

        layoutManager = LinearLayoutManager(this)
        list_product.layoutManager = layoutManager
        adapter = SetupRecyclerAdapter(arrayListOf(), arrayListOf(), arrayListOf(), arrayListOf(), intent.getStringExtra("setup").toString())

        list_product.adapter = adapter

        var setup:String? = null
        if(intent.getStringExtra("setup")!= null) {
            setup = intent.getStringExtra("setup")
        }


//        controller.listPartsByTerm(intent.getStringExtra("peca").toString()).addOnSuccessListener { response ->

        val peca = intent.getStringExtra("peca").toString()
        search_part.addTextChangedListener{
            search()
        }


        controller.listPartsByTerm(peca, intent.getStringExtra("order").toString()).addOnSuccessListener { response ->
            for (element in response) {

                nomes.add(element.data.get("nome").toString())
                precos.add(element.data.get("preco").toString())
                imgs.add(element.data.get("img").toString())
                id.add(element.data.get("asin").toString())
            }

            adapter = SetupRecyclerAdapter(nomes, precos, imgs, id, intent.getStringExtra("setup").toString())
            list_product.adapter = adapter

            if(intent.getStringExtra("search") != null && intent.getStringExtra("search") != "") {
                search_part.setText(intent.getStringExtra("search"))
                search()
            }
            else
                search_part.setText("")



        }
        part_filter.setOnClickListener{
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.part_filter_dialog, null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
            val mAlertDialog = mBuilder.show()


            mDialogView.price_desc_part.setOnClickListener {
                mAlertDialog.dismiss()
                val intent = Intent(this, ListProductActivity::class.java)
                intent.putExtra("order", "desc")
                intent.putExtra("peca", peca)
                intent.putExtra("search", search_part.text.toString())
                startActivity(intent);
            }
            mDialogView.price_cresc_part.setOnClickListener {
                mAlertDialog.dismiss()
                val intent = Intent(this, ListProductActivity::class.java)
                intent.putExtra("order", "asc")
                intent.putExtra("peca", peca)
                intent.putExtra("search", search_part.text.toString())
                startActivity(intent);
            }
        }

    }

    public fun search(){
        val nomesS = ArrayList<String>()
        val precosS = ArrayList<String>()
        val imgsS = ArrayList<String>()
        val idS = ArrayList<String>()
        val text = search_part.text.toString()
        if(text == "")
            adapter = SetupRecyclerAdapter(nomes, precos, imgs, id, intent.getStringExtra("setup").toString())
        else{
            for (i in 0..nomes.size-1){
                if (nomes.get(i).toUpperCase().contains(text.toUpperCase())){
                    nomesS.add(nomes.get(i))
                    precosS.add(precos.get(i))
                    imgsS.add(imgs.get(i))
                    idS.add(id.get(i))
                }
            }
            adapter = SetupRecyclerAdapter(nomesS, precosS, imgsS, idS, intent.getStringExtra("setup").toString())

        }
        list_product.adapter = adapter

    }

}