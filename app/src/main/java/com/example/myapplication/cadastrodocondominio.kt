package com.example.myapplication

import android.content.Intent
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.banco_de_dados.ClienteCondo
import com.example.myapplication.bancodedados.Clientes
import com.example.myapplication.bancodedados.Condominios
import com.example.myapplication.bancodedados.DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class cadastrodocondominio : AppCompatActivity() {
    lateinit var email : EditText
    lateinit var CEP : EditText
    lateinit var CNPJ : EditText
    lateinit var numero : EditText
    lateinit var Cadastrar: Button
    lateinit var nome : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastrodocondominio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        nome = findViewById(R.id.edtnome)
        email = findViewById(R.id.edtEmail)
        CEP = findViewById(R.id.edtCep)
        CNPJ = findViewById(R.id.edtCnpj)
        numero = findViewById(R.id.edtNum)
        Cadastrar = findViewById(R.id.btnCadastrar)
        Cadastrar.setOnClickListener {
            cadastro()
        }
    }

    private val clienteIdAtual: Int by lazy {
        intent.getIntExtra("cliente_id", -1)
    }
    fun cadastro() {
        if (email.text.isEmpty() || CEP.text.isEmpty() || CNPJ.text.isEmpty() || numero.text.isEmpty()) {
            Toast.makeText(this, "CADASTRO DO CONDOMINIO DEU ERRADO!!", Toast.LENGTH_SHORT).show()
            email.setText("")
            CEP.setText("")
            CNPJ.setText("")
            numero.setText("")

        } else {
            val codigogerado = gerarCodigo()
            val obj = Condominios( nome.text.toString(), email.text.toString(), CNPJ.text.toString(), numero.text.toString(), CEP.text.toString(), codigogerado)

            lifecycleScope.launch(Dispatchers.IO) {
                val novoCondominioId = DB.getinstance(this@cadastrodocondominio).getMeuCrudCodominio().Inserir(obj)

                // Agora que temos o ID do condomínio, podemos criar o ClienteCondo
                val obj2 = ClienteCondo(clienteIdAtual, novoCondominioId.toInt(), "sindico")
                DB.getinstance(this@cadastrodocondominio).getMeuCrudClientteCond().Inserir(obj2)
            }
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "CADASTRO DO CONDOMINIO FEITO COM SUCESSO!!", Toast.LENGTH_SHORT)
                .show()

        }
    }
    fun gerarCodigo(): String {
        val caracteres = "0123456789ABCDEFGHIJKLMNOPQ"
        val codigo = StringBuilder()


        for (i in 0 until 6) {
            val indice = (caracteres.indices).random()
            codigo.append(caracteres[indice])
        }

        return codigo.toString()
    }
}