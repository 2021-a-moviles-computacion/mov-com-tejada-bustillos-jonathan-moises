package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

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
            DBcomp.SQLdatabase!!.crearCliente(
                findViewById<EditText>(R.id.txt_registrar_cliente_nombre).text.toString(),
                findViewById<EditText>(R.id.txt_registrar_cliente_apellido).text.toString(),
                findViewById<EditText>(R.id.txt_registrar_cliente_direccion).text.toString(),
                findViewById<EditText>(R.id.txt_registrar_cliente_cedula).text.toString(),
                findViewById<EditText>(R.id.txt_registrar_cliente_numTelefono).text.toString()
            )
            findViewById<EditText>(R.id.txt_registrar_cliente_nombre).setText("")
            findViewById<EditText>(R.id.txt_registrar_cliente_apellido).setText("")
            findViewById<EditText>(R.id.txt_registrar_cliente_direccion).setText("")
            findViewById<EditText>(R.id.txt_registrar_cliente_cedula).setText("")
            findViewById<EditText>(R.id.txt_registrar_cliente_numTelefono).setText("")
        }




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
