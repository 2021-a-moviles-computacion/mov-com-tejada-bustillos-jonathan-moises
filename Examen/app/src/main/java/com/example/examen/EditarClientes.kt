package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

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
        botonAceptar.setOnClickListener {
            var objCliente=Cliente()
            objCliente.setNombre(findViewById<EditText>(R.id.txt_editar_cliente_nombre).text.toString())
            objCliente.setApellido( findViewById<EditText>(R.id.txt_editar_cliente_apellido).text.toString())
            objCliente.setNUmeroDeTelefono(findViewById<EditText>(R.id.txt_editar_cliente_numTelefono).text.toString())
            objCliente.setDireccion(findViewById<EditText>(R.id.txt_editar_cliente_direccion).text.toString())
            objCliente.setCedula(findViewById<EditText>(R.id.txt_editar_cliente_cedula).text.toString())
            objCliente.setIdCliente(clienteRecivido.getIdCliente()!!)
            DBcomp.SQLdatabase!!.actualizarCliente(
                objCliente
            )
            finish()
        }

    }

}