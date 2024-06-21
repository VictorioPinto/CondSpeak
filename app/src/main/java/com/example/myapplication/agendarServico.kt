package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.banco_de_dados.ClienteCondo
import com.example.myapplication.banco_de_dados.servicosClass
import com.example.myapplication.banco_de_dados.servicosCliente
import com.example.myapplication.bancodedados.DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class agendarServico : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agendar_servico)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





        var MeuSpinnerDeServicos : Spinner = findViewById(R.id.spinnerServicosdoCond)
        var ArrayDosServicoesDoSeuCondominio = mutableListOf("")
        GlobalScope.launch(Dispatchers.IO){
            val ListaDeServicos = DB.getinstance(this@agendarServico).getMeuCrudServicos().ler()
            for (i in ListaDeServicos)
            {
                ArrayDosServicoesDoSeuCondominio.add(i.nome.toString())
            }
        }


        val AdaptadorDoSpinner  = ArrayAdapter(this, android.R.layout.simple_spinner_item, ArrayDosServicoesDoSeuCondominio)
        MeuSpinnerDeServicos.adapter = AdaptadorDoSpinner




        val clienteIdAtual: Int by lazy {
            intent.getIntExtra("cliente_id", 1)
        }


        var NomeCliente : String = ""
        var lblCliente : TextView = findViewById(R.id.edittxtCliente)
        GlobalScope.launch(Dispatchers.IO) {
            val clienteNome = DB.getinstance(this@agendarServico).getMeuCrudCliente().getClienteByIdAuxTabela(clienteIdAtual)
            NomeCliente = clienteNome[0].nome
            lblCliente.text = "Morador: ${NomeCliente}"
        }




        var condoIdAux = 0
        var EditCondoNome : TextView = findViewById(R.id.txtCondominioNome)
        GlobalScope.launch(Dispatchers.IO) {
            var condoId =
                DB.getinstance(this@agendarServico).getMeuCrudClientteCond().GetComdominioByClienteId(clienteIdAtual)
            condoIdAux = condoId[0].id

            var NomeCondominioAux = DB.getinstance(this@agendarServico).getMeuCrudCodominio().getCondominioById(condoIdAux)
            EditCondoNome.text = "Local: Condominio do ${NomeCondominioAux[0].nome}"

        }



        var BotaoCriarServico : Button = findViewById(R.id.btnAgendarAoSindico)
        BotaoCriarServico.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
              DB.getinstance(this@agendarServico).getMeuCrudServicosCliente()
                  .Inserir(
                      servicosCliente(
                          "${MeuSpinnerDeServicos.selectedItem}",
                          "${condoIdAux}",
                          "${clienteIdAtual}",
                          "Esperando resposta"
                      )
                  )
                Toast.makeText(applicationContext, "SUCESSO! Servico Agendado",
                    Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this, telainicial::class.java))
        }






    }
}