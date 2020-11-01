package com.example.setupbuilder.model

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.setupbuilder.MenuActivity
import com.example.setupbuilder.repositories.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.account_fragment.*
import kotlinx.android.synthetic.main.login_activity.*

class UserModel {
    private val User = UserRepository().getUser()

    public fun getEmail (): String? {
        return User?.email
    }
    public fun getUID ():String?{
        return User?.uid
    }

    //splash screen
    public fun isAuthenticated ():Boolean{
        if (User !== null) return true
        return false
    }
    //sign in screen
    public fun changePassword (email:String): Task<Void> {
        return FirebaseAuth.getInstance().sendPasswordResetEmail(
            email
        )
    }
    public fun signIn(email:String, password:String): Task<AuthResult> {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
    }
    //sign up screen
    public fun signUp(){

    }

    //account screen
    public fun deleteUser(password:String): Task<Void>? {
        val credential = EmailAuthProvider
            .getCredential(User?.email.toString(), password)
        User?.reauthenticate(credential)
        return User?.delete()
    }

    public fun signOut(){

    }
}