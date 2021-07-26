package com.example.moviles_comunicacion_2021_a

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val arregloNumeros = BBaseDatosMemoria.arregloBentrenador

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloNumeros


        )
        val listViewEjemplo = findViewById<ListView>(R.id.ltv_ejemplo)
        listViewEjemplo.adapter=adaptador

        val botonAnadirNumero = findViewById<Button>(R.id.btn_anadir_numero)
        botonAnadirNumero.setOnClickListener { anadirItemsAlListView(Bentrenador("prueba","e@d.com"),arregloNumeros,adaptador) }


        listViewEjemplo.setOnItemLongClickListener{adapterView,view,posicion,id->
            Log.i("list-view","Dio Long Click ${posicion}")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Titulo")
            builder.setMessage("Mensaje")
            builder.setPositiveButton(
                "Si",
                DialogInterface.OnClickListener { dialog, which ->
                    Log.i("list-view","si")
                }
            )

            builder.setNegativeButton(
                "No",
                null
            )
            val dialogo = builder.create()
            dialogo.show()

            return@setOnItemLongClickListener true
        }


        registerForContextMenu(listViewEjemplo)

    }
    var posicionItemSeleccionado = 0
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
        Log.i("list-view", "List view ${posicionItemSeleccionado}")
        Log.i("list-view", "List view ${BBaseDatosMemoria.arregloBentrenador[id]}")



    }

    fun anadirItemsAlListView(
        valor:Bentrenador,arreglo: ArrayList<Bentrenador>,adaptador: ArrayAdapter<Bentrenador>
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            //editar
                R.id.mi_editar->{
                    Log.i("lis-view","editar ${BBaseDatosMemoria.arregloBentrenador[posicionItemSeleccionado]}")
                    return true
                }
            R.id.mi_eliminar->{
                Log.i("lis-view","eliminar ${BBaseDatosMemoria.arregloBentrenador[posicionItemSeleccionado]}")
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }
}