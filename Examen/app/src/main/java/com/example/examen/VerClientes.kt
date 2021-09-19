package com.example.examen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class VerClientes : AppCompatActivity() {
    var selectedItemPosition =0
    val listaClientes = arrayListOf<Cliente>()
    var adapter: ArrayAdapter<Cliente>?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_clientes)

       // refreshListViewCliente()

        val botonCrearCliente = findViewById<Button>(
            R.id.btn_ver_clientes_crear
        )
        botonCrearCliente.setOnClickListener { abrirActividad(CrearCliente::class.java)

        }
    }

    private fun refreshListViewCliente() {

        val db = Firebase.firestore

        val clientes=db.collection("cliente")
        listaClientes.clear()
        clientes.get().addOnSuccessListener {   clienteSnap->
            for (cliente in clienteSnap){

                listaClientes.add(
                    Cliente(
                        cliente.getString("nombre"),
                        cliente.getString("apellido"),
                        cliente.id,
                        cliente.getString("cedula"),
                        cliente.getString("direccion"),
                        cliente.getString("numTelefono")
                    )
                )
            }
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                listaClientes
            )
            val listViewVerClientes = findViewById<ListView>(
                R.id.listView_Clientes
            )

            listViewVerClientes.adapter=adapter
            registerForContextMenu(listViewVerClientes)
            Log.i("firebase","Lista actualizada ")

        }.addOnFailureListener {
            Log.i("firebase","Error conusltando cliente")
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

        var db = Firebase.firestore
        var clienteSeleccionado:Cliente= listaClientes[selectedItemPosition]


        //var clienteSeleccionado = DBcomp.SQLdatabase!!.consultarListaClientes()[selectedItemPosition]

        return when(item?.itemId){
            R.id.editar -> {
                abrirActividadConParametros(EditarClientes::class.java,clienteSeleccionado!!)
                adapter?.notifyDataSetChanged()
                return true
            }
            R.id.eliminar -> {
                db.collection("cliente").document("${clienteSeleccionado.getIdCliente()}").delete()
                    .addOnSuccessListener {
                        Log.i("firebase","ELiminado correctamente")
                        //listaClientes.drop(selectedItemPosition)
                        Toast.makeText(applicationContext,"Registro eliminado", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Log.i("firebase","Error eliminando")
                    }
                refreshListViewCliente()
                adapter?.notifyDataSetChanged()

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
