package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.proyectoibimestre.SQLdatabase

class VerClientes : AppCompatActivity() {
    var selectedItemPosition =0

    var adapter: ArrayAdapter<Cliente>?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_clientes)

        refreshListViewCliente()

        val botonCrearCliente = findViewById<Button>(
            R.id.btn_ver_clientes_crear
        )
        botonCrearCliente.setOnClickListener { abrirActividad(CrearCliente::class.java)

        }
    }

    private fun refreshListViewCliente() {
        DBcomp.SQLdatabase = SQLdatabase(this)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            DBcomp.SQLdatabase!!.consultarListaClientes()
        )

        val listViewVerClientes = findViewById<ListView>(
            R.id.listView_Clientes
        )

        listViewVerClientes.adapter=adapter
        registerForContextMenu(listViewVerClientes)
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

    override fun onResume() {
        super.onResume()
        refreshListViewCliente()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        view: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, view, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu_clientes,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        selectedItemPosition = posicion
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var clienteSeleccionado = DBcomp.SQLdatabase!!.consultarListaClientes()[selectedItemPosition]
        return when(item?.itemId){
            R.id.editar -> {
                abrirActividadConParametros(EditarClientes::class.java,clienteSeleccionado!!)
                adapter?.notifyDataSetChanged()
                return true
            }
            R.id.eliminar -> {
                if (DBcomp.SQLdatabase != null) {
                    DBcomp.SQLdatabase!!.eliminarCliente(clienteSeleccionado.getIdCliente()!!)
                    adapter?.remove(adapter!!.getItem(selectedItemPosition))
                    adapter?.notifyDataSetChanged()
                }
                refreshListViewCliente()
                return true
            }
            R.id.consultarMascotas -> {
                abrirActividadConParametros(VerMascota::class.java,clienteSeleccionado!!)
                adapter?.notifyDataSetChanged()

                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun abrirActividadConParametros(
        clase: Class<*>,Cliente:Cliente
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("Cliente",Cliente)
        startActivityForResult(intentExplicito, 1)


    }
}
