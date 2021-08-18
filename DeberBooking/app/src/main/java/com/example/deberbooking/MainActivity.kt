package com.example.deberbooking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaBusqueda=ArrayList<busqueda> ()
        var listaPromociones=ArrayList<Promociones>()

        listaBusqueda.add(busqueda(R.mipmap.quito,"Quito","2021/18/08 - 2021/19/08"))
        listaBusqueda.add(busqueda(R.mipmap.guayaquil2,"Guayaquil","2021/18/08 - 2021/19/08"))
        listaBusqueda.add(busqueda(R.mipmap.cuenca, "Cuenca", "2021/18/08 - 2021/19/08"))

        val rvBusqueda = findViewById<RecyclerView>(R.id.rv_busqueda)
        rvBusqueda.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        rvBusqueda.adapter=BusquedaAdapter(listaBusqueda,this)

        listaPromociones.add(Promociones(R.mipmap.uno,"Semana de descuento","hasta el 2021/21/08 "))
        listaPromociones.add(Promociones(R.mipmap.dos,"Fin de semana","desde 2021/20/08 hasta el 2021/22/08 "))

        val rvPromociones = findViewById<RecyclerView>(R.id.rv_ofertas)
        rvPromociones.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        rvPromociones.adapter = PromocionesAdapter(listaPromociones,this)
    }
}