package com.example.setupbuilder.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.setupbuilder.LoginActivity
import com.example.setupbuilder.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.account_fragment.*

class AccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.account_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
//        Toast.makeText(context, user?.email, Toast.LENGTH_LONG).show()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        sign_out.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(context, LoginActivity::class.java))
        }


        FirebaseFirestore.getInstance().collection("users").document(user?.uid.toString()).get()
            .addOnSuccessListener { document ->
                home_text1?.setText("${document.data?.get("name")}")
                name_home_input?.setText("${document.data?.get("name")}")

            }
        home_text2.setText("${user?.email}")

        //Editar nome
        update_button.setOnClickListener {
            if (name_home_input.text.toString().isEmpty()) {
                name_home_input.setError("Campo vazio")
                return@setOnClickListener
            }

            name_home_input.text.toString().forEach {
                if (!it.isLetter() && !it.isWhitespace()) {
                    name_home_input.setError("Esse campo não permite números ou caracteres especiais")
                    return@setOnClickListener
                }
            }

            FirebaseFirestore.getInstance().collection("users").document(user?.uid.toString()).get()
                .addOnSuccessListener { document ->
                    //Atualizar um campo
                    home_text1.setText(name_home_input.text.toString())
                    document.reference.update("name", name_home_input.text.toString())

                }
        }
        //Açao de deletar conta
        delete_button.setOnClickListener {
            //validar

            if (password_home_input.text.toString().isEmpty()) {
                password_home_input.setError("Campo vazio.")
                return@setOnClickListener
            }
            //re-autenticar
            val credential = EmailAuthProvider
                .getCredential(user?.email.toString(), password_home_input.text.toString())

            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setTitle("Exclusão de conta")
                    .setMessage("Tem certeza que deseja excluir sua conta? Não será possível recuperar seus dados posteriormente.")
                    .setNeutralButton("Não") { dialog, which ->
                        // Respond to neutral button press
                    }
                    .setPositiveButton("Sim") { dialog, which ->
                        user?.reauthenticate(credential)
                            ?.addOnSuccessListener {
                                //Se o login estiver correto
                                user?.delete()
                                    ?.addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            FirebaseFirestore.getInstance().collection("users")
                                                .document(user?.uid).get()
                                                .addOnSuccessListener { document ->
                                                    document.reference.delete().addOnSuccessListener {
                                                        Toast.makeText(
                                                            context,
                                                            "Conta excluida",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                    }
                                                }
                                            FirebaseFirestore.getInstance().collection("setup")
                                                .whereEqualTo("userUid", user?.uid).get()
                                                .addOnSuccessListener { documents ->
                                                    for (document in documents) {
                                                        document.reference.delete()
                                                    }
                                                    startActivity(
                                                        Intent(
                                                            context,
                                                            LoginActivity::class.java
                                                        )
                                                    )
                                                }
                                        } else {
//                                Toast.makeText(this, "Erro. Reautenticação necessária", Toast.LENGTH_LONG).show()
                                        }
                                    }
                            }?.addOnFailureListener {
                                if (it.message.toString().contains("password is invalid")) {
                                    password_home_input.setError("Senha incorreta.")
                                } else {
//                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                                }
                            }
                    }
                    .show()
            }


        }
    }


}