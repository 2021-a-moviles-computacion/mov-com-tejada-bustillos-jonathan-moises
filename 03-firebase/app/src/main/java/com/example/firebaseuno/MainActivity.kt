package com.example.firebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.firebase.ui.auth.AuthUI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonLogin = findViewById<Button>(R.id.btn_login)
        botonLogin.setOnClickListener {
            llamarLoginUsuario()
        }

    }

    fun llamarLoginUsuario(){
        val providers = arrayListOf(
            AuthUI.IdpConfig
        )
    }


}