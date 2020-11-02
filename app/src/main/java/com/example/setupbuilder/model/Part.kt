package com.example.setupbuilder.model

import android.widget.Toast
import android.widget.Toast.*


open class Part(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String){
    var name: String = name
        protected set
    var id: String = id
        protected set
    var price: Float = price
        protected set
    var releaseYear:String = releaseYear
        protected set
    var brand: String = brand
        protected set
    var details: String = details
        protected set

    init {
    }
}