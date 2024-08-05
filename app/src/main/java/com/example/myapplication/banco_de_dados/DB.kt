package com.example.myapplication.bancodedados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Servicos
import com.example.myapplication.banco_de_dados.ClienteCondo
import com.example.myapplication.banco_de_dados.CrudClienteCond
import com.example.myapplication.banco_de_dados.servicosClass
import com.example.myapplication.banco_de_dados.servicosCliente
import com.example.myapplication.banco_de_dados.CRUDavisos

@Database(entities = [Avisos::class,Clientes::class, Condominios::class, ClienteCondo::class, servicosClass::class, servicosCliente::class , Obras::class], exportSchema = false, version = 3)
abstract class DB : RoomDatabase() {
    abstract fun getMeuCrudCliente(): CRUDClientes
    abstract fun getMeuCrudCodominio(): CRUDCondominios
    abstract fun getMeuCrudClientteCond(): CrudClienteCond
    abstract fun getMeuCrudServicos(): crudServicos
    abstract fun getMeuCrudServicosCliente(): crudServicosCliente
    abstract fun getMeuCrudAvisos(): CRUDavisos
    abstract fun getMeuCrudObras(): CRUDobras


    companion object {
        var INSTANCE: DB? = null
            fun getinstance(context: Context): DB {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DB::class.java,
                        name = "dbcondominio.db"
                    ).fallbackToDestructiveMigration()

                        .build()
                }
                return INSTANCE!!
            }

    }
}