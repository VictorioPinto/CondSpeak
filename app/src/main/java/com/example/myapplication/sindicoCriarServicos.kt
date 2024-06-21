package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.banco_de_dados.servicosClass
import com.example.myapplication.bancodedados.DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class sindicoCriarServicos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sindico_criar_servicos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var comdoNome = ""
        var comdoID = ""
        var clienteIDcomp = intent.getIntExtra("clienteID",1)
        var comdoTxt : TextView = findViewById(R.id.editCondominioSindico)
        GlobalScope.launch(Dispatchers.IO) {
            comdoNome = DB.getinstance(this@sindicoCriarServicos)
                .getMeuCrudCodominio().getCondominioById(
                    DB.getinstance(this@sindicoCriarServicos)
                        .getMeuCrudClientteCond().
                        GetComdominioByClienteId(clienteIDcomp)[0].id
                )[0].nome

            comdoID = DB.getinstance(this@sindicoCriarServicos)
                .getMeuCrudCodominio().getCondominioById(
                    DB.getinstance(this@sindicoCriarServicos)
                        .getMeuCrudClientteCond().
                        GetComdominioByClienteId(clienteIDcomp)[0].id
                )[0].id.toString()
            comdoTxt.text = "Condomínio (nome): $comdoNome"
        }

        var BotaoCadastrar : Button = findViewById(R.id.btnCadastroSindico)
        var ServicoNome : EditText = findViewById(R.id.nomeDescServicoSindico)
        var ServicoDescricao : EditText = findViewById(R.id.txtDescServicoSindico)





        BotaoCadastrar.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                DB.getinstance(this@sindicoCriarServicos)
                    .getMeuCrudServicos().Inserir(
                        servicosClass(
                            "${ServicoNome.text}",
                            "${ServicoDescricao.text}",
                            "${comdoID}"
                        )
                    )
            }
            startActivity(Intent(this, telainicial::class.java))
        }
    }
}