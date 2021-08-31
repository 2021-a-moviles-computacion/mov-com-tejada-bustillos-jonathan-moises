package com.example.firebaseuno

data class DTOUser (
    val uid: String = "",
    val email: String = "",
    val roles: ArrayList<String> = arrayListOf()
){
}
