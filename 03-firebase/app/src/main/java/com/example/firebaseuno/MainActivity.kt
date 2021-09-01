package com.example.firebaseuno

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val  CODIGO_INICIO_SESION=102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonLogin = findViewById<Button>(R.id.btn_login)
        botonLogin.setOnClickListener {
            llamarLoginUsuario()
        }

        val botonLogOut= findViewById<Button>(R.id.btn_logout)
        botonLogOut.setOnClickListener {
            llamarLogOutUsuario()
        }
        botonLogOut.visibility=View.INVISIBLE

        val btn_IrProductos = findViewById<Button>(R.id.btn_IrProductos)
        btn_IrProductos.setOnClickListener {
            val intent = Intent(
                this,
                Producto::class.java
            )
            startActivity(intent)
        }
        btn_IrProductos.visibility= View.INVISIBLE
        val btn_IrRestaurante = findViewById<Button>(R.id.btn_restaurante)
        btn_IrRestaurante.setOnClickListener {
            val intent = Intent(
                this,
                Restaurante::class.java
            )
            startActivity(intent)
        }
        btn_IrRestaurante.visibility= View.INVISIBLE

        setBienvenida()
    }

    private fun llamarLogOutUsuario() {
        AuthUI
            .getInstance()
            .signOut(this)
            .addOnCompleteListener {
                AuthUser.usuario = null
                setBienvenida()
            }
    }

    fun llamarLoginUsuario(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html",
                    "https://example.com/privacy.html"
                ).build(),CODIGO_INICIO_SESION
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODIGO_INICIO_SESION -> {
                if (resultCode == Activity.RESULT_OK){
                    val usuario = IdpResponse.fromResultIntent(data)
                    if(usuario?.isNewUser == true){
                        Log.i("firebase-login","Nuevo Usuario")
                        registrarUsuarioPorPrimeraVez(usuario)

                    }else{
                        Log.i("firebase-login","Usuario Antiguo")
                        setUsuarioFirebase()

                    }
                }
                else{
                    Log.i("firebase-login","El usuario cancelo")
                }
            }
        }
    }

    private fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse) {
        val usuarioLogeado = FirebaseAuth.getInstance().getCurrentUser()

        if (usuario.email != null && usuarioLogeado != null){
            val db = Firebase.firestore
            val rolesUsuario = arrayListOf("usuario")
            val idUsuario = usuario.email
            val nuevoUsuario= hashMapOf<String, Any>("roles" to rolesUsuario, "uid" to usuarioLogeado!!.uid,"email" to idUsuario.toString() )

            db.collection("usuario").document(idUsuario.toString()).set(nuevoUsuario).addOnSuccessListener {
                Log.i("firebase-login","Se creo")
                setUsuarioFirebase()
            }
                .addOnFailureListener{
                    Log.i("firebase-login","Fallo")
                }
        }else{
            Log.i("firebase-login","ERROR")

        }
    }

    fun setUsuarioFirebase(){
        val usuarioInstsancia = FirebaseAuth.getInstance()
        val usuarioLocal = usuarioInstsancia.currentUser
        if (usuarioLocal != null) {
            if (usuarioLocal.email != null ){
                val db = Firebase.firestore

                val referencia = db
                    .collection("usuario")
                    .document(usuarioLocal.email.toString())

                referencia
                    .get()
                    .addOnSuccessListener {
                        val usuarioObtenido: DTOUser? =
                            it.toObject(DTOUser::class.java)
                        if (usuarioObtenido != null) {
                            AuthUser.usuario = UsuarioAutenticado(
                                usuarioObtenido.uid,
                                usuarioObtenido.email,
                                usuarioObtenido.roles
                            )
                            setBienvenida()

                        }
                        Log.i("firebase-firestore","Usuario obtenido")
                    }
                    .addOnFailureListener {
                        Log.i("firebase-firestore","Usuario no se pudo obtener")
                    }

            }
        }
    }
    fun setBienvenida() {
        val txv_mensaje = findViewById<TextView>(
            R.id.txv_bienvenida
        )
        val btn_LogIn = findViewById<Button>(R.id.btn_login)
        val btn_LogOut = findViewById<Button>(R.id.btn_logout)
        val btn_IrProductos = findViewById<Button>(R.id.btn_IrProductos)
        val btn_IrRestaurante = findViewById<Button>(R.id.btn_restaurante)
        if (AuthUser.usuario != null) {
            txv_mensaje.text = "Bienvenido a la aplicación ${AuthUser.usuario?.email}"
            btn_LogIn.visibility = View.INVISIBLE
            btn_LogOut.visibility = View.VISIBLE
            btn_IrProductos.visibility = View.VISIBLE
            btn_IrRestaurante.visibility = View.VISIBLE
        }
        else {
            txv_mensaje.text = "Ingrese a la aplicación por favor"
            btn_LogIn.visibility = View.VISIBLE
            btn_LogOut.visibility = View.INVISIBLE
            btn_IrProductos.visibility = View.INVISIBLE
            btn_IrRestaurante.visibility = View.INVISIBLE
        }
    }

}