package com.example.firebaseuno

class ObjetoOrden (
    val nombreRestaurante: String,
    val nombreProducto: String,
    val precioProducto: String,
    val cantidadProducto: String,
) {

    override fun toString(): String {
        return "Nombre Restaurante: ${nombreRestaurante}, " +
    "Producto: ${nombreProducto}, " +
    "Precio: ${precioProducto}, " +
    "Cantidad: ${cantidadProducto}"
}
}