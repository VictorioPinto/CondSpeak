package com.example.myapplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.banco_de_dados.servicosClass
import com.example.myapplication.bancodedados.Avisos


class AdaptadorAvisos(listaAvisos : List<Avisos>) : RecyclerView.Adapter<Servicos>() {




    var listaAviso : List<Avisos> = listaAvisos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Servicos {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return Servicos(view)
    }

    override fun getItemCount(): Int {
        return  listaAviso.size
    }

    override fun onBindViewHolder(holder: Servicos, position: Int) {
        holder.nomeServico.text = listaAviso[position].titulo
        holder.descricaoServico.text = listaAviso[position].descricao
    }
}