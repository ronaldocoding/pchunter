package com.example.setupbuilder.model

import java.util.*

class RAM(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String,
          type: String, totalSize:String, speed:String, CASLatency:String, sticksNumber:String):
    Part(name, id, price, releaseYear, brand, details){

    var type: String = type
        private set
    var totalSize: String = totalSize
        private set
    var speed: String = speed
        private set
    var CASLatency: String = CASLatency
        private set
    var sticksNumber: String = sticksNumber
        private set
}