package com.example.setupbuilder.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.ViewPartActivity


class TipsRecyclerAdapter(list:Array<String>) :  RecyclerView.Adapter<TipsRecyclerAdapter.ViewHolder>() {
    private var listItems = list

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var title : TextView
        init{
            title = itemView.findViewById(R.id.tip_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.tips_model, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = listItems[position]

    }

    override fun getItemCount(): Int {
        return listItems.size
    }

}