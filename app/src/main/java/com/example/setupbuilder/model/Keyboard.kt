package com.example.setupbuilder.model

class Keyboard(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String,
            connection: String, wirelees:String, type:String, layout:String):
    Peripheral(name, id, price, releaseYear, brand, details, connection, wirelees, type){

    var layout: String = layout
        private set

}