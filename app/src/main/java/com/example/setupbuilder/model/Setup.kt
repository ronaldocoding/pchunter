package com.example.setupbuilder.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import java.time.LocalDateTime

data class Setup(val name:String, val timestamp:FieldValue, val userUid:String, val preco:Double)