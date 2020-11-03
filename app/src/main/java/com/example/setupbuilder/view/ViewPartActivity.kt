package com.example.setupbuilder.view

import android.app.DownloadManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.setupbuilder.R
import com.example.setupbuilder.adapters.TipsRecyclerAdapter
import com.example.setupbuilder.controller.APIController
import com.example.setupbuilder.model.Part
import com.example.setupbuilder.model.Setup
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import kotlinx.android.synthetic.main.new_setup_activity.*
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

        //val api = APIController()
        //api.searchTerm("Mouse", this)
        
    }


}