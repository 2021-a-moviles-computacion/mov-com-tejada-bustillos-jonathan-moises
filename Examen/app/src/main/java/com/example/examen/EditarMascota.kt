package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditarMascota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_mascota)


        val mascotaRecivida = intent.getParcelableExtra<Mascota>("Mascota")
        findViewById<EditText>(R.id.txt_editar_mascota_nombre).setText(mascotaRecivida!!.getNombre())
        findViewById<EditText>(R.id.txt_editar_mascota_especie).setText(mascotaRecivida!!.getEspecie())
        findViewById<EditText>(R.id.txt_editar_mascota_raza).setText(mascotaRecivida!!.getRaza())
        findViewById<EditText>(R.id.txt_editar_mascota_fechaNac).setText(mascotaRecivida.getFechaNac())
        findViewById<EditText>(R.id.txt_editar_mascota_sexo).setText(mascotaRecivida!!.getSexo().toString())

        val botonSalir = findViewById<Button>(
            R.id.btn_editar_mascota_cancelar
        )
        botonSalir.setOnClickListener { finish(); }
        val botonAceptar = findViewById<Button>(
            R.id.btn_editar_mascota_editar
        )
        botonAceptar.setOnClickListener {
            var objMascota=Mascota()
            objMascota.setNombre(findViewById<EditText>(R.id.txt_editar_mascota_nombre).text.toString())
            objMascota.setEspecie( findViewById<EditText>(R.id.txt_editar_mascota_especie).text.toString())
            objMascota.setRaza(findViewById<EditText>(R.id.txt_editar_mascota_raza).text.toString())
            objMascota.setFechaNac(findViewById<EditText>(R.id.txt_editar_mascota_fechaNac).text.toString())
            objMascota.setSexo(findViewById<EditText>(R.id.txt_editar_mascota_sexo).text.toString().toCharArray().get(0))
            objMascota.setIdCliente(mascotaRecivida.getIdCliente()!!)
            objMascota.setIdMascota(mascotaRecivida.getIdMascota()!!)
            DBcomp.SQLdatabase!!.actualizarMascota(
                objMascota
            )
            finish()
        }

    }

}

