package com.example.myapplication.bancodedados

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.banco_de_dados.ClienteCondo
import com.example.myapplication.banco_de_dados.servicosClass

@Dao
interface crudServicos {
    @Insert
    fun Inserir(servicosvar: servicosClass)

    @Update
    fun Alterar(servicosvar: servicosClass)


    @Query("SELECT * FROM servicos")
    fun ler() : List<servicosClass>

    @Query("SELECT * FROM servicos WHERE Condominio = :Condominio")
    fun getByCond(Condominio:String) : List<servicosClass>


    @Query("delete from servicos")
    fun Deletar()
}