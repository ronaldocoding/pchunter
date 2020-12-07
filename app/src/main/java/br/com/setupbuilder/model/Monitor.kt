package br.com.setupbuilder.model

class Monitor(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String, image:String,
          screenSize: String, resolution:String, aspectRation:String, refreshRate:String, panelType:String, adaptativeSync:String, ports:String):
    Part(name, id, price, releaseYear, brand, details, image){

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