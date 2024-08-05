package com.example.myapplication.bancodedados

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CRUDobras {
    @Insert
    suspend fun Inserir(objObras: Obras)

    @Update
    fun Alterar(objObras: Obras)

    @Query("SELECT * FROM obrasDoCondominio WHERE sindicoid = :codigo")
    suspend fun getObrasBySindicoId(codigo: Int): List<Obras>

    @Delete
    fun Deletar (objObras: Obras)
}