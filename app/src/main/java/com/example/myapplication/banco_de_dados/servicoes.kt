package com.example.myapplication.banco_de_dados

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity (tableName = "servicos")
data class servicosClass(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id: Int = 0,


    @ColumnInfo(name = "Nome")
    @NotNull
    val nome: String,


    @ColumnInfo(name = "Descricao")
    @NotNull
    var desc: String,


    @ColumnInfo(name = "Condominio")
    @NotNull
    var cond: String
) {
    constructor(
        nome: String,
        desc: String,
        cond: String,
    ) : this(0, nome, desc, cond)
}