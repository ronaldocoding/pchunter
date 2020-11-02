package com.example.setupbuilder.model

import java.util.*

class Headset(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String,
            connection: String, wirelees:String, type:String, headphoneFrequencyResponse:String, micFrequencyResponse:String):
    Peripheral(name, id, price, releaseYear, brand, details, connection, wirelees, type){

    var headphoneFrequencyResponse: String = headphoneFrequencyResponse
        private set

    var micFrequencyResponse: String = micFrequencyResponse
        private set
}