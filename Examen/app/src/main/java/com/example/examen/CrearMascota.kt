package com.example.examen

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

class CrearMascota : AppCompatActivity() {

    private lateinit var mapa: GoogleMap
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_mascota)

        solicitarPermisos()

        val framentoMapa = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        framentoMapa.getMapAsync { googleMap ->
            mapa = googleMap
            establecerConfiguracionMapa()
            mapa.addMarker(
                MarkerOptions()
                    .position(LatLng(-0.18288452555, -78.48449971346))
                    .title("Punto de referencia")
            )
            mapa.moveCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(-0.18288452555, -78.48449971346), 17f)
            )

            mapa.setOnMapClickListener {
                mapa.clear()
                mapa.addMarker(
                    MarkerOptions()
                        .position(it)
                        .title("NuevaUbicacion")
                )

                findViewById<EditText>(R.id.txt_registrar_mascota_latitud).setText(it.latitude.toString())
                findViewById<EditText>(R.id.txt_registrar_mascota_Longitud).setText(it.longitude.toString())

            }
        }


        val idCliente = intent.getStringExtra("idCliente")
        val botonSalir = findViewById<Button>(
            R.id.btn_registrar_mascota_cancelar
        )
        botonSalir.setOnClickListener { finish(); }

        val botonAceptar = findViewById<Button>(
            R.id.btn_registrar_mascota_aceptar
        )
        botonAceptar.setOnClickListener {

            val db = Firebase.firestore

            val mascota = hashMapOf(
                "nombre" to findViewById<EditText>(R.id.txt_registrar_mascota_nombre).text.toString(),
                "especie" to findViewById<EditText>(R.id.txt_registrar_mascota_especie).text.toString(),
                "fechaNac" to findViewById<EditText>(R.id.txt_registrar_mascota_fecNac).text.toString(),
                "idCliente" to idCliente,
                "sexo" to findViewById<EditText>(R.id.txt_registrar_mascota_sexo).text.toString(),
                "raza" to findViewById<EditText>(R.id.txt_registrar_mascota_raza).text.toString(),
                "lat" to findViewById<EditText>(R.id.txt_registrar_mascota_latitud).text.toString()
                    .toDouble(),
                "lon" to findViewById<EditText>(R.id.txt_registrar_mascota_Longitud).text.toString()
                    .toDouble()
            )

            db.collection("mascota").add(mascota).addOnSuccessListener {
                Log.i("firebase", "Mascota Registrada")
                Toast.makeText(applicationContext, "Mascota Registrada", Toast.LENGTH_SHORT).show()

                finish()

            }
                .addOnFailureListener {
                    Log.i("firebase", "Error registrando mascota")
                }


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
