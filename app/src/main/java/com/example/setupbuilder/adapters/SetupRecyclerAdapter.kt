package com.example.setupbuilder.adapters

import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.controller.SetupController
import com.example.setupbuilder.view.ListProductActivity
import com.example.setupbuilder.view.ViewProductActivity
import com.example.setupbuilder.view.ViewSetupActivity
import com.squareup.picasso.Picasso

class SetupRecyclerAdapter(val names: ArrayList<String>, val price: ArrayList<String>?, val urls:ArrayList<String>?, val ids:ArrayList<String>?,val setupName:String?) :  RecyclerView.Adapter<SetupRecyclerAdapter.ViewHolder>() {

    //Nome do Setup
    private val ItemTitles = arrayOf("Build barata", "Build cara")

    inner class ViewHolder(ItemView:View) : RecyclerView.ViewHolder(ItemView) {
        var title : TextView
        var infoOne : TextView
        var infoTwo : TextView
        var card : LinearLayout
        var image : ImageView
        var priceText : TextView

        init{
            title = itemView.findViewById(R.id.cardTitle)
            infoOne = itemView.findViewById(R.id.cardInfoOne)
            infoTwo = itemView.findViewById(R.id.cardInfoTwo)
            card = itemView.findViewById(R.id.setupCard)
            image = itemView.findViewById(R.id.setupImage)
            priceText=itemView.findViewById(R.id.cardPrice)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_model, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val setups = SetupController()
        setups.getByName(names.get(position).toString()).addOnSuccessListener { response ->
            for (r in response){
                holder.priceText.text = "R$ "+r.data.get("preco").toString()
            }
        }

        holder.title.text = names.get(position)
        holder.infoOne.text = "CPU vazia"
        holder.infoTwo.text = "GPU vazia"
        if(price!==null){
            holder.image.visibility=View.VISIBLE
            holder.infoOne.visibility=View.GONE
            holder.priceText.text=price.get(position).toString()
            holder.infoTwo.visibility=View.GONE
            holder.card.setOnClickListener {
                val intent = Intent(it.context, ViewProductActivity::class.java)
                if (ids != null) {
                    intent.putExtra("name", ids.get(position))
                }
                if(setupName!= null){
                    intent.putExtra("setup", setupName)
                }
                it.context.startActivity(intent)
            }

            Picasso.get()
                .load(urls?.get(position).toString())
                .into(holder.image);
        }else{
            holder.card.setOnClickListener {
                val intent = Intent(it.context, ViewSetupActivity::class.java)
                if (ids != null) {

                    intent.putExtra("name", ids.get(position))
                }
                it.context.startActivity(intent)
            }
        }




    }

    override fun getItemCount(): Int {
        return names.size
    }

}