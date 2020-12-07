package br.com.setupbuilder.model

class Mouse(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String, image:String,
                 connection: String, wirelees:String, type:String, dpi:String):
    Peripheral(name, id, price, releaseYear, brand, details, connection, wirelees, type, image){


    var dpi: String = dpi
        private set

}