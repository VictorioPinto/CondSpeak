package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.bancodedados.DB
import com.example.myapplication.bancodedados.Obras
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VizualizazarObras : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vizualizazar_obras)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var ClineteID : Int = intent.getIntExtra("cliente_id", 1)


        var Obras : List<Obras>

        GlobalScope.launch(Dispatchers.IO) {


            var meureciclador : RecyclerView = findViewById(R.id.recicladorObras)

            meureciclador.adapter = AdaptadorObras(
                DB.getinstance(this@VizualizazarObras)
                    .getMeuCrudObras()
                    .getObrasBySindicoId(ClineteID)
            )

            meureciclador.layoutManager = LinearLayoutManager(this@VizualizazarObras)

        }
    }
}