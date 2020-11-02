package com.example.setupbuilder.model

import java.util.*

class MotherBoard(name: String, id: String, price: Float, releaseYear:String, brand:String, details:String,
           ramSlots: String, chipset:String,socket: String, formFactor:String):
    Part(name, id, price, releaseYear, brand, details){

    var ramSlots: String = ramSlots
        protected set
    var chipset: String = chipset
        protected set
    var socket: String = socket
        protected set
    var formFactor: String = formFactor
        protected set

    init{
        println("placa mãe criado com sucesso")
    }
}