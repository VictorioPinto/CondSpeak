package com.example.myapplication.bancodedados

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CRUDCondominios {
    @Insert
    suspend fun Inserir(objCondominios: Condominios): Long
    @Update
    fun Alterar(objCondominios: Condominios)
    @Query("SELECT * FROM Condominios WHERE cnpj =(:cnpjC)")
    fun ler(cnpjC: String) : List<Condominios>
    @Query("SELECT Id FROM Condominios WHERE Code = :codigo")
    suspend fun getCondominioIdByCode(codigo: String): Int?
    @Query("SELECT * FROM Condominios WHERE Code = :codigo")
    suspend fun getCondominioByCode(codigo: String): Condominios?



    @Query("SELECT * FROM Condominios WHERE id = :codigo")
    suspend fun getCondominioById(codigo: Int): List<Condominios>


    @Delete
    fun Deletar (objCondominios: Condominios)
}