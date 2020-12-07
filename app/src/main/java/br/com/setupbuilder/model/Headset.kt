package br.com.setupbuilder.model

class Headset(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String, image:String,
            connection: String, wirelees:String, type:String, headphoneFrequencyResponse:String, micFrequencyResponse:String):
    Peripheral(name, id, price, releaseYear, brand, details, connection, wirelees, type, image){

    var headphoneFrequencyResponse: String = headphoneFrequencyResponse
        private set

    var micFrequencyResponse: String = micFrequencyResponse
        private set
}