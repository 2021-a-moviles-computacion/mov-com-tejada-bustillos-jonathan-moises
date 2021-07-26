package com.example.moviles_comunicacion_2021_a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreCedula (
    private val contexto: Class<*>,
    private val listaEntrenadores: List<Bentrenador>,
    private val recyclerView: RecyclerView
    ): RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyViewHolder>() {
        inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

            val nombreTextView: TextView
            val cedulaTExtView: TextView
            val likesTextView: TextView
            val accionButton: Button
            var numeroLikes=0

            init {
                 nombreTextView = view.findViewById(R.id.tv_nombre)
                cedulaTExtView = view.findViewById(R.id.tv_cedula)
                likesTextView= view.findViewById(R.id.tv_likes)
                accionButton= view.findViewById(R.id.btn_dar_like)
                accionButton.setOnClickListener {
                    this.anadirLike() }
            }

            fun anadirLike(){
                likesTextView.text=numeroLikes++.toString()
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false,
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenador = listaEntrenadores[position]
        holder.nombreTextView.text= entrenador.nombre
        holder.cedulaTExtView.text=entrenador.descripcion
        holder.accionButton.text="like ${entrenador.nombre}"
        holder.likesTextView.text="0"
    }

    override fun getItemCount(): Int {
        return listaEntrenadores.size
    }


}