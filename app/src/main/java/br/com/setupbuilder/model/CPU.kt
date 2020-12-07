package br.com.setupbuilder.model

class CPU(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String, image:String,
          coreCount:String, socket:String, architecture:String, thermalSolution:String):
    Part(name, id, price, releaseYear, brand, details, image){

    var coreCount: String = coreCount
        private set
    var socket: String = socket
        private set
    var architecture: String = architecture
        private set
    var thermalSolution: String = thermalSolution
        private set

}