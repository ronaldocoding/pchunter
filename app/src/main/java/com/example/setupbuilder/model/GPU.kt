package com.example.setupbuilder.model
import java.util.*

class GPU(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String,
          shaderCount:String, model:String, architecture:String, VRAMSize:String, VRAMType:String):
    Part(name, id, price, releaseYear, brand, details){

    var shaderCount: String = shaderCount
        private set
    var model: String = model
        private set
    var architecture: String = architecture
        private set
    var VRAMSize: String = VRAMSize
        private set
    var VRAMType: String = VRAMType
        private set

    init {
        println("oi")
    }
}