package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class areaSindico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_area_sindico)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var cliente_sindico_id : Int = intent.getIntExtra("cliente_id", 0)

        var BntCadastrarServico = findViewById<TextView>(R.id.btnCadastrarServico)

        BntCadastrarServico.setOnClickListener {
            val intent = Intent(this, sindicoCriarServicos::class.java)
            intent.putExtra("cliente_id", cliente_sindico_id)
            startActivity(intent)
        }

        var btnCadastroDeAvisos : Button = findViewById(R.id.btnCadastroAviso)
        btnCadastroDeAvisos.setOnClickListener {
            val intent = Intent(this, cadastrarAvisos::class.java)
            intent.putExtra("cliente_id", cliente_sindico_id)
            startActivity(intent)
        }

        var btnCadastroDeObras : Button = findViewById(R.id.btnCadastroObra)
        btnCadastroDeObras.setOnClickListener {
            val intent = Intent(this, CadastroObras_Sindico::class.java)
            intent.putExtra("cliente_id", cliente_sindico_id)
            startActivity(intent)
        }




    }
}