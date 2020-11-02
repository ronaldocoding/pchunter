package com.example.setupbuilder.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.ViewSetupActivity


class SetupRecyclerAdapter(val names: ArrayList<String>) :  RecyclerView.Adapter<SetupRecyclerAdapter.ViewHolder>() {
    //Nome do Setup
    private val ItemTitles = arrayOf("Build barata", "Build cara")

    inner class ViewHolder(ItemView:View) : RecyclerView.ViewHolder(ItemView) {
        var title : TextView
        var infoOne : TextView
        var infoTwo : TextView
        var card : LinearLayout

        init{
            title = itemView.findViewById(R.id.cardTitle)
            infoOne = itemView.findViewById(R.id.cardInfoOne)
            infoTwo = itemView.findViewById(R.id.cardInfoTwo)
            card = itemView.findViewById(R.id.setupCard)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_model, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = names.get(position)
        holder.infoOne.text = "CPU vazia"
        holder.infoTwo.text = "GPU vazia"

        holder.card.setOnClickListener {
            val intent = Intent(it.context, ViewSetupActivity::class.java)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return names.size
    }

}