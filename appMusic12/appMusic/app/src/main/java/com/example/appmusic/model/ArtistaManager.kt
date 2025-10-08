package com.example.appmusic.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.appmusic.db.DatabaseHelper

class ArtistaManager(context: Context) {
    private var helper: DatabaseHelper? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = DatabaseHelper(context)
        db = helper!!.writableDatabase
    }




    fun obtenerTodosArtistas(): Cursor? {
        val columns = arrayOf(Artista.COL_ID, Artista.COL_NOMBRE, Artista.COL_GENERO)
        return db?.query(
            Artista.TABLE_NAME_ARTISTAS,
            columns,
            null,
            null,
            null,
            null,
            "${Artista.COL_NOMBRE} ASC"
        )
    }




    fun buscarArtistaPorId(id: Int): Cursor? {
        val columns = arrayOf(Artista.COL_ID, Artista.COL_NOMBRE, Artista.COL_GENERO)
        return db?.query(
            Artista.TABLE_NAME_ARTISTAS,
            columns,
            "${Artista.COL_ID}=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
    }


    fun buscarArtistaPorNombre(nombre: String): Cursor? {
        val columns = arrayOf(Artista.COL_ID, Artista.COL_NOMBRE, Artista.COL_GENERO)
        return db?.query(
            Artista.TABLE_NAME_ARTISTAS,
            columns,
            "${Artista.COL_NOMBRE} LIKE ?",
            arrayOf("%$nombre%"),
            null,
            null,
            null
        )
    }



    fun cerrar() {
        db?.close()
    }
}