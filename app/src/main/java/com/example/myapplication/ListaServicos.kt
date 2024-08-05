package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.bancodedados.DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListaServicos : AppCompatActivity() {

    lateinit var recyclerViewEscudos: RecyclerView
    lateinit var botaoVoltar : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_servicos)

        recyclerViewEscudos = findViewById(R.id.reciclador)
        botaoVoltar = findViewById(R.id.button)



        var CondID = intent.getIntExtra("cliente_id",1)
        GlobalScope.launch(Dispatchers.IO){
            recyclerViewEscudos.adapter = AdaptadorListaServicos(
                DB.getinstance(this@ListaServicos)
                .getMeuCrudServicos()
                .getByCond(CondID.toString())
            )

        }


        recyclerViewEscudos.layoutManager = LinearLayoutManager(this)
       botaoVoltar.setOnClickListener{ telaIniciala() }
    }


    fun telaIniciala(){
        startActivity(Intent(this, telainicial::class.java))
    }
}