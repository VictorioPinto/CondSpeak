package com.example.myapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Servicos(view : View) : RecyclerView.ViewHolder(view) {
    var nomeServico : TextView = view.findViewById(R.id.txtNomeServico)
    var descricaoServico : TextView = view.findViewById(R.id.txtDescricaoServico)
    var localobra : TextView = view.findViewById(R.id.txtlocal)

}