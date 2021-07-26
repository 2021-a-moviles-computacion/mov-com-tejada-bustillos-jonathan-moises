package com.example.moviles_comunicacion_2021_a

import android.os.Parcel
import android.os.Parcelable

class Bentrenador (val nombre: String?,val descripcion: String?):Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }


    override fun toString(): String {
        return "${nombre}-${descripcion}"
    }

    override fun describeContents(): Int {

        return 0
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(nombre)
        parcel?.writeString(descripcion)


    }

    companion object CREATOR : Parcelable.Creator<Bentrenador> {
        override fun createFromParcel(parcel: Parcel): Bentrenador {
            return Bentrenador(parcel)
        }

        override fun newArray(size: Int): Array<Bentrenador?> {
            return arrayOfNulls(size)
        }
    }

}

