package br.com.setupbuilder.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.setupbuilder.R
import br.com.setupbuilder.adapters.SetupRecyclerAdapter
import br.com.setupbuilder.controller.SetupController
import br.com.setupbuilder.controller.UserController
import br.com.setupbuilder.model.Setup
import br.com.setupbuilder.view.MenuActivity
import br.com.setupbuilder.view.ViewSetupActivity
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.dialog_with_edittext_setup_creation.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.setup_filter_dialog.view.*
import kotlin.collections.ArrayList as Array


class HomeFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<SetupRecyclerAdapter.ViewHolder>? = null

    val mUser = UserController()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        var setups = SetupController()
        var order = "cresc"
        var filterBy = "time"

        try {
            order = activity?.intent?.getStringExtra("order").toString()
            if (!activity?.intent?.getStringExtra("filterBy").isNullOrBlank()) {
                filterBy = activity?.intent?.getStringExtra("filterBy").toString()
            }
        } catch (e: Exception) {
        }
//        var i = 0

//        if (filterBy.contains("time")) {
//            setups.listSetupsByTime(order)
//                .addOnSuccessListener { documents ->
//                    progressBarSetup?.visibility = View.GONE
//                    for (document in documents) {
//                        val uid = document.get("userUid").toString()
//
//                        if (uid.equals(mUser.getUID())) {
//                            names.add(document.get("name").toString())
//                        }
//                        i++
//                    }
//                    if (i === 0) {
//                        no_setup.visibility = View.VISIBLE
//                        recyclerView.visibility = View.GONE
//                        no_setup.setText("Nenhum Setup criado.\nClique no + para criar.")
//                    } else {
//                        no_setup?.visibility = View.GONE
//                        recyclerView?.visibility = View.VISIBLE
//                    }
//                    layoutManager = LinearLayoutManager(context)
//                    recyclerView?.layoutManager = layoutManager
//                    adapter = SetupRecyclerAdapter(names, null, null, names, null)
//                    recyclerView?.adapter = adapter
//                }.addOnFailureListener {
//                    progressBarSetup?.visibility = View.GONE
//                    no_setup?.visibility = View.VISIBLE
//                    recyclerView?.visibility = View.GONE
//                    no_setup?.setText("Ocorreu um erro, mas não se preocupe, não é culpa sua.\nTente novamente mais tarde.")
//                }
//        } else {
//            setups.listSetupsByPrice(order)
//                .addOnSuccessListener { documents ->
//                    progressBarSetup?.visibility = View.GONE
//                    for (document in documents) {
//                        val uid = document.get("userUid").toString()
//
//                        if (uid.equals(mUser.getUID())) {
//                            names.add(document.get("name").toString())
//                        }
//                        i++
//                    }
//                    if (i === 0) {
//                        no_setup.visibility = View.VISIBLE
//                        recyclerView.visibility = View.GONE
//                        no_setup.setText("Nenhum Setup criado.\nClique no + para criar.")
//                    } else {
//                        no_setup?.visibility = View.GONE
//                        recyclerView?.visibility = View.VISIBLE
//                    }
//                    layoutManager = LinearLayoutManager(context)
//                    recyclerView?.layoutManager = layoutManager
//                    adapter = SetupRecyclerAdapter(names, null, null, names, null)
//                    recyclerView?.adapter = adapter
//                }.addOnFailureListener {
//                    progressBarSetup?.visibility = View.GONE
//                    no_setup?.visibility = View.VISIBLE
//                    recyclerView?.visibility = View.GONE
//                    no_setup?.setText("Ocorreu um erro, mas não se preocupe, não é culpa sua.\nTente novamente mais tarde.")
//                }
//        }


        if (filterBy == "time") {
            setups.listSetupsByTime(order)
                .addOnSuccessListener { documents ->
                    setSetups(documents)
                }.addOnFailureListener {
                    progressBarSetup?.visibility = View.GONE
                    no_setup?.visibility = View.VISIBLE
                    recyclerView?.visibility = View.GONE
                    no_setup?.setText("Ocorreu um erro, mas não se preocupe, não é culpa sua.\nTente novamente mais tarde.")
                }
        } else {
            setups.listSetupsByPrice(order)
                .addOnSuccessListener { documents ->
                    setSetups(documents)
                }.addOnFailureListener {
                    progressBarSetup?.visibility = View.GONE
                    no_setup?.visibility = View.VISIBLE
                    recyclerView?.visibility = View.GONE
                    no_setup?.setText("Ocorreu um erro, mas não se preocupe, não é culpa sua.\nTente novamente mais tarde.")
                }
        }


        setup_filter.setOnClickListener {
            val mDialogView =
                LayoutInflater.from(context).inflate(R.layout.setup_filter_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
            //show dialog
            val mAlertDialog = mBuilder.show()


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
            mDialogView.price_cresc.setOnClickListener {
                mAlertDialog.dismiss()
                val intent = Intent(context, MenuActivity::class.java)
                intent.putExtra("filterBy", "price")
                intent.putExtra("order", "cresc")
                startActivity(intent);
            }

            mDialogView.price_desc.setOnClickListener {
                mAlertDialog.dismiss()
                val intent = Intent(context, MenuActivity::class.java)
                intent.putExtra("filterBy", "price")
                intent.putExtra("order", "desc")
                startActivity(intent);

            }
        }
        fab.setOnClickListener {
            val mDialogView = LayoutInflater.from(context)
                .inflate(R.layout.dialog_with_edittext_setup_creation, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
            //show dialog
            val mAlertDialog = mBuilder.show()
            var user = UserController().getUser();

            mDialogView.submit_dialog.setOnClickListener {
                val dialogText = mDialogView.dialogEditText.text.toString()


                if (dialogText.isEmpty()) {
                    mDialogView.dialogEditText.setError("Este campo não pode ficar vazio")
                } else {

                    var i = 0
                    setups.listSetupsByTime(order).addOnSuccessListener { documents ->
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
                            var setup = user?.uid?.let { it1 ->
                                Setup(
                                    dialogText, FieldValue.serverTimestamp(), it1, 0.0
                                )
                            }

                            if (setup != null) {
                                setups.add(setup).addOnSuccessListener {
                                    Toast.makeText(context, "Adicionado.", Toast.LENGTH_LONG)
                                        .show()
                                    val intent = Intent(context, ViewSetupActivity::class.java)
                                    intent.putExtra("name", "${dialogText}")
                                    startActivity(intent)
                                }.addOnFailureListener {
                                    Toast.makeText(context, it.message, Toast.LENGTH_LONG)
                                        .show()
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

    public fun setSetups(documents: QuerySnapshot?) {
        var i = 0

        var names = Array<String>()

        progressBarSetup?.visibility = View.GONE
        if (documents != null) {
            for (document in documents) {
                val uid = document.get("userUid").toString()

                if (uid.equals(mUser.getUID())) {
                    names.add(document.get("name").toString())
                }
                i++
            }
        }
        if (i === 0) {
            no_setup.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            no_setup.setText("Nenhum Setup criado.\nClique no + para criar.")
        } else {
            no_setup.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }

        layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager
        adapter = SetupRecyclerAdapter(names, null, null, names, null)
        recyclerView?.adapter = adapter
    }

}
