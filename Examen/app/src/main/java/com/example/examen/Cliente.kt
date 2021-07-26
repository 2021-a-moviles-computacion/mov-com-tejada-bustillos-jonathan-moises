package com.example.examen
import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Cliente(
    private var nombre:String ?=null,
    private var apellido:String ?=null,
    private var idCliente:Int ?=null,
    private var cedula:String ?=null,
    private var direccion:String ?=null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    ) {
    }


    fun getNombre():String?{
        return this.nombre
    }

    fun setNombre(nombre:String){
        this.nombre=nombre
    }

    fun getApellido():String?{
        return this.apellido
    }

    fun setApellido(apellido:String){
        this.apellido=apellido
    }



    fun getIdCliente():Int?{

        return this.idCliente
    }

    fun setIdCliente(idCliente:Int) {

        this.idCliente = idCliente
    }

    fun getDireccion():String?{
        return this.direccion
    }

    fun setDireccion(direccion:String){

        this.direccion=direccion
    }

    fun getCedula():String?{
        return this.cedula
    }

    fun setCedula(cedula:String){
        this.cedula=cedula
    }



    override fun toString(): String {
        return idCliente.toString()+","+nombre+","+apellido+","+cedula+","+direccion+"\n"

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeValue(idCliente)
        parcel.writeString(cedula)
        parcel.writeString(direccion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cliente> {
        override fun createFromParcel(parcel: Parcel): Cliente {
            return Cliente(parcel)
        }

        override fun newArray(size: Int): Array<Cliente?> {
            return arrayOfNulls(size)
        }
    }


}