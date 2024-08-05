package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.bancodedados.Avisos
import com.example.myapplication.bancodedados.DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class cadastrarAvisos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastrar_avisos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var ClienteID = intent.getIntExtra("cliente_id",0)
        var CondoClienteID = 0

        GlobalScope.launch(Dispatchers.IO) {
            CondoClienteID =
                DB.getinstance(this@cadastrarAvisos)
                    .getMeuCrudClientteCond()
                    .GetComdominioByClienteId(ClienteID)[0].id
        }


        var titulotxt : EditText = findViewById(R.id.txtTituloDoAviso)
        var Desctxt : EditText = findViewById(R.id.txtDescricaoDoAviso)

        var btnCadastroAviso : Button = findViewById(R.id.btnCadastrarAvisos)
        btnCadastroAviso.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                DB.getinstance(this@cadastrarAvisos)
                    .getMeuCrudAvisos()
                    .Inserir(
                        Avisos(
                            "${titulotxt.text}",
                            "${Desctxt.text}",
                            "${CondoClienteID}",
                            "${ClienteID}"
                        )
                    )
                Toast.makeText(
                    this@cadastrarAvisos,
                    "Aviso cadastrado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }




    }
}