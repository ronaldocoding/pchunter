package com.example.setupbuilder.model

import java.util.*

class Case(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String, image:String,
              formFactor: String, sideWindow:String):
    Part(name, id, price, releaseYear, brand, details, image){

    var formFactor: String = formFactor
        private set
    var sideWindow: String = sideWindow
        private set

    init{
        println("case criado com sucesso")
    }
}