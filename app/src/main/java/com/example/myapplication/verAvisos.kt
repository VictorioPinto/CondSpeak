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
import com.example.myapplication.banco_de_dados.ClienteCondo
import com.example.myapplication.bancodedados.DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class verAvisos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_avisos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var ClineteID : Int = intent.getIntExtra("cliente_id", 1)

        var botaoVerAvisos : Button = findViewById(R.id.btnVerAvisos)
        botaoVerAvisos.setOnClickListener {
            startActivity(Intent(this, telainicial::class.java))
        }
        var condo : List<ClienteCondo>

        GlobalScope.launch(Dispatchers.IO) {
            condo = DB.getinstance(this@verAvisos).
            getMeuCrudClientteCond().
            GetComdominioByClienteId(ClineteID)
            var condoID : Int = condo[0].Condoid ?: 0
            var meureciclador : RecyclerView =
                findViewById(R.id.meuReciclador)

            meureciclador.adapter = AdaptadorAvisos(
                DB.getinstance(this@verAvisos).getMeuCrudAvisos()
                    .getByCondID(condoID)
            )

            meureciclador.layoutManager = LinearLayoutManager(this@verAvisos)

        }




    }
}

