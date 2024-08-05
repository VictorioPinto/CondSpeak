package com.example.myapplication.banco_de_dados

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "ClienteCondo")
data class ClienteCondo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id: Int = 0,
    @ColumnInfo(name = "Clienteid")
    @NotNull
    val Clienteid: Int,

    @ColumnInfo(name = "Condoid")
    val Condoid: Int?,

    @ColumnInfo(name = "Role")
    @NotNull
    val Role: String,
){
    constructor(
        Clienteid: Int,
        Condoid: Int,
        Role: String
    ) : this(0,Clienteid,Condoid,Role)
}
