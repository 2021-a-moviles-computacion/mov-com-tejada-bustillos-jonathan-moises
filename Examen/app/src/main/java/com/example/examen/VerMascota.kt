package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class VerMascota : AppCompatActivity() {
    var selectedItemPosition =0
    var idClienteMascota:String=""
    var listaMascota=arrayListOf<Mascota>()
    var adapter: ArrayAdapter<Mascota>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_mascota)


        val clienteRecivido = intent.getParcelableExtra<Cliente>("Cliente")

        idClienteMascota=clienteRecivido!!.getIdCliente()!!

        //refreshListViewMascota(idClienteMascota!!)

        val botonCrearMascota = findViewById<Button>(
            R.id.btn_ver_mascota_crear
        )
        botonCrearMascota.setOnClickListener { abrirActividadConParametros2(CrearMascota::class.java,
            idClienteMascota!!
        )

        }
    }

    private fun refreshListViewMascota(idCliente:String) {

        val db = Firebase.firestore
        listaMascota.clear()
        db.collection("mascota").whereEqualTo("idCliente","${idCliente}").get()
            .addOnSuccessListener { mascotaSnapshot->
                for (mascota in mascotaSnapshot){
                    listaMascota.add(
                        Mascota(
                            mascota.getString("nombre"),
                            mascota.getString("especie"),
                            mascota.getString("fechaNac"),
                            mascota.id,
                            mascota.getString("idCliente"),
                            mascota.getString("sexo"),
                            mascota.getString("raza"),
                            mascota.getDouble("lat"),
                            mascota.getDouble("lon")

                        )
                    )

                }
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    listaMascota
                )

                val listViewVerMascotas = findViewById<ListView>(
                    R.id.viewList_Mascotas
                )

                listViewVerMascotas.adapter=adapter
                registerForContextMenu(listViewVerMascotas)
                Log.i("firebase","Lista de mascotas consultada con exito")
            }
            .addOnFailureListener {
                Log.i("firebase","Error condultando lista de mascotas")

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
        val db = Firebase.firestore
        var mascotaSeleccionada= listaMascota[selectedItemPosition]
        return when(item?.itemId){

            R.id.editarM -> {
                abrirActividadConParametros(EditarMascota::class.java,mascotaSeleccionada!!)
                adapter?.notifyDataSetChanged()
                return true
            }
            R.id.eliminarM -> {
                db.collection("mascota").document("${mascotaSeleccionada.idMascota}").delete()
                    .addOnSuccessListener {
                        Log.i("firebase","registro eliminado")
                        Toast.makeText(applicationContext,"Registro Eliminado", Toast.LENGTH_SHORT).show()

                    }
                refreshListViewMascota(idClienteMascota)
                return true
            }
            R.id.verM->{
                abrirActividadConParametros(MostrarMapa::class.java,mascotaSeleccionada!!)
                adapter?.notifyDataSetChanged()
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
        clase: Class<*>,idCliente: String
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("idCliente",idCliente)
        startActivityForResult(intentExplicito, 1)


    }
}
