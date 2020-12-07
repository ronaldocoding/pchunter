package br.com.setupbuilder.model

import com.google.firebase.firestore.FieldValue

data class Setup(val name:String, val timestamp:FieldValue, val userUid:String, val preco:Double)