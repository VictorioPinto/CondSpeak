package com.example.myapplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.banco_de_dados.servicosClass


class AdaptadorListaServicos(listaServicos : List<servicosClass>) : RecyclerView.Adapter<Servicos>() {




    var servico : List<servicosClass> = listaServicos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Servicos {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return Servicos(view)
    }

    override fun getItemCount(): Int {
        return  servico.size
    }

    override fun onBindViewHolder(holder: Servicos, position: Int) {
        holder.nomeServico.text = servico[position].nome
        holder.descricaoServico.text = servico[position].desc
    }
}