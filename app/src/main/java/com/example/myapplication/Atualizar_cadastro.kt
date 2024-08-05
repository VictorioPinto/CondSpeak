package com.example.myapplication

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
import com.example.myapplication.bancodedados.Clientes
import com.example.myapplication.bancodedados.DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class atualizarcadastro : AppCompatActivity() {
    lateinit var nome : EditText
    lateinit var email : EditText
    lateinit var senha : EditText
    lateinit var telefone : EditText
    lateinit var cpf : EditText
    lateinit var VoltarConta : TextView
    lateinit var AtualizarConta : Button
    private var iddocliente: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_atualizar_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setContentView(R.layout.activity_teladecadastro)
        telefone = findViewById(R.id.edttelefone)
        nome = findViewById(R.id.edtNome)
        email = findViewById(R.id.edtEmail)
        senha = findViewById(R.id.edtSenhacliente)
        cpf = findViewById(R.id.edtCpf)
        VoltarConta = findViewById(R.id.txtVoltarConta)
        AtualizarConta = findViewById(R.id.btnAtualizar)
        iddocliente = intent.getStringExtra("cliente_id")
        AtualizarConta.setOnClickListener { salvar() } // Correção: atualizarConta em camelCase
        preencherCamposComDadosDoCliente()
    }

    private fun preencherCamposComDadosDoCliente() {
        val idClienteInt = iddocliente?.toIntOrNull() ?: return

        lifecycleScope.launch { // Usar lifecycleScope para evitar vazamentos de memória
            try {
                val cliente = withContext(Dispatchers.IO) {
                    DB.getinstance(this@atualizarcadastro)
                        .getMeuCrudCliente()
                        .getClienteById(idClienteInt)
                        .firstOrNull()
                }

                withContext(Dispatchers.Main) {
                    cliente?.let {
                        nome.setText(it.nome)
                        email.setText(it.email)
                        senha.setText(it.senha)
                        telefone.setText(it.telefone)
                        cpf.setText(it.cpf)
                    } ?: run {
                        // Lidar com o caso em que o cliente não é encontrado
                        Toast.makeText(this@atualizarcadastro, "Cliente não encontrado", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                // Lidar com erros ao buscar o cliente
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@atualizarcadastro, "Erro ao buscar o cliente", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun salvar() {
        val nome = nome.text.toString()
        val email = email.text.toString()
        val senha = senha.text.toString()
        val telefone = telefone.text.toString()
        val cpf = cpf.text.toString()

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || telefone.isEmpty() || cpf.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val idClienteInt = iddocliente?.toIntOrNull() ?: 0

        val clienteAtualizado = Clientes(
            nome = nome,
            email = email,
            senha = senha,
            cpf = cpf,
            telefone = telefone
        )

        lifecycleScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    DB.getinstance(this@atualizarcadastro).getMeuCrudCliente().updateCliente(clienteAtualizado)
                }
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@atualizarcadastro, "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                    // Aqui você pode adicionar código para finalizar a Activity ou navegar para outra tela, se necessário
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@atualizarcadastro, "Erro ao atualizar o cliente", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}