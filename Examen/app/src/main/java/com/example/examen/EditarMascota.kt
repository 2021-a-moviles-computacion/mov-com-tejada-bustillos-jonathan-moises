package com.example.examen

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarMascota : AppCompatActivity() {

    private lateinit var mapa:GoogleMap
    var permisos= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_mascota)

        val mascotaRecivida = intent.getParcelableExtra<Mascota>("Mascota")

        findViewById<EditText>(R.id.txt_editar_mascota_nombre).setText(mascotaRecivida!!.nombre)
        findViewById<EditText>(R.id.txt_editar_mascota_especie).setText(mascotaRecivida!!.especie)
        findViewById<EditText>(R.id.txt_editar_mascota_raza).setText(mascotaRecivida!!.raza)
        findViewById<EditText>(R.id.txt_editar_mascota_fecNac).setText(mascotaRecivida.fechaNac)
        findViewById<EditText>(R.id.txt_editar_mascota_sexo).setText(mascotaRecivida!!.sexo)
        findViewById<EditText>(R.id.txt_editar_mascota_latitud).setText(mascotaRecivida.lat.toString())
        findViewById<EditText>(R.id.txt_editar_mascota_longitud).setText(mascotaRecivida.lon.toString())


        solicitarPermisos()
        val framentoMapa = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        framentoMapa.getMapAsync { googleMap ->
            mapa = googleMap
            establecerConfiguracionMapa()
            mapa.addMarker(
                MarkerOptions()
                    .position(
                        LatLng(
                            findViewById<EditText>(R.id.txt_editar_mascota_latitud).text.toString().toDouble(),
                            findViewById<EditText>(R.id.txt_editar_mascota_longitud).text.toString().toDouble()))
                    .title("Ubicacion Anterior")
            )
            mapa.moveCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(findViewById<EditText>(R.id.txt_editar_mascota_latitud).text.toString().toDouble(),
                    findViewById<EditText>(R.id.txt_editar_mascota_longitud).text.toString().toDouble()), 17f)
            )

            mapa.setOnMapClickListener {
                mapa.clear()
                mapa.addMarker(
                    MarkerOptions()
                        .position(it)
                        .title("Nueva Ubicacion")
                )

                findViewById<EditText>(R.id.txt_editar_mascota_latitud).setText(it.latitude.toString())
                findViewById<EditText>(R.id.txt_editar_mascota_longitud).setText(it.longitude.toString())

            }
        }




        val botonSalir = findViewById<Button>(
            R.id.btn_editar_mascota_cancelar
        )
        botonSalir.setOnClickListener { finish(); }
        val botonAceptar = findViewById<Button>(
            R.id.btn_editar_mascota_aceptar
        )
        botonAceptar.setOnClickListener {

            var mascotaHashMap= hashMapOf(
                "nombre" to findViewById<EditText>(R.id.txt_editar_mascota_nombre).text.toString(),
                "especie" to findViewById<EditText>(R.id.txt_editar_mascota_especie).text.toString(),
                "raza"  to      findViewById<EditText>(R.id.txt_editar_mascota_raza).text.toString(),
                "fechaNac"  to   findViewById<EditText>(R.id.txt_editar_mascota_fecNac).text.toString(),
                "idCliente" to mascotaRecivida.idCliente,
                "sexo" to findViewById<EditText>(R.id.txt_editar_mascota_sexo).text.toString(),
                "lat" to findViewById<EditText>(R.id.txt_editar_mascota_latitud).text.toString().toDouble(),
                "lon" to findViewById<EditText>(R.id.txt_editar_mascota_longitud).text.toString().toDouble()
            )

            val db = Firebase.firestore
            db.collection("mascota").document("${mascotaRecivida.idMascota}").set(mascotaHashMap)
                .addOnSuccessListener {
                    Log.i("firebase","mascota actualizada")
                    Toast.makeText(applicationContext,"Registro Actualizado", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {
                    Log.i("Firebase" ,"Error Actualizando: ${it}")
                }

            finish()
        }

    }

    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermisos) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ), 1
            )
        }




    }
    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true

            }
            uiSettings.isZoomControlsEnabled=true
            uiSettings.isMyLocationButtonEnabled=true//aun no tiene permisos
        }

    }

}

