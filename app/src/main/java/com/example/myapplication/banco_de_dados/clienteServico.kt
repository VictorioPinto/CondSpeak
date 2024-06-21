package com.example.myapplication.banco_de_dados


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity (tableName = "servicosCliente")
data class servicosCliente(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id: Int = 0,

    @ColumnInfo(name = "Nome")
    @NotNull
    val nome: String,

    @ColumnInfo(name = "Condominio")
    @NotNull
    var cond: String,

    @ColumnInfo(name = "ClienteNome")
    @NotNull
    var cliente: String,

    @ColumnInfo(name = "SituacaoDoAgendamento")
    @NotNull
    var SituacaoDoAgendamento: String
) {
    constructor(
        nome: String,
        cond: String,
        cliente: String,
        SituacaoDoAgendamento:String
    ) : this(0, nome, cond,cliente, SituacaoDoAgendamento)
}