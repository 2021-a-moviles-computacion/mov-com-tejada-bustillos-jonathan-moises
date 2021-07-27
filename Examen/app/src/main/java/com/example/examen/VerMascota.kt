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

class VerMascota : AppCompatActivity() {
    var selectedItemPosition =0
    var idClienteMascota:Int=0
    var adapter: ArrayAdapter<Cliente>?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_mascota)
        val clienteRecivido = intent.getParcelableExtra<Cliente>("Cliente")
        println(clienteRecivido!!.getIdCliente())

        idClienteMascota=clienteRecivido!!.getIdCliente()!!
        refreshListViewMascota(idClienteMascota)

        val botonCrearMascota = findViewById<Button>(
            R.id.btn_ver_mascota_crear
        )
        botonCrearMascota.setOnClickListener { abrirActividadConParametros2(CrearMascota::class.java,idClienteMascota)

        }
    }

    private fun refreshListViewMascota(idCliente:Int) {
        println("entra a  refrelisview"+idCliente)
        DBcomp.SQLdatabase = SQLdatabase(this)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,

            DBcomp.SQLdatabase!!.consultarListaMascotas(idCliente)
        )

        val listViewVerMascotas = findViewById<ListView>(
            R.id.viewList_Mascotas
        )

        listViewVerMascotas.adapter=adapter
        registerForContextMenu(listViewVerMascotas)
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
        refreshListViewMascota(idClienteMascota)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        view: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, view, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu_mascotas,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        selectedItemPosition = posicion
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var mascotaSeleccionada = DBcomp.SQLdatabase!!.consultarListaMascotas(idClienteMascota)[selectedItemPosition]
        return when(item?.itemId){
            R.id.editarM -> {
                abrirActividadConParametros(EditarMascota::class.java,mascotaSeleccionada!!)
                adapter?.notifyDataSetChanged()
                return true
            }
            R.id.eliminarM -> {
                if (DBcomp.SQLdatabase != null) {
                    DBcomp.SQLdatabase!!.eliminarMascota(mascotaSeleccionada.getIdMascota()!!)
                    adapter?.remove(adapter!!.getItem(selectedItemPosition))
                    adapter?.notifyDataSetChanged()
                }
                refreshListViewMascota(idClienteMascota)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }
    fun abrirActividadConParametros(
        clase: Class<*>,mascota: Mascota
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("Mascota",mascota)
        startActivityForResult(intentExplicito, 1)


    }
    fun abrirActividadConParametros2(
        clase: Class<*>,idCliente: Int
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("idCliente",idCliente)
        startActivityForResult(intentExplicito, 1)


    }
}
