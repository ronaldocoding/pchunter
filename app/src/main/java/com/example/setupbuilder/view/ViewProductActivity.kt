package com.example.setupbuilder.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.setupbuilder.R
import com.example.setupbuilder.controller.PartController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_product_activity.*

class ViewProductActivity: AppCompatActivity() {
    var url:String=""
    var infos:String = "Características: "
    var value:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_product_activity)
        val controller = PartController()
        controller.listPartsByAsin(intent.getStringExtra("name").toString()).addOnSuccessListener { response ->
            for (el in response){
                url = el.data.get("url").toString()
                product_price.setText(el.data.get("preco").toString())
                id_product.setText(el.data.get("nome").toString())
                val keys = el.data.keys

                for(key in keys){
                    if(key != "img" && key != "url" && key != "preco" && key != "nome" && key != "produto") {
                        value = el.data.get(key).toString()
                        infos += key + ": " + value + " - "
                    }
                }
                caracteristicas.text = infos
                Picasso.get()
                    .load( el.data.get("img").toString())
                    .into(product_image);
            }

        }

    }

    fun getUrlFromIntent(view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}