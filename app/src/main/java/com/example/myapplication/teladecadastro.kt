package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.bancodedados.CRUDClientes
import com.example.myapplication.bancodedados.Clientes
import com.example.myapplication.bancodedados.DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class teladecadastro : AppCompatActivity() {
    lateinit var nome : EditText
    lateinit var email : EditText
    lateinit var senha : EditText
    lateinit var telefone : EditText
    lateinit var cpf : EditText
    lateinit var VoltarConta : TextView
    lateinit var CadastrarConta : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teladecadastro)
        telefone = findViewById(R.id.edttelefone)
        nome = findViewById(R.id.edtNome)
        email = findViewById(R.id.edtEmail)
        senha = findViewById(R.id.edtSenhacliente)
        cpf = findViewById(R.id.edtCpf)
        VoltarConta = findViewById(R.id.txtVoltarConta)
        CadastrarConta = findViewById(R.id.btnCadastrar)
        VoltarConta.setOnClickListener{ chamaTela() }
        CadastrarConta.setOnClickListener{ cadastro() }
    }
    fun cadastro() {
        val novoEmail = email.text.toString()
        val novoCpf = cpf.text.toString()

        if (novoEmail.isEmpty() || novoCpf.isEmpty() || telefone.text.isEmpty() ||
            senha.text.isEmpty() || nome.text.isEmpty()
        ) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            // Limpe os campos se necessário
        } else {
            lifecycleScope.launch(Dispatchers.Main) {
                val existe = withContext(Dispatchers.IO) {
                    clienteJaExiste(novoEmail, novoCpf)
                }

                if (existe) {
                    Toast.makeText(
                        this@teladecadastro,
                        "Email ou CPF já cadastrado!",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    val obj = Clientes(nome.text.toString(), novoEmail, senha.text.toString(),
                        novoCpf, telefone.text.toString()
                    )
                    val novoId = withContext(Dispatchers.IO) {
                        DB.getinstance(this@teladecadastro).getMeuCrudCliente().inserir(obj)
                    }
                    proximapagina(novoId.toInt()) // Passa o novo ID gerado
                    Toast.makeText(
                        this@teladecadastro,
                        "CADASTRO DO CLIENTE FEITO COM SUCESSO!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun proximapagina(clienteId : Int) {
        val intent = Intent(this, codigocondominio::class.java)
        intent.putExtra("cliente_id", clienteId)
        startActivity(intent)
    }

    private suspend fun clienteJaExiste(email: String, cpf: String): Boolean {
        return withContext(Dispatchers.IO) {
            DB.getinstance(this@teladecadastro).getMeuCrudCliente().ler(cpf).isNotEmpty() ||
                    DB.getinstance(this@teladecadastro).getMeuCrudCliente().buscarPorEmail(email) != null
        }
    }


    private fun chamaTela() {
        val segundaTela = Intent(this, MainActivity::class.java)
        startActivity(segundaTela)
    }
}