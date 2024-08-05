package com.example.myapplication.banco_de_dados

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.bancodedados.Avisos

@Dao
interface CRUDavisos {
    @Insert
    fun Inserir(objClienteCondo: Avisos)

    @Update
    fun Alterar(objClienteCondo: Avisos)

    @Query("SELECT * FROM avisosDoCondominio")
    fun ler() : List<Avisos>


    @Query("SELECT * FROM avisosDoCondominio WHERE condominioid = :condID")
    suspend fun getByCondID(condID: Int): List<Avisos>

    @Query("SELECT * FROM avisosDoCondominio WHERE sindicoid = :sindID")
    suspend fun getBySindicoID(sindID: Int): List<Avisos>

}