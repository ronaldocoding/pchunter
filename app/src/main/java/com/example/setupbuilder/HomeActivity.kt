package com.example.setupbuilder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.register_activity.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        var user = FirebaseAuth.getInstance().currentUser
        var id = ""

        FirebaseFirestore.getInstance().collection("users").whereEqualTo("uid",user?.uid).get()
            .addOnSuccessListener {documents ->
                for (document in documents) {
                    //Atualizar um campo
//                    document.reference.update("name", "teste")
                    home_text1.setText("Nome: ${document.data.get("name")}")
                    name_home_input.setText("${document.data.get("name")}")
                }
            }
        home_text2.setText("E-mail: ${user?.email}")

        //Editar nome
        update_button.setOnClickListener {
            if(name_home_input.text.toString().isEmpty()){
                name_home_input.setError("Campo vazio")
                return@setOnClickListener
            }

            name_home_input.text.toString().forEach {
                if(!it.isLetter() && !it.isWhitespace()){
                    name_register.setError("Esse campo não permite números ou caracteres especiais")
                    return@setOnClickListener
                }
            }

            FirebaseFirestore.getInstance().collection("users").whereEqualTo("uid",user?.uid).get()
                .addOnSuccessListener {documents ->
                    for (document in documents) {
                        //Atualizar um campo

                        document.reference.update("name", name_home_input.text.toString())

                            ?.addOnFailureListener{
                                Toast.makeText(this, it.message.toString(), Toast.LENGTH_LONG).show()
                            }?.addOnSuccessListener {
                                home_text1.setText("Nome: ${name_home_input.text.toString()}")
                                Toast.makeText(this, "Nome editado com sucesso", Toast.LENGTH_LONG).show()
                            }


                    }
                }
        }
        //Açao de deletar conta
        delete_button.setOnClickListener {
            //validar
            if(password_home_input.text.toString().isEmpty()){
                password_home_input.setError("Campo vazio.")
                return@setOnClickListener
            }
            //re-autenticar
            val credential = EmailAuthProvider
                    .getCredential(user?.email.toString(), password_home_input.text.toString())

            user?.reauthenticate(credential)
                ?.addOnSuccessListener {
                    //Se o login estiver correto
                    user?.delete()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                FirebaseFirestore.getInstance().collection("users").whereEqualTo("uid",user?.uid).get()
                                    .addOnSuccessListener {documents ->
                                        for (document in documents) {
                                            document.reference.delete().addOnSuccessListener {
                                                Toast.makeText(this, "Usuario excluido", Toast.LENGTH_LONG).show()
                                                startActivity(Intent(this, LoginActivity::class.java))
                                            }
                                        }
                                    }
                            }else{
                                Toast.makeText(this, "Erro. Reautenticação necessária", Toast.LENGTH_LONG).show()
                            }
                        }
                }?.addOnFailureListener {
                    if(it.message.toString().contains("password is invalid")){
                        password_home_input.setError("Senha incorreta.")
                    }else {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }

        }
        //Sair da conta
        sign_out.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}

