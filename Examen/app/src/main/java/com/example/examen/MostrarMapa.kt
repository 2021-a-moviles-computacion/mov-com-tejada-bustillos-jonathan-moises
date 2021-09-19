package com.example.examen

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MostrarMapa : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_mapa)
        solicitarPermisos()
        val mascotaRecivida = intent.getParcelableExtra<Mascota>("Mascota")

        findViewById<TextView>(R.id.tv_mascota).text=mascotaRecivida!!.nombre
        findViewById<TextView>(R.id.tv_latitud).text=mascotaRecivida!!.lat.toString()
        findViewById<TextView>(R.id.tv_longitud).text=mascotaRecivida!!.lon.toString()
        val framentoMapa = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        framentoMapa.getMapAsync { googleMap ->
            mapa = googleMap
            establecerConfiguracionMapa()
            mapa.addMarker(
                MarkerOptions()
                    .position(LatLng(mascotaRecivida!!.lat!!, mascotaRecivida!!.lon!!))
                    .title("${mascotaRecivida.nombre.toString()}")
            )
            mapa.moveCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(mascotaRecivida!!.lat!!, mascotaRecivida.lon!!.toDouble()), 17f)
            )

            mapa.uiSettings.isScrollGesturesEnabled=false
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