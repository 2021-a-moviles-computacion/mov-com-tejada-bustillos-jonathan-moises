package com.example.moviles_comunicacion_2021_a

class BBaseDatosMemoria {
    companion object{
        val arregloBentrenador = arrayListOf<Bentrenador>()
        init {
            arregloBentrenador.add(Bentrenador("Adrian","a@a.com"))
            arregloBentrenador.add(Bentrenador("Vicente","b@a.com"))
            arregloBentrenador.add(Bentrenador("Carolina","c@a.com"))
            arregloBentrenador.add(Bentrenador("Pedro","d@a.com"))
        }
    }
}