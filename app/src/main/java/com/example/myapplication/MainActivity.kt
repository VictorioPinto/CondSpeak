package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.banco_de_dados.CrudClienteCond
import com.example.myapplication.bancodedados.CRUDClientes
import com.example.myapplication.bancodedados.Clientes
import com.example.myapplication.bancodedados.DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var login: EditText
    private lateinit var senha: EditText
    private lateinit var logar: Button
    private lateinit var criaConta: TextView
    private lateinit var testar: Button
    private lateinit var clientedao: CRUDClientes
    private lateinit var clienteconddao: CrudClienteCond

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.edtLogin)
        senha = findViewById(R.id.edtCep)
        logar = findViewById(R.id.btnLogar)
        criaConta = findViewById(R.id.txtCriaConta2)
        testar = findViewById(R.id.tester)
        val db = DB.getinstance(this)

        // Obtém a instância do DAO CRUDClientes através do banco de dados
        clientedao = db.getMeuCrudCliente()
        clienteconddao = db.getMeuCrudClientteCond()

        criaConta.setOnClickListener { chamaTelaCadastro() }
        logar.setOnClickListener { validaLogin() }
        testar.setOnClickListener { chamaTelaTeste() }
    }

    private fun chamaTelaTeste() {
        startActivity(Intent(this, telainicial::class.java))
    }

    private fun chamaTelaCadastro() {
        startActivity(Intent(this, teladecadastro::class.java))
    }

    private fun validaLogin() {
        val email = login.text.toString()
        val senhaInformada = senha.text.toString()

        if (email.isEmpty() || senhaInformada.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val quantidadeUsuariosComEmail = withContext(Dispatchers.IO) {
                clientedao.contarUsuariosComEmail(email)
            }

            when (quantidadeUsuariosComEmail) {
                1 -> {
                    val cliente = conseguirsenha(email)
                    if (cliente != null && cliente.senha == senhaInformada) {
                        verificarVinculoCondominio(cliente.id)
                    } else {
                        Toast.makeText(this@MainActivity, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
                    }
                }
                0 -> {
                    Toast.makeText(this@MainActivity, "Usuário não cadastrado", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this@MainActivity, "Erro ao validar login", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun verificarVinculoCondominio(clienteId: Int) {
        val estaEmCondominio = withContext(Dispatchers.IO) {
            clienteconddao.existeClienteCondominio(clienteId)
        }

        if (estaEmCondominio) {
            navegarParaTelaInicial(clienteId)
        } else {
            navegarParaVincularCondominio(clienteId)
        }

        Toast.makeText(this@MainActivity, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
    }

    private suspend fun conseguirsenha(email: String): Clientes? {
        return withContext(Dispatchers.IO) {
            clientedao.getsenhaByemail(email)
        }
    }

    private fun navegarParaTelaInicial(clienteId: Int) {
        val intent = Intent(this, telainicial::class.java)
        intent.putExtra("cliente_id", clienteId)
        startActivity(intent)
    }

    private fun navegarParaVincularCondominio(clienteId: Int) {
        val intent = Intent(this, codigocondominio::class.java)
        intent.putExtra("cliente_id", clienteId)
        startActivity(intent)
    }


}