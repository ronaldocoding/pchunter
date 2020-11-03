package com.example.setupbuilder.controller

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.setupbuilder.model.Part
import com.example.setupbuilder.model.Setup
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import org.json.JSONArray
import org.json.JSONObject

class APIController {

    public fun searchTerm (term: String, context:Context):String  {
        val queue = Volley.newRequestQueue(context)
        val url = "https://api.rainforestapi.com/request?api_key=7287E35DB65F4D478562FEEAACFCEB22&type=search&amazon_domain=amazon.com.br&search_term="+term+"&sort_by=price_high_to_low"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val obj = JSONObject(response);
                val produtos:ArrayList<Part> = ArrayList();
                val obj1 = obj.get("search_results") as JSONArray;
                for (x in 0 until obj1.length()) {

                    var nome:String = obj1.getJSONObject(x).get("title").toString()

                    produtos.add(Part(nome, "oi", 4.5f, "2020", "opa", "teste"))

                }
                //a partir daqui manipular o array arquivos
                Toast.makeText(context, "Produtos carregados com sucesso", Toast.LENGTH_LONG).show()

            },
            Response.ErrorListener {
                Toast.makeText(context, "alguma coisa deu errado", Toast.LENGTH_LONG).show()
            })


        return queue.add(stringRequest).toString()
    }


}