package com.example.setupbuilder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R


class ComponentRecyclerAdapter :  RecyclerView.Adapter<ComponentRecyclerAdapter.ViewHolder>() {
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

    inner class ViewHolder(ItemView:View) : RecyclerView.ViewHolder(ItemView) {
        var title : TextView
        init{
            title = itemView.findViewById(R.id.partTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.partview1_model, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var add = "Adicionar "
        var vazio = "Nenhum "
        holder.title.text = add + ItemTitles[position]

    }

    override fun getItemCount(): Int {
        return ItemTitles.size
    }

}