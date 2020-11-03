package com.example.setupbuilder.model

class Storage(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String,
                  size: String, type:String, formFactor:String):
    Part(name, id, price, releaseYear, brand, details){

    var size: String = size

    var type: String = type

    var formFactor: String = formFactor


}