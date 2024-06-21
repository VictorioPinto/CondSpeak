package com.example.myapplication.bancodedados

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity (tableName = "Condominios")
data class Condominios(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id: Int = 0,
    @ColumnInfo(name = "Nome")
    @NotNull
    val nome: String,
    @ColumnInfo(name = "Email")
    @NotNull
    var email: String,
    @ColumnInfo(name = "Cnpj")
    @NotNull
    val cnpj: String,
    @ColumnInfo(name = "Numero")
    @NotNull
    var numero: String,
    @ColumnInfo(name = "cep")
    @NotNull
    var cep: String,
    @ColumnInfo(name = "Code")
    @NotNull
    var code: String,
) {
    constructor(
        nome: String,
        email: String,
        cnpj: String,
        numero: String,
        cep: String,
        code: String
    ) : this(0, nome, email, cnpj, numero, cep, code)
}