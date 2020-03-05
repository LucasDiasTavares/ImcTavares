package com.example.tavares.imctavares.MVP_PesoAltura.repositorios

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.tavares.imctavares.MVP_PesoAltura.data.ImcT
import com.example.tavares.imctavares.MVP_PesoAltura.db.CreateDB
import java.util.*

class Repo_imcT(private val context: Context) {

    private var db_conection: CreateDB? = null

    init {
        db_conection = CreateDB(context)
    }

    fun createAtImcT(imcT: ImcT): Int? {
        val values = ContentValues()

        values.put("peso", imcT.peso)
        values.put("altura", imcT.altura)
        values.put("dataPesagem", imcT.dataPesagem?.time)
        values.put("imc", imcT.imc)
        val retorno_insert = db_conection?.getConexaoDatabase()?.insert(
                "imcT", null, values)

        db_conection?.close()
        return retorno_insert?.toInt()
    }


    fun getAllImcT(): ArrayList<ImcT> {
        val cursor = db_conection?.getConexaoDatabase()?.rawQuery("SELECT * FROM imcT ORDER BY id ASC", null)
        cursor?.moveToFirst()
        val list = ArrayList<ImcT>()

        if (cursor?.count != 0) {
            db_conection?.getConexaoDatabase()?.close()
            list.add(gerarObjImcT(cursor!!))
            try {
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        list.add(gerarObjImcT(cursor))
                    }
                }
            } catch (ex: Exception) {
                Log.e("TAG_GET", ex.message)
            } finally {
                cursor?.close()
            }
        } else {
            db_conection?.getConexaoDatabase()?.close()
        }

        return list
    }


    fun getLastImct() : ImcT?{
        return getAllImcT().last()
    }


    //Exemplo get a single item
    fun getImcT(id: Int): ImcT? {
        val cursor = db_conection?.getConexaoDatabase()?.rawQuery("SELECT * FROM imcT WHERE id = $id ORDER BY ASC", null)
        cursor?.moveToLast()

        return try {
            if (cursor?.count != 0) {
                gerarObjImcT(cursor!!)
            } else null
        } finally {
            cursor?.close()
            db_conection?.getConexaoDatabase()?.close()
        }

    }


    fun deleteImcT(id: Int) {
        db_conection?.getConexaoDatabase()?.delete("imcT", "id=$id", null)
        db_conection?.getConexaoDatabase()?.close()
    }


    //Exemplo update
    fun updateImct(id: Int, imc: Float) {
        val values = ContentValues()

        values.put("imc", imc)

        db_conection?.getConexaoDatabase()?.update("imcT", values, "id = $id", null)
        db_conection?.getConexaoDatabase()?.close()
    }


    private fun gerarObjImcT(cursor: Cursor): ImcT {
        val at = ImcT()
        at.id = cursor.getInt(cursor.getColumnIndex("id"))
        at.altura = cursor.getFloat(cursor.getColumnIndex("altura"))
        at.peso = cursor.getFloat(cursor.getColumnIndex("peso"))
        at.imc = cursor.getFloat(cursor.getColumnIndex("imc"))
        val date_pesagem = cursor.getString(cursor.getColumnIndex("dataPesagem"))
        if (date_pesagem != null && date_pesagem != "null") {
            at.dataPesagem = Date(date_pesagem.toLong())
        }

        return at
    }


    fun get2(): Boolean {
        val cursor = db_conection?.getConexaoDatabase()?.rawQuery(
                "SELECT * FROM imcT ORDER BY id ASC", null)
        cursor?.moveToFirst()
        db_conection?.getConexaoDatabase()?.close()
        return cursor?.count != 0
    }

}