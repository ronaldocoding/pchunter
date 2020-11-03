package com.example.setupbuilder.model
import java.util.*

class CPUcooler(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String,
           noiseLevel: String, fanRPM:String, coolerType:String):
    Part(name, id, price, releaseYear, brand, details){

    var noiseLevel: String = noiseLevel
        private set
    var fanRPM: String = fanRPM
        private set
    var coolerType: String = coolerType
        private set

    init{
        println("cpu cooler criado com sucesso")
    }
}