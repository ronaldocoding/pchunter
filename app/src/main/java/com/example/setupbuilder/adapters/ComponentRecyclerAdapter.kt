package com.example.setupbuilder.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.view.ListProductActivity


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
        var card : ConstraintLayout
        init{
            card = itemView.findViewById(R.id.compCard)
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
        holder.card.setOnClickListener {
            val intent = Intent(it.context, ListProductActivity::class.java)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return ItemTitles.size
    }

}