package com.example.deberbooking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PromocionesAdapter(private var lista:ArrayList<Promociones>, private var contexo: Context):
    RecyclerView.Adapter<PromocionesAdapter.ViewHolder>(){

    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {

        var imagen: ImageView
        var nombre: TextView
        var descripcion: TextView

        init {
            imagen = vista.findViewById(R.id.image_oferta)
            nombre = vista.findViewById(R.id.txt_nombre_oferta)
            descripcion= vista.findViewById(R.id.txt_descripcion_oferta)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromocionesAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rc_ofertasdeldia,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val holderItem=lista[position]
        println("Tamaño de la lista"+lista.size)
        holder.imagen.setImageResource(holderItem.imagen)
        holder.nombre.text = holderItem.titulo
        holder.descripcion.text=holderItem.descricion
    }


}