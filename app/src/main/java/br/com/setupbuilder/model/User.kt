package br.com.setupbuilder.model


import com.google.firebase.firestore.FieldValue

data class User(val email:String, val timestamp:FieldValue, val userUid:String, val password:String)