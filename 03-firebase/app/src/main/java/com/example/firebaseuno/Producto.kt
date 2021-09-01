package com.example.firebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Producto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        val botonCrear = findViewById<Button>(R.id.btn_CrearProducto)
        botonCrear.setOnClickListener {
            crearProducto()
        }

    }

    private fun crearProducto() {
        val editTextNombre = findViewById<EditText>(R.id.txv_Nombre)
        val editTextPrecio = findViewById<EditText>(R.id.txv_Precio)
        val nuevoProducto = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString(),
            "precio" to editTextPrecio.text.toString().toDouble()
        )

        val db = Firebase.firestore
        val referenia = db.collection("producto")
        referenia.add(nuevoProducto)
            .addOnSuccessListener {
                editTextNombre.text.clear()
                editTextPrecio.text.clear()
            }
            .addOnFailureListener{

            }
    }
}