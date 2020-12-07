package br.com.setupbuilder.model


open class Part(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String, image:String){
    var name: String = name
        protected set
    var id: String = id
        protected set
    var price: Float = price
        protected set
    var releaseYear:String = releaseYear
        protected set
    var brand: String = brand
        protected set
    var details: String = details
        protected set
    var image:String = image
    init {

    }


}