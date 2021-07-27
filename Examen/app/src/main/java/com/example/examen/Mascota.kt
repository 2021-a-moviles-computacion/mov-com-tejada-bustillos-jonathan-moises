package com.example.examen
import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate

import java.util.*

class Mascota (
    private var nombre:String ?=null,
    private var especie:String ?=null,
    private var fechaNac:String ?=null,
    private var idMascota:Int ?=null,
    private var idCliente:Int ?=null,
    private var sexo:Char ?=null,
    private var raza:String ?=null,
    ):Parcelable{


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Char::class.java.classLoader) as? Char
    ) {
    }

    fun getNombre():String?{
        return this.nombre
    }

    fun setNombre(nombre:String){
        this.nombre=nombre
    }

    fun getEspecie():String?{
        return this.especie
    }

    fun setEspecie(especie:String){
        this.especie=especie
    }
    fun getRaza(): String? {
        return this.raza
    }

    fun setRaza(raza:String){
        this.raza=raza
    }

    fun getFechaNac():String?{
        return this.fechaNac
    }

    fun setFechaNac(fechaNac: String?){
        this.fechaNac=fechaNac
    }
    fun getIdCliente():Int?{
        return this.idCliente
    }

    fun setIdCliente(idCliente: Int?){
        this.idCliente=idCliente
    }
    fun getIdMascota():Int?{
        return this.idMascota
    }

    fun setIdMascota(idMascota: Int?){
        this.idMascota=idMascota
    }
    fun getSexo():Char?{
        return this.sexo
    }

    fun setSexo(sexo:Char){
        this.sexo=sexo
    }


    override fun toString(): String {
        return idCliente.toString()+","+idMascota+","+nombre+","+especie+","+fechaNac+","+sexo.toString()+"\n"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(especie)
        parcel.writeString(fechaNac)
        parcel.writeValue(idMascota)
        parcel.writeValue(idCliente)
        parcel.writeValue(sexo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mascota> {
        override fun createFromParcel(parcel: Parcel): Mascota {
            return Mascota(parcel)
        }

        override fun newArray(size: Int): Array<Mascota?> {
            return arrayOfNulls(size)
        }
    }

}