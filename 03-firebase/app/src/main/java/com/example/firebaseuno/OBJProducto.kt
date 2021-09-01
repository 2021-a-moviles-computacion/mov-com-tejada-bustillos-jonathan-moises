package com.example.firebaseuno

class OBJProducto(val nombre: String,val precio:Double) {
    override fun toString(): String {
        return nombre+" Precio: "+precio
    }
}