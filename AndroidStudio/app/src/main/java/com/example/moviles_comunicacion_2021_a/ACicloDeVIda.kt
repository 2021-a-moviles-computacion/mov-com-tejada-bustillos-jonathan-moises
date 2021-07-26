package com.example.moviles_comunicacion_2021_a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ACicloDeVIda : AppCompatActivity() {

    var numero= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_de_vida)
        Log.i("ciclo-vida","onCreate")

        val textViewACicloVIda = findViewById<TextView>(
            R.id.txv_ciclo_vida
        )
        textViewACicloVIda.text=numero.toString()

        val buttonAcicloVida = findViewById<Button>(
            R.id.btn_aumentar_ciclo_vida
        )
        buttonAcicloVida.setOnClickListener{aumentarNumero()}

    }

    fun aumentarNumero(){
        numero = numero  + 1
        val textViewACicloDeVIda= findViewById<TextView>(
            R.id.txv_ciclo_vida
        )
        textViewACicloDeVIda.text=numero.toString()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run{
            //aqui se guarda primitivos
            putInt("numeroGuardado",numero)
        }
        super.onSaveInstanceState(outState)
        Log.i("ciclo-vida","onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val numeroRecuperado:Int? = savedInstanceState.getInt("numeroGuardado")
        if(numeroRecuperado != null){

            numero=numeroRecuperado
            val textViewACicloDeVIda= findViewById<TextView>(
                R.id.txv_ciclo_vida
            )
            textViewACicloDeVIda.text=numero.toString()
        }
        Log.i("ciclo-vida","onRestoreInstanceState")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida","onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida","onDestroy")
    }

}