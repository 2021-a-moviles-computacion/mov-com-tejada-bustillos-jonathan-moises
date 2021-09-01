package com.example.firebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Restaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurante)
        val botonCrear = findViewById<Button>(R.id.btn_crear_restaurante)
        botonCrear.setOnClickListener {
            crearRestaurante()
        }
    }

    private fun crearRestaurante() {
        val editTextNombre = findViewById<EditText>(R.id.te_nombre_restaurante)

        val nuevoRestaurante = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString()
        )

        val db = Firebase.firestore
        val referencia = db.collection("restaurante")
        referencia.add(nuevoRestaurante)
            .addOnSuccessListener {
                editTextNombre.text.clear()
            }
            .addOnFailureListener{

            }
    }

}