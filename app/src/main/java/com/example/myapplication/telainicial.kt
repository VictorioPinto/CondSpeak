package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.banco_de_dados.servicosClass
import com.example.myapplication.bancodedados.DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text

lateinit var botaoServicos : Button

class telainicial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_telainicial)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var ClineteID : Int = intent.getIntExtra("cliente_id", 1)
        var welcome : TextView = findViewById(R.id.txtBemVindo)






        GlobalScope.launch(Dispatchers.IO) {
            var a  = DB.getinstance(this@telainicial).getMeuCrudCliente().getClienteById(ClineteID)[0].nome
            welcome.text = "Bem vindo(a) ${a}"
        }




        botaoServicos = findViewById(R.id.btnAgendarServicos)


        botaoServicos.setOnClickListener {
            startActivity( Intent(this, agendarServico::class.java).putExtra("cliente_id",ClineteID ) )
        }
        var BotaoAreaSindico : Button = findViewById(R.id.btnAreaDoSindico)


        var BotaoConsultarServicos : Button = findViewById(R.id.btnConsultarServicos)
        BotaoConsultarServicos.setOnClickListener {
            startActivity( Intent(this, ListaServicos::class.java).putExtra("cliente_id",ClineteID) )
        }


        BotaoAreaSindico.setOnClickListener {
          GlobalScope.launch(Dispatchers.IO)
            {
              var sindicoOuNao = DB.getinstance(this@telainicial).getMeuCrudClientteCond().GetComdominioByClienteId(ClineteID)
               if (sindicoOuNao[0].Role == "sindico"){

                  val intent2 = Intent(this@telainicial, areaSindico::class.java)
                   intent2.putExtra("cliente_id", ClineteID)
                    startActivity(intent2)

                }else{
                    Toast.makeText(applicationContext,  "voce nao é sindico", Toast.LENGTH_SHORT).show()
        }
          }


        }

        var botaoObras : Button = findViewById(R.id.btnObras)
        botaoObras.setOnClickListener {
            startActivity( Intent(this, VizualizazarObras::class.java).putExtra("cliente_id",ClineteID) )
        }


        var botaoAvisos : Button = findViewById(R.id.btnAvisos)
        botaoAvisos.setOnClickListener {
            startActivity( Intent(this, verAvisos::class.java).putExtra("cliente_id",ClineteID) )
        }


    }
}