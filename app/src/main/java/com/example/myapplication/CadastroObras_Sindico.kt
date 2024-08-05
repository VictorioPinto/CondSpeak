package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.banco_de_dados.ClienteCondo
import com.example.myapplication.bancodedados.Avisos
import com.example.myapplication.bancodedados.DB
import com.example.myapplication.bancodedados.Obras
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class CadastroObras_Sindico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro_obras_sindico)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        var titulotxt : EditText = findViewById(R.id.tituloID)
        var descricaotxt : EditText = findViewById(R.id.DescricaoID)
        var localObra : EditText = findViewById(R.id.localID)
        var sindicoNome : String = ""

        var sindicoID = intent.getIntExtra("cliente_id",999)
        var CondoID : ClienteCondo = ClienteCondo(0,0,0,"")

        GlobalScope.launch(Dispatchers.IO) {
            CondoID = DB.getinstance(this@CadastroObras_Sindico)
                .getMeuCrudClientteCond().GetComdominioByClienteId(sindicoID)[0]

            sindicoNome = DB.getinstance(this@CadastroObras_Sindico)
                .getMeuCrudCliente().getClienteById(
                    sindicoID
                )[0].nome
            var sindoNomelbl : TextView = findViewById(R.id.nomeDoSindicoID)
            sindoNomelbl.text = "Nome do sindico: ${sindicoNome}"
        }



        var condlbl : TextView = findViewById(R.id.nomedoCondID)
        condlbl.text = "ID do condominio: ${CondoID.Condoid}"


        var btnCadastroAviso : Button = findViewById(R.id.botaoCriar)
        btnCadastroAviso.setOnClickListener {

            GlobalScope.launch(Dispatchers.IO) {
                DB.getinstance(this@CadastroObras_Sindico)
                    .getMeuCrudObras()
                    .Inserir(
                        Obras(
                            "${titulotxt.text}",
                            "${descricaotxt.text}",
                            "${localObra.text}",
                            "${sindicoID}",
                            "${CondoID.id}"
                        )
                    )

            }
        }



    }





}