package com.example.moviles_comunicacion_2021_a

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO=700
    val CODIGO_RESPUESTA_INTENT_IMPLICITO=700

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //boton ciclo de vida
        val botonIrACicloDeVIda = findViewById<Button>(
            R.id.btn_ciclo_vida
        )
        botonIrACicloDeVIda.setOnClickListener{abrirActividad(ACicloDeVIda::class.java)}
        // boton Lista view
        val botonIrBlistView = findViewById<Button>(
            R.id.btn_ir_listview
        )
        botonIrBlistView.setOnClickListener{abrirActividad(BListView::class.java)}
        // boton intent

        val botonIrIntent = findViewById<Button>(R.id.btn_it_intent)
        botonIrIntent.setOnClickListener {
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }

        val botonAbrirIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)

        botonAbrirIntentImplicito.setOnClickListener {
            val intentConRespuesta = Intent(Intent.ACTION_PICK,ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
            startActivityForResult(intentConRespuesta,CODIGO_RESPUESTA_INTENT_IMPLICITO)
        }
        val botonIrHttp = findViewById<Button>(R.id.btn_ir_http)

        botonIrHttp.setOnClickListener {
           abrirActividadConParametros(HHttpActivity::class.java)
        }


    }

    fun abrirActividad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentExplicito)
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito= Intent(
            this,clase
        )
        intentExplicito.putExtra("nombre","Jonathan")
        intentExplicito.putExtra("apellido","Tejada")
        intentExplicito.putExtra("edad",26)
        intentExplicito.putExtra("entrenador",Bentrenador("Jhon","Tejada"))
        //startActivity(intentExplicito)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
        /*
         registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
            Activity.RESULT_OK-> {
            //Ejecutar codigo OK
            it.data?.getStringExtra("nombreModofocado")
            it.data?.getIntExtra("edadModificada",0)
            it.data?.getParcelableExtra<BEntrenador>("entrenadorModificado")
            }
            }
        }
         */
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            CODIGO_RESPUESTA_INTENT_EXPLICITO->{
                if (resultCode== RESULT_OK){
                    Log.i("intetnt-explicito","Siactualizo los datos")
                    if(data !=null){
                        val nombre = data.getStringExtra("nombreModificado")
                        val edad = data.getIntExtra("edadModificada",0)
                        val entrenador = data.getParcelableExtra<Bentrenador>("entrenadorModificado")
                        Log.i("intent-explicito","${nombre}")
                        Log.i("intent-explicito","${edad}")
                        Log.i("intent-explicito","${entrenador}")


                    }
                }
            }

            CODIGO_RESPUESTA_INTENT_IMPLICITO->{
                if (resultCode == RESULT_OK){
                    if (data != null){
                        if (data.data !=null){
                            val uri: Uri = data.data!!
                            val cursor = contentResolver.query(
                                uri,null,null,null,null,null
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(indiceTelefono!!)
                            cursor?.close()
                            Log.i("resultado","${telefono}")
                        }
                    }




                }
            }
        }
    }
}