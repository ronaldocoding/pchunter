package br.com.setupbuilder.model

class PSU(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String, image:String,
          certification:String, wattage:String, formFactor:String, modular:String):
    Part(name, id, price, releaseYear, brand, details, image,){

    var certification: String = certification
        private set
    var wattage: String = wattage
        private set
    var formFactor: String = formFactor
        private set
    var modular: String = modular
        private set

}