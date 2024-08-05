package com.example.myapplication.bancodedados

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.banco_de_dados.servicosCliente

@Dao
interface crudServicosCliente {
    @Insert
    fun Inserir(servicosvar: servicosCliente)

    @Update
    fun Alterar(servicosvar: servicosCliente)

    @Query("delete from servicosCliente")
    fun Deletar ()
}