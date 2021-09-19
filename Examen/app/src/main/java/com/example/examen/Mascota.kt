package com.example.examen
import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate

import java.util.*

class Mascota (
     var nombre:String ?=null,
     var especie:String ?=null,
     var fechaNac:String ?=null,
     var idMascota:String ?=null,
     var idCliente:String ?=null,
     var sexo:String ?=null,
     var raza:String ?=null,
     var lat:Double?=null,
     var lon:Double?=null
    ):Parcelable{


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(especie)
        parcel.writeString(fechaNac)
        parcel.writeString(idMascota)
        parcel.writeString(idCliente)
        parcel.writeString(sexo)
        parcel.writeString(raza)
        parcel.writeValue(lat)
        parcel.writeValue(lon)
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

    override fun toString(): String {
        return "Nombre: $nombre  | Sexo: $sexo\nEspecie: $especie  | Raza: $raza"
    }


}