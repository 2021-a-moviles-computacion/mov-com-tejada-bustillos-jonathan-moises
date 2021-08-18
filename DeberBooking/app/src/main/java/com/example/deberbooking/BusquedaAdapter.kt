package com.example.deberbooking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BusquedaAdapter(private var lista:ArrayList<busqueda>, private var contexo: Context):
    RecyclerView.Adapter<BusquedaAdapter.ViewHolder>(){

    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {

        var imagen: ImageView
        var ciudad: TextView
        var descripcion: TextView

        init {
            imagen = vista.findViewById(R.id.image_busqueda)
            ciudad = vista.findViewById(R.id.txt_ciudad)
            descripcion= vista.findViewById(R.id.txt_descripcion_busqueda)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_continua_tu_busqueda,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val holderItem=lista[position]
        println("Tamaño de la lista"+lista.size)
        holder.imagen.setImageResource(holderItem.imagen)
        holder.ciudad.text = holderItem.nombre
        holder.descripcion.text=holderItem.descripcion
    }

    override fun getItemCount(): Int {
        return  lista.size
    }
}