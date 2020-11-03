package com.example.setupbuilder.controller

import android.annotation.SuppressLint
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

    @SuppressLint("WrongConstant")
    public fun searchTerm (term: String, context:Context):String  {
        val queue = Volley.newRequestQueue(context)

        val url =
            "https://api.rainforestapi.com/request?api_key=7BD8CF5031B04EE3843065EA60BA56FA&type=search&amazon_domain=amazon.com&search_term=$term&sort_by=price_high_to_low"
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val obj = JSONObject(response);
                val produtos:ArrayList<Part> = ArrayList();
                val obj1 = obj.get("search_results") as JSONArray;
                for (x in 0 until obj1.length()) {

                    try {
                        var nome:String = obj1.getJSONObject(x).get("title").toString()
                        var preco:Int = obj1.getJSONObject(x).getJSONObject("price").getInt("value");
                        var image = obj1.getJSONObject(x).get("image").toString()
                        produtos.add(Part(nome, "oi", preco.toFloat(), "2020", "opa", "teste", image))
                        Toast.makeText(context, nome+preco, Toast.LENGTH_LONG).show()
                    }
                    catch (e: Exception){

                    }

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