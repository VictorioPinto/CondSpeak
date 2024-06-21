package com.example.myapplication.banco_de_dados

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.bancodedados.Condominios
@Dao
interface CrudClienteCond {
    @Insert
    fun Inserir(objClienteCondo: ClienteCondo)
    @Update
    fun Alterar(objClienteCondo: ClienteCondo)
    @Query("SELECT * FROM ClienteCondo WHERE Clienteid =(:clientei)")
    fun ler(clientei: String) : List<ClienteCondo>

    @Query("SELECT EXISTS(SELECT 1 FROM ClienteCondo WHERE Clienteid = :clienteId)")
    suspend fun existeClienteCondominio(clienteId: Int): Boolean

    @Query("SELECT * FROM ClienteCondo WHERE id =:clienteId3")
    suspend fun GetComdominioByClienteId(clienteId3: Int) : List<ClienteCondo>

    @Delete
    fun Deletar (objClienteCondo: ClienteCondo)

}