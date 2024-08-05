package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.banco_de_dados.ClienteCondo
import com.example.myapplication.banco_de_dados.CrudClienteCond
import com.example.myapplication.bancodedados.CRUDClientes
import com.example.myapplication.bancodedados.CRUDCondominios
import com.example.myapplication.bancodedados.DB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class codigocondominio : AppCompatActivity() {
    lateinit var code: EditText
    lateinit var entrar: Button
    lateinit var telacadcondominio: TextView
    private lateinit var condominioDao: CRUDCondominios
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_codigocondominio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        telacadcondominio = findViewById(R.id.txtCadCond)
        code = findViewById(R.id.edtCode)
        entrar = findViewById(R.id.btnentrar)

        condominioDao = DB.getinstance(this).getMeuCrudCodominio()
        telacadcondominio.setOnClickListener {
            caddocondominio(clienteIdAtual)
        }
        entrar.setOnClickListener {
            val codigoCondominio = code.text.toString()
            entra(codigoCondominio)
        }
    }



    private val clienteIdAtual: Int by lazy {
        intent.getIntExtra("cliente_id", -1)
    }
    private fun entra(codigoCondominio: String) {
        // 1. Buscar o condomínio pelo código:
        CoroutineScope(Dispatchers.IO).launch {
            val condominio = condominioDao.getCondominioByCode(codigoCondominio)

            if (condominio != null) {
                val condoid = fetchCondominioIdByCode(code.text.toString())
                if (condoid != null) { // Verifica se condoid não é nulo
                    val obj = ClienteCondo(clienteIdAtual, condoid, "Morador")
                    lifecycleScope.launch(Dispatchers.IO) {
                        DB.getinstance(this@codigocondominio).getMeuCrudClientteCond().Inserir(obj)
                    }


                    // 3. Notificar o usuário do sucesso:
                    runOnUiThread {
                        Toast.makeText(
                            this@codigocondominio,
                            "Condomínio vinculado com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    proximapagina(clienteIdAtual)
                } else {
                    // Lidar com o caso em que o condomínio não foi encontrado
                    runOnUiThread {
                        Toast.makeText(
                            this@codigocondominio,
                            "Condomínio não encontrado.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                // 4. Notificar o usuário do erro:
                runOnUiThread {
                    Toast.makeText(this@codigocondominio, "Condomínio não encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun proximapagina(clienteId: Int) {
        val intent = Intent(this, telainicial::class.java)
        intent.putExtra("cliente_id", clienteId)
        startActivity(intent)
    }
    private fun caddocondominio(clienteId: Int) {
        val intent = Intent(this, cadastrodocondominio::class.java)
        intent.putExtra("cliente_id", clienteId)
        startActivity(intent)
    }


    suspend fun fetchCondominioIdByCode(codigo: String): Int? {
        val condominio = condominioDao.getCondominioByCode(codigo)
        return condominio?.id
    }
}