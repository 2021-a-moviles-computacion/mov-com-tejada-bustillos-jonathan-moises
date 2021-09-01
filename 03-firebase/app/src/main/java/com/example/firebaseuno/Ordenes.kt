package com.example.firebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.get
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Ordenes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordenes)
        val spinnerRestaurante = findViewById<Spinner>(R.id.sp_restaurantes)

       
        llenarGUI()
    }

    private fun llenarGUI() {



        val db = Firebase.firestore
        val referencia1 = db.collection("restaurante")
        val listaRestaurantes = ArrayList<OBJRestaurante>()
        referencia1.get().addOnSuccessListener {
            for(restaurante in it){

                val restauranteObtenido: DTORestaurantes? = restaurante.toObject(DTORestaurantes::class.java)
                if (restauranteObtenido != null){
                    listaRestaurantes.add(OBJRestaurante(restauranteObtenido.nombre.toString()))
                }
            }

        }.addOnFailureListener{
            Log.i("firebase-firestore","Restaurante no se pudo obtener")
        }
        val spinnerRestaurante = findViewById<Spinner>(R.id.sp_restaurantes)
        spinnerRestaurante.adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,listaRestaurantes)

        val listaProductos = ArrayList<OBJProducto>()
        val referencia2 = db
            .collection("producto")
        referencia2
            .get()
            .addOnSuccessListener {
                Log.i("firebase-firestore","productos Obtenidos")
                for (producto in it) {

                    val productoObtenido: DTOProductos? =
                        producto.toObject(DTOProductos::class.java)
                    if (productoObtenido != null) {
                        listaProductos.add(
                            OBJProducto(
                                productoObtenido.nombre.toString(),
                                productoObtenido.precio.toDouble()
                            )
                        )
                    }
                }

            }
            .addOnFailureListener {
                Log.i("firebase-firestore","Producto no se pudo obtener")
            }
        val spinnerProducto= findViewById<Spinner>(R.id.sp_producto)
        spinnerProducto.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaProductos)

    }

}