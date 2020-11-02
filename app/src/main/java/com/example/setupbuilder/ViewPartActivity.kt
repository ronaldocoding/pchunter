package com.example.setupbuilder

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.adapters.PartRecyclerAdapter
import com.example.setupbuilder.adapters.SetupRecyclerAdapter
import com.example.setupbuilder.adapters.TipsRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_part_activity.*

class ViewPartActivity: AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<TipsRecyclerAdapter.ViewHolder>?=null

    private val names = arrayOf(
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

    private val definitions = arrayOf(
        "CPU é a sigla para Central Process Unit, ou Unidade Central de Processamento."+
                "Ele é o principal item de hardware do computador, que também é conhecido como processador. A CPU é responsável por calcular e realizar tarefas determinadas pelo usuário e é considerado o cérebro do PC.",
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

    private val photos = arrayOf(
        "CPU é a sigla para Central Process Unit, ou Unidade Central de Processamento."+
                "Ele é o principal item de hardware do computador, que também é conhecido como processador. A CPU é responsável por calcular e realizar tarefas determinadas pelo usuário e é considerado o cérebro do PC.",
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

    private val tips = arrayOf(
        arrayOf("Isso aqui", "Isso ali"),
        arrayOf("Isso aqui", "Isso ali"),
        arrayOf("Isso aqui", "Isso ali"),
        arrayOf("Isso aqui", "Isso ali"),
        arrayOf("Isso aqui", "Isso ali"),
        arrayOf("Isso aqui", "Isso ali"),
        arrayOf("Isso aqui", "Isso ali"),
        arrayOf("Isso aqui", "Isso ali"),
        arrayOf("Isso aqui", "Isso ali"),
        arrayOf("Isso aqui", "Isso ali"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_part_activity)
        var pos =  intent.getIntExtra("id", 0)

        compName.text = names[pos]
        definition_text.text=definitions[pos]


        layoutManager = LinearLayoutManager(this)
        tips_list.layoutManager = layoutManager

        adapter = TipsRecyclerAdapter(tips[pos])
        tips_list.adapter = adapter
    }

}