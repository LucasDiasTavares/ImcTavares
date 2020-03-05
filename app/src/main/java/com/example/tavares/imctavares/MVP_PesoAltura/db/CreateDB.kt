package com.example.tavares.imctavares.MVP_PesoAltura.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class CreateDB(private val context: Context) : SQLiteOpenHelper(context, DB_Name,null, VERSAO) {
    companion object {
        private val DB_Name = "imctavares.db"
        private val VERSAO = 3

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val imcT = StringBuilder()

            imcT.append(" CREATE TABLE imcT (    	 ")
            imcT.append("       id      	    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL	,")
            imcT.append("       dataPesagem     LONG    ,")
            imcT.append("       peso 		    REAL    ,")
            imcT.append("       altura 		    REAL    ,")
            imcT.append("       imc 		    REAL    );")

        db?.execSQL(imcT.toString())
        Log.d("TAG_CREATE_DB", imcT.toString())

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        try {
            db?.execSQL("DROP TABLE IF EXISTS imcT")

        } catch (ex: Exception) {
            Log.d("TAG_UPDATE_DB", " error ao deletar: " + ex.message)
        }

        onCreate(db)
    }

    fun getConexaoDatabase(): SQLiteDatabase {
        return this.writableDatabase
    }

}