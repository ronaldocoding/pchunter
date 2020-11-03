package com.example.setupbuilder.model

import java.util.*

class Mouse(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String,
                 connection: String, wirelees:String, type:String, dpi:String):
    Peripheral(name, id, price, releaseYear, brand, details, connection, wirelees, type){


    var dpi: String = dpi
        private set

}