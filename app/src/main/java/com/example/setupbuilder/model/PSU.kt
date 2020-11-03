package com.example.setupbuilder.model

import java.util.*

class PSU(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String,
          certification:String, wattage:String, formFactor:String, modular:String):
    Part(name, id, price, releaseYear, brand, details){

    var certification: String = certification
        private set
    var wattage: String = wattage
        private set
    var formFactor: String = formFactor
        private set
    var modular: String = modular
        private set

}