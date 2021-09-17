package com.example.firebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Ordenes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordenes)
        val db = Firebase.firestore
        val referencia1 = db.collection("restaurante")
        val listaRestaurantes = ArrayList<OBJRestaurante>()
        referencia1.get().addOnSuccessListener {
            //listaRestaurantes.add(OBJRestaurante("Seleccione un Restaurate"))
            for(restaurante in it){

                val restauranteObtenido: DTORestaurantes? = restaurante.toObject(DTORestaurantes::class.java)
                if (restauranteObtenido != null){
                    listaRestaurantes.add(OBJRestaurante(restauranteObtenido.nombre.toString()))
                }
            }
            val spinnerRestaurante = findViewById<Spinner>(R.id.sp_restaurantes)
            spinnerRestaurante.adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,listaRestaurantes)
            spinnerRestaurante.onItemSelectedListener = object:
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    Toast.makeText(this@Ordenes,listaRestaurantes[position].toString(),Toast.LENGTH_LONG).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(this@Ordenes,"Seleccione un Restaurante",Toast.LENGTH_LONG).show()
                }
            }

        }.addOnFailureListener{
            Log.i("firebase-firestore","Restaurante no se pudo obtener")
        }


        val listaProductos = ArrayList<OBJProducto>()
        val referencia2 = db
            .collection("producto")
        referencia2
            .get()
            .addOnSuccessListener {
                Log.i("firebase-firestore","productos Obtenidos")
                //listaProductos.add(OBJProducto("Seleccione el Producto",0.0))
                for (producto in it) {

                    val productoObtenido: DTOProductos? =
                        producto.toObject(DTOProductos::class.java)
                    if (productoObtenido != null) {
                        listaProductos.add(
                            OBJProducto(
                                productoObtenido.nombre.toString(),
                                productoObtenido.precio.toDouble()
                            )
                        )
                    }
                }
                val spinnerProducto= findViewById<Spinner>(R.id.sp_producto)
                spinnerProducto.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaProductos)
                spinnerProducto.onItemSelectedListener = object:
                    AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {

                        Toast.makeText(this@Ordenes,listaProductos[position].toString(),Toast.LENGTH_LONG).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Toast.makeText(this@Ordenes,"Seleccione un Producto",Toast.LENGTH_LONG).show()
                    }
                }

            }
            .addOnFailureListener {
                Log.i("firebase-firestore","Producto no se pudo obtener")
            }




    }



}