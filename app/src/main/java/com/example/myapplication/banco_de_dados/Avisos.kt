package com.example.myapplication.bancodedados

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "avisosDoCondominio"
)
data class Avisos(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id: Int = 0,

    @ColumnInfo(name = "titulo")
    @NotNull
    val titulo: String,

    @ColumnInfo(name = "descricao")
    @NotNull
    var descricao: String,

    @ColumnInfo(name = "condominioid")
    @NotNull
    var condominioID: String,

    @ColumnInfo(name = "sindicoid")
    @NotNull
    var sindicoID: String
    ) {
    constructor(
        titulo: String,
        descricao: String,
        condominioID: String,
        sindicoID: String,
    ) : this(0, titulo, descricao, condominioID, sindicoID)
}

