package com.example.myapplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.banco_de_dados.servicosClass
import com.example.myapplication.bancodedados.Obras


class AdaptadorObras(listaObra : List<Obras>) : RecyclerView.Adapter<Servicos>() {




    var obras : List<Obras> = listaObra

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Servicos {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return Servicos(view)
    }

    override fun getItemCount(): Int {
        return  obras.size
    }

    override fun onBindViewHolder(holder: Servicos, position: Int) {
        holder.nomeServico.text = obras[position].titulo
        holder.descricaoServico.text = obras[position].descricao
        holder.localobra.text = obras[position].local
    }
}