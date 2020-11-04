package com.example.setupbuilder.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.adapters.ComponentRecyclerAdapter
import com.example.setupbuilder.controller.SetupController
import com.example.setupbuilder.controller.UserController
import com.example.setupbuilder.model.Setup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.dialog_exclusion.view.*
import kotlinx.android.synthetic.main.dialog_with_edittext_setup_creation.view.*
import kotlinx.android.synthetic.main.dialog_with_edittext_setup_creation.view.text_dialog
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_setup_activity.*
import kotlinx.android.synthetic.main.view_setup_activity.view.*

class ViewSetupActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ComponentRecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_setup_activity)

        layoutManager = LinearLayoutManager(this)
        recyclerViewComp.layoutManager = layoutManager

        adapter = ComponentRecyclerAdapter()
        recyclerViewComp.adapter = adapter
        var setups = SetupController()

        name.setText(intent.getStringExtra("name"))
        name.setOnClickListener {
            val mDialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_with_edittext_setup_creation, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            //show dialog
            val mAlertDialog = mBuilder.show()

            mDialogView.text_dialog.setText("Editar nome")
            mDialogView.dialogEditText.setText(intent.getStringExtra("name"))
            mDialogView.submit_dialog.setText("Editar")
            mDialogView.submit_dialog.setOnClickListener {
                val dialogText = mDialogView.dialogEditText.text.toString()

                if (dialogText.isEmpty()) {
                    mDialogView.dialogEditText.setError("Este campo não pode ficar vazio")
                } else {

                    var i = 0
                    setups.listSetups().addOnSuccessListener { documents ->
                        var equal = false

                        for (document in documents) {
                            if (document.data.get("name").toString().equals(dialogText)) {
                                mDialogView.dialogEditText.setError("Nome já existente")
                                equal = true
                            }
                            i++
                        }

                        if (!equal) {
                            mAlertDialog.dismiss()
                            FirebaseFirestore.getInstance().collection("setup")
                                .whereEqualTo("name", intent.getStringExtra("name")).get()
                                .addOnSuccessListener { documents ->
                                    for (document in documents) {
                                        //Atualizar um campo
                                        name.setText(dialogText)
                                        document.reference.update("name", dialogText)
                                    }
                                }.addOnFailureListener {
                                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                                }
                        }
                    }

                }
            }
            mDialogView.cancel_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        delete_setup.setOnClickListener {
            val mDialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_exclusion, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            //show dialog
            val mAlertDialog = mBuilder.show()

            mDialogView.exclusion_title.setText("Exclusão de Setup")
            mDialogView.exclusion_text.setText("Tem certeza que deseja excluir este Setup? Não será possível recupera-lo posteriormente.")
            mDialogView.confirm_dialog.setOnClickListener {
                mAlertDialog.dismiss()
                FirebaseFirestore.getInstance().collection("setup")
                    .whereEqualTo("name", intent.getStringExtra("name")).get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            document.reference.delete().addOnSuccessListener {
                                startActivity(Intent(this, MenuActivity::class.java))
                            }.addOnFailureListener {
                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                            }
                        }
                    }
            }
            mDialogView.cancel_dialog_exclusion.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }

    }
}



