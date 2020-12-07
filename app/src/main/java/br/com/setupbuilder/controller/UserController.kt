package br.com.setupbuilder.controller

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserController {

    public fun getUser (): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    public fun changePassword (email:String): Task<Void> {
        return FirebaseAuth.getInstance().sendPasswordResetEmail(
            email
        )
    }
    public fun deleteUser(password:String): Task<Void>? {
        val credential = EmailAuthProvider
            .getCredential(getUser()?.email.toString(), password)
        getUser()?.reauthenticate(credential)
        return getUser()?.delete()
    }

    public fun signIn(email:String, password:String): Task<AuthResult> {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
    }

    public fun isAuthenticated ():Boolean{

        if (getUser() !== null) return true
        return false
    }

    public fun getEmail (): String? {
        return getUser()?.email
    }
    public fun getUID ():String?{
        return getUser()?.uid
    }

    public fun createUserWithEmailAndPassword(email:String, password:String): Task<AuthResult> {
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
    }
}