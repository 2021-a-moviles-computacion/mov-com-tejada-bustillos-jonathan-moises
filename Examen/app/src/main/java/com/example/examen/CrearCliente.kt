package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cliente)


        val botonSalir = findViewById<Button>(
            R.id.btn_registrar_cliente_cancelar
        )
        botonSalir.setOnClickListener { finish(); }

        val botonAceptar = findViewById<Button>(
            R.id.btn_registrar_cliente_aceptar
        )
        botonAceptar.setOnClickListener {
            val db = Firebase.firestore

            val cedula = findViewById<EditText>(R.id.txt_registrar_cliente_cedula).text.toString()
            db.collection("cliente").whereEqualTo("cedula",cedula).get()
                .addOnSuccessListener {
                    if (it.size()<1){
                        val cliente= hashMapOf(
                            "nombre" to findViewById<EditText>(R.id.txt_registrar_cliente_nombre).text.toString(),
                            "apellido" to findViewById<EditText>(R.id.txt_registrar_cliente_apellido).text.toString(),
                            "direccion" to findViewById<EditText>(R.id.txt_registrar_cliente_direccion).text.toString(),
                            "cedula" to findViewById<EditText>(R.id.txt_registrar_cliente_cedula).text.toString(),
                            "numTelefono" to findViewById<EditText>(R.id.txt_registrar_cliente_numTelefono).text.toString()
                        )
                        db.collection("cliente").add(cliente)
                            .addOnSuccessListener {
                                Log.i("firestore","cliente registrado")
                                Toast.makeText(applicationContext,"El cliente se ha registrado con exito", Toast.LENGTH_SHORT).show()
                                //limpiarGUI()
                                finish()


                        }
                            .addOnFailureListener {
                                Log.i("firestore","error registgrando cliente")
                            }
                    }
                    else{
                        Log.i("firestore","ya se encuentra registrado")
                        Toast.makeText(applicationContext,"El cliente con la cedula ${cedula} ya se encuentra registrado", Toast.LENGTH_SHORT).show()


                    }
                }.addOnFailureListener {
                    Log.i("firestore","Error en consulta de cedula")
                }






        }




    }

    private fun limpiarGUI() {
        findViewById<EditText>(R.id.txt_registrar_cliente_nombre).setText("")
        findViewById<EditText>(R.id.txt_registrar_cliente_apellido).setText("")
        findViewById<EditText>(R.id.txt_registrar_cliente_direccion).setText("")
        findViewById<EditText>(R.id.txt_registrar_cliente_cedula).setText("")
        findViewById<EditText>(R.id.txt_registrar_cliente_numTelefono).setText("")
    }


    fun abrirActividad(
        clase: Class<*>
    ) {
        val intent = Intent(
            this,
            clase
        )
        // this.startActivity(intent)
        startActivity(intent)
        }
}
