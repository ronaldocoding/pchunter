package br.com.setupbuilder.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.setupbuilder.R
import br.com.setupbuilder.controller.PartController
import br.com.setupbuilder.controller.SetupController
import br.com.setupbuilder.view.ListProductActivity
import br.com.setupbuilder.view.ViewProductActivity
import br.com.setupbuilder.view.ViewSetupActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_exclusion.view.*


class ComponentRecyclerAdapter(val setupName: String) :  RecyclerView.Adapter<ComponentRecyclerAdapter.ViewHolder>() {
    private val ItemTitles = arrayOf(
        "CPU",
        "Placa-mãe",
        "Memória RAM",
        "Placa de vídeo",
        "Disco de Armazenamento (Storage)",
        "Monitor",
        "Teclado",
        "Mouse",
        "Headset",
        "Fonte de alimentação",
        "Gabinete"
    )
    private val keys = arrayOf(
        "CPU",
        "placa-mãe",
        "Memória RAM",
        "Placa de vídeo",
        "hd externo",
        "Monitor",
        "Teclado",
        "Mouse",
        "Headset",
        "Fonte de alimentação",
        "Gabinete"
    )

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var title : TextView
        var name : TextView
        var add : TextView
        var card : ConstraintLayout
        var photo : ImageView
        var price : TextView
        var delete : View
        init{
            card = itemView.findViewById(R.id.compCard)
            title = itemView.findViewById(R.id.partTitle)
            photo = itemView.findViewById(R.id.compImage)
            add = itemView.findViewById(R.id.partName)
            name = itemView.findViewById(R.id.compTitle)
            price = itemView.findViewById(R.id.compPrice)
            delete = itemView.findViewById(R.id.compDelete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.partview1_model, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var add = "Adicionar "
        holder.title.text = add + ItemTitles[position]
        val cSetup = SetupController()
        val cPart = PartController()



        cSetup.listSetupsByTime("cresc").addOnSuccessListener { documents->
                for(document in documents){

                    if(document.data.get(keys[position])!== null && setupName.equals(
                            document.data.get(
                                "name"
                            ).toString()
                        )){
                        cPart.listPartsByAsin(document.data.get(keys[position]).toString()).addOnSuccessListener { documents->
                                for(document in documents){
                                    holder.add.visibility = View.GONE
                                    holder.photo.visibility = View.VISIBLE
                                    holder.name.visibility = View.VISIBLE
                                    holder.price.visibility = View.VISIBLE
                                    holder.delete.visibility=View.VISIBLE
                                    holder.title.text = ItemTitles[position]
                                    holder.name.text =  document.data.get("nome").toString()
                                    holder.price.text = "R$ " + document.data.get("preco").toString()
                                    Picasso.get()
                                        .load(document.data.get("img").toString())
                                        .placeholder(R.drawable.round_square)
                                        .into(holder.photo);

                                    holder.delete.setOnClickListener {
                                        val mDialogView = LayoutInflater.from(it.context)
                                            .inflate(R.layout.dialog_exclusion, null)
                                        //AlertDialogBuilder
                                        val mBuilder = AlertDialog.Builder(it.context)
                                            .setView(mDialogView)
                                        //show dialog
                                        val mAlertDialog = mBuilder.show()

                                        mDialogView.exclusion_title.setText("Remoção de " + ItemTitles[position])
                                        mDialogView.exclusion_text.setText("Tem certeza que deseja remover este componente? Poderá adicioná-lo novamente procurando-o na lista de produtos.")
                                        mDialogView.confirm_dialog.setOnClickListener {
                                            cSetup.deletePart(
                                                document.data.get("produto").toString(), document.data.get("preco") as Double ,setupName
                                            )
                                            holder.title.text = add + ItemTitles[position]
                                            holder.add.visibility = View.VISIBLE
                                            holder.photo.visibility = View.GONE
                                            holder.name.visibility = View.GONE
                                            holder.price.visibility = View.GONE
                                            holder.delete.visibility=View.GONE
                                            val refresh =
                                                Intent(it.context, ViewSetupActivity::class.java)
                                            refresh.putExtra("name", setupName)
                                            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                            it.context.startActivity(refresh)

                                            mAlertDialog.dismiss()
                                        }
                                        mDialogView.cancel_dialog_exclusion.setOnClickListener {
                                            mAlertDialog.dismiss()
                                        }

                                    }
                                    holder.card.setOnClickListener {

                                            val intent = Intent(it.context, ViewProductActivity::class.java)
                                            intent.putExtra("name",  document.data.get("asin").toString())
                                            it.context.startActivity(intent)


                                    }
                                }


                        }

                    }else{
                        holder.add.visibility = View.VISIBLE
                        holder.photo.visibility = View.GONE
                        holder.name.visibility = View.GONE
                        holder.price.visibility = View.GONE
                        holder.delete.visibility=View.GONE
                        holder.card.setOnClickListener {

                            val intent = Intent(it.context, ListProductActivity::class.java)
                            intent.putExtra("peca", keys[position])
                            intent.putExtra("setup", setupName)
                            it.context.startActivity(intent)

                        }
                    }
                }
        }



    }

    override fun getItemCount(): Int {
        return ItemTitles.size
    }

}