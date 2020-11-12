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
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.list_product_activity.*
import kotlinx.android.synthetic.main.part_filter_dialog.view.*
import kotlinx.android.synthetic.main.setup_filter_dialog.view.*

class ListProductActivity: AppCompatActivity(){
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<SetupRecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_product_activity)

        layoutManager = LinearLayoutManager(this)
        list_product.layoutManager = layoutManager
        adapter = SetupRecyclerAdapter(arrayListOf(), arrayListOf(), arrayListOf(), arrayListOf(), "")
        list_product.adapter = adapter

        val controller = PartController()
        val nomes = ArrayList<String>()
        val precos = ArrayList<String>()
        val imgs = ArrayList<String>()
        val id = ArrayList<String>()
        val peca = intent.getStringExtra("peca").toString()
        search_part.addTextChangedListener{
            val text = search_part.text.toString()
            adapter = SetupRecyclerAdapter(nomes, precos, imgs, id, text)
            list_product.adapter = adapter
        }

        controller.listPartsByTerm(peca, intent.getStringExtra("order").toString()).addOnSuccessListener { response ->

            for(element in response){
                nomes.add(element.data.get("nome").toString())
                precos.add(element.data.get("preco").toString())
                imgs.add(element.data.get("img").toString())
                id.add(element.data.get("asin").toString())
            }


            adapter = SetupRecyclerAdapter(nomes, precos, imgs, id, "")
            list_product.adapter = adapter
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
                startActivity(intent);
            }
            mDialogView.price_cresc_part.setOnClickListener {
                mAlertDialog.dismiss()
                val intent = Intent(this, ListProductActivity::class.java)
                intent.putExtra("order", "asc")
                intent.putExtra("peca", peca)
                startActivity(intent);
            }
        }


    }


}