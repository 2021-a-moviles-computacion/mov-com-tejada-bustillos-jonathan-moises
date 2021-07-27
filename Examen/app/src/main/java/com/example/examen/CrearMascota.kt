package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CrearMascota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_mascota)
        val idCLiente = intent.getIntExtra("idCliente",0)
        val botonSalir = findViewById<Button>(
            R.id.btn_registrar_mascota_cancelar
        )
        botonSalir.setOnClickListener { finish(); }

        val botonAceptar = findViewById<Button>(
            R.id.btn_registrar_mascota_aceptar
        )
        botonAceptar.setOnClickListener {
            DBcomp.SQLdatabase!!.crearMascota(
                idCLiente,
                findViewById<EditText>(R.id.txt_registrar_mascota_nombre).text.toString(),
                findViewById<EditText>(R.id.txt_registrar_mascota_especie).text.toString(),
                findViewById<EditText>(R.id.txt_registrar_mascota_raza).text.toString(),
                findViewById<EditText>(R.id.txt_registrar_mascota_fecNac).text.toString(),
                findViewById<EditText>(R.id.txt_registrar_mascota_sexo).text.toString().toCharArray().get(0)
            )
            findViewById<EditText>(R.id.txt_registrar_mascota_nombre).setText("")
            findViewById<EditText>(R.id.txt_registrar_mascota_especie).setText("")
            findViewById<EditText>(R.id.txt_registrar_mascota_fecNac).setText("")
            findViewById<EditText>(R.id.txt_registrar_mascota_sexo).setText("")
            findViewById<EditText>(R.id.txt_registrar_mascota_raza).setText("")
        }





    }
}