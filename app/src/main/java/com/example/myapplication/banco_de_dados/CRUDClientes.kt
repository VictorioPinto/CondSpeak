package com.example.myapplication.bancodedados

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface CRUDClientes {
    @Insert
    suspend fun inserir(objClientes: Clientes): Long

    @Update
    suspend fun alterar(objClientes: Clientes)

    @Query("SELECT * FROM Clientes WHERE cpf =(:cpfC)")
    fun ler(cpfC: String): List<Clientes>
    @Query("SELECT * FROM Clientes WHERE email = :email")
    suspend fun buscarPorEmail(email: String): Clientes?



    @Query("SELECT * FROM Clientes WHERE id = :id")
    suspend fun getClienteById(id: Int): List<Clientes>



    @Query("SELECT * FROM Clientes WHERE id = :id")
    suspend fun getClienteByIdAuxTabela(id: Int): List<Clientes>

    @Query("SELECT COUNT(*) FROM Clientes WHERE Email = :email")
    fun contarUsuariosComEmail(email: String): Int
    @Query("SELECT * FROM Clientes WHERE Email = :email")
    suspend fun getsenhaByemail (email: String): Clientes

    @Update
    suspend fun updateCliente(cliente: Clientes)

    @Delete
    suspend fun deletar(objClientes: Clientes)
}

data class ClienteComNomeCondominio(
    val Nome: String,
    val NomeCondominio: String
)