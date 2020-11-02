package com.example.setupbuilder.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.ViewPartActivity
import com.example.setupbuilder.ViewSetupActivity


class PartRecyclerAdapter() :  RecyclerView.Adapter<PartRecyclerAdapter.ViewHolder>() {
    //Nome do componente
    private val ItemTitles = arrayOf(
        "CPU",
        "Placa-mãe",
        "RAM",
        "Placa de vídeo",
        "Armazenamento",
        "Cooler de CPU",
        "Sistema Operacional",
        "Monitor",
        "Teclado",
        "Mouse"
    )
    //Preço
    //Informação 1
    //Informação 2


    inner class ViewHolder(ItemView:View) : RecyclerView.ViewHolder(ItemView) {
        var title : TextView
        init{
            title = itemView.findViewById(R.id.partTitle2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.partview2_model, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = ItemTitles[position]
        holder.title.setOnClickListener {
            val intent = Intent(it.context, ViewPartActivity::class.java)
            intent.putExtra("id", position)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return ItemTitles.size
    }

}