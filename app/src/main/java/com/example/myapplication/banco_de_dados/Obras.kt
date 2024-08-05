package com.example.myapplication.bancodedados

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "obrasDoCondominio"
)
data class Obras(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id: Int = 0,

    @ColumnInfo(name = "titulo")
    @NotNull
    val titulo: String,

    @ColumnInfo(name = "descricao")
    @NotNull
    var descricao: String,

    @ColumnInfo(name = "local")
    @NotNull
    var local: String,

    @ColumnInfo(name = "sindicoid")
    @NotNull
    var sindicoID: String,

    @ColumnInfo(name = "condominioid")
    @NotNull
    var condominioid: String
) {
    constructor(
        titulo: String,
        descricao: String,
        local: String,
        sindicoID: String,
        condominioid: String
    ) : this(0, titulo, descricao, local, sindicoID, condominioid)
}

