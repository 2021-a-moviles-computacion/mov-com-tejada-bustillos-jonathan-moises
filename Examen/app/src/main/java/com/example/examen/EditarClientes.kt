package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarClientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_clientes)

        val clienteRecivido = intent.getParcelableExtra<Cliente>("Cliente")
        findViewById<EditText>(R.id.txt_editar_cliente_nombre).setText(clienteRecivido!!.getNombre())
        findViewById<EditText>(R.id.txt_editar_cliente_apellido).setText(clienteRecivido!!.getApellido())
        findViewById<EditText>(R.id.txt_editar_cliente_direccion).setText(clienteRecivido!!.getDireccion())
        findViewById<EditText>(R.id.txt_editar_cliente_cedula).setText(clienteRecivido.getCedula())
        findViewById<EditText>(R.id.txt_editar_cliente_numTelefono).setText(clienteRecivido!!.getNUmeroDeTelefono())

        val botonSalir = findViewById<Button>(
            R.id.btn_editar_cliente_cancelar
        )
        botonSalir.setOnClickListener { finish(); }
        val botonAceptar = findViewById<Button>(
            R.id.btn_editar_cliente_editar
        )

        val db = Firebase.firestore


        botonAceptar.setOnClickListener {



            val cliente= hashMapOf(
                "nombre" to findViewById<EditText>(R.id.txt_editar_cliente_nombre).text.toString(),
                "apellido" to findViewById<EditText>(R.id.txt_editar_cliente_apellido).text.toString(),
                "direccion" to findViewById<EditText>(R.id.txt_editar_cliente_direccion).text.toString(),
                "cedula" to findViewById<EditText>(R.id.txt_editar_cliente_cedula).text.toString(),
                "numTelefono" to findViewById<EditText>(R.id.txt_editar_cliente_numTelefono).text.toString()
            )
            db.collection("cliente").document("${clienteRecivido.getIdCliente()}").set(cliente)
                .addOnSuccessListener {
                    Log.i("firebase","Cliente actualizado")
                    Toast.makeText(applicationContext,"Registro Actualizado", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Log.i("firebase","Error actualizando CLiente")
                }
            finish()
        }

    }

}