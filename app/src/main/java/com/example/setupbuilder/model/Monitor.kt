package com.example.setupbuilder.model

import java.util.*

class Monitor(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String,
          screenSize: String, resolution:String, aspectRation:String, refreshRate:String, panelType:String, adaptativeSync:String, ports:String):
    Part(name, id, price, releaseYear, brand, details){

    var screenSize: String = screenSize
        private set
    var resolution: String = resolution
        private set
    var aspectRation: String = aspectRation
        private set
    var refreshRate: String = refreshRate
        private set
    var panelType:String = panelType
        private set
    var adaptativeSync: String = adaptativeSync
        private set
    var ports: String = ports
        private set

    init{
        println("monitor criado com sucesso")
    }
}