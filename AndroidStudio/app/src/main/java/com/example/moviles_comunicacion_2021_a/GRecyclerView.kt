package com.example.moviles_comunicacion_2021_a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)
        val listaEntrenador = arrayListOf<Bentrenador>()

        //val ligaPokemon = DLiga("kanto","Liga Kanto")
        listaEntrenador.add(Bentrenador("Jonathan","175454"))
    }
}