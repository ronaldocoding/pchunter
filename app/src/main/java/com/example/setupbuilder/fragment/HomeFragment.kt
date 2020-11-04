package com.example.setupbuilder.fragment

import android.app.AlertDialog
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.adapters.SetupRecyclerAdapter
import com.example.setupbuilder.controller.SetupController
import com.example.setupbuilder.controller.UserController
import com.example.setupbuilder.model.Setup
import com.example.setupbuilder.view.MenuActivity
import com.example.setupbuilder.view.ViewSetupActivity
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.dialog_with_edittext_setup_creation.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.setup_filter_dialog.*
import kotlinx.android.synthetic.main.setup_filter_dialog.view.*
import java.lang.NullPointerException
import kotlin.collections.ArrayList as Array


class HomeFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<SetupRecyclerAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        val mUser = UserController()
        var names = Array<String>()
        var setups = SetupController()
        var order = "cresc"
        var filterBy ="time"
        try{
            order = activity?.intent?.getStringExtra("order").toString()
            filterBy = activity?.intent?.getStringExtra("filterBy").toString()
        }catch (e:Exception){}

        var i = 0
        setups.listSetupsByTime(order)
            .addOnSuccessListener { documents ->
                progressBarSetup.visibility=View.GONE
                for (document in documents) {
                    val uid = document.get("userUid").toString()

                    if (uid.equals(mUser.getUID())) {
                        names.add(document.get("name").toString())
                    }
                    i++
                }
                if(i===0){
                    no_setup.visibility=View.VISIBLE
                    recyclerView.visibility =View.GONE
                }else{
                    no_setup.visibility=View.GONE
                    recyclerView.visibility =View.VISIBLE
                }
                layoutManager = LinearLayoutManager(context)
                recyclerView.layoutManager = layoutManager
                adapter = SetupRecyclerAdapter(names, null, null)
                recyclerView.adapter = adapter
            }


        setup_filter.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.setup_filter_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
            //show dialog
            val  mAlertDialog = mBuilder.show()

            mDialogView.time_desc.setOnClickListener {
                mAlertDialog.dismiss()
                val intent = Intent(context, MenuActivity::class.java)
                intent.putExtra("filterBy", "time")
                intent.putExtra("order", "desc")
                startActivity(intent);
            }

            mDialogView.time_cresc.setOnClickListener {
                mAlertDialog.dismiss()
                val intent = Intent(context, MenuActivity::class.java)
                intent.putExtra("filterBy", "time")
                intent.putExtra("order", "cresc")
                startActivity(intent);
            }
        }
        fab.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_with_edittext_setup_creation, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
            //show dialog
            val  mAlertDialog = mBuilder.show()
            var user = UserController().getUser();

            mDialogView.submit_dialog.setOnClickListener {
                val dialogText = mDialogView.dialogEditText.text.toString()

                if(dialogText.isEmpty()){
                    mDialogView.dialogEditText.setError("Este campo não pode ficar vazio")
                }else{

                    var i = 0
                    setups.listSetupsByTime(order).addOnSuccessListener { documents ->
                        var equal = false

                        for(document in documents){
                            if(document.data.get("name").toString().equals(dialogText)){
                                mDialogView.dialogEditText.setError("Nome já existente")
                                equal = true
                            }
                            i++
                        }

                        if (!equal){
                            mAlertDialog.dismiss()
                            var setup = user?.uid?.let { it1 ->
                                Setup(
                                    dialogText, FieldValue.serverTimestamp(), it1
                                )
                            }

                            if (setup != null) {
                                setups.add(setup).addOnSuccessListener {
                                    Toast.makeText(context, "Adicionado.", Toast.LENGTH_LONG).show()
                                    val intent = Intent(context, ViewSetupActivity::class.java)
                                    intent.putExtra("name", "${dialogText}")
                                    startActivity(intent)
                                }.addOnFailureListener {
                                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }

                }
            }
            mDialogView.cancel_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }


    }

}
