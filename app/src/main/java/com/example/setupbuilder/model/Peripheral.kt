package com.example.setupbuilder.model

open class Peripheral(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String,
              connection: String, wirelees:String, type:String):
    Part(name, id, price, releaseYear, brand, details){

    var connection: String = connection
        protected set
    var wirelees: String = wirelees
        protected set
    var type: String = type
        protected set

    init{
        println("periférico genérico criado criada com sucesso")
    }
}