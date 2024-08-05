package com.example.myapplication.bancodedados

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "Clientes"
)
data class Clientes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id: Int = 0,

    @ColumnInfo(name = "Nome")
    @NotNull
    val nome: String,

    @ColumnInfo(name = "Email")
    @NotNull
    var email: String,

    @ColumnInfo(name = "Senha")
    @NotNull
    var senha: String,

    @ColumnInfo(name = "Cpf")
    @NotNull
    val cpf: String,

    @ColumnInfo(name = "Telefone")
    @NotNull
    var telefone: String,

) {
    constructor(
        nome: String,
        email: String,
        senha: String,
        cpf: String,
        telefone: String,
    ) : this(0, nome, email, senha, cpf, telefone)
}

