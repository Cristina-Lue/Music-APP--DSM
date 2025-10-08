package com.example.appmusic.model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.appmusic.db.DatabaseHelper


class AlbumManager(context: Context) {
    private var helper: DatabaseHelper? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = DatabaseHelper(context)
        db = helper!!.writableDatabase
    }



    fun obtenerTodosAlbumes(): Cursor? {
        val query = """
            SELECT a.${Album.COL_ID}, 
                   a.${Album.COL_NOMBRE}, 
                   a.${Album.COL_AÑO}, 
                   ar.${Artista.COL_NOMBRE} as artista_nombre
            FROM ${Album.TABLE_NAME_ALBUMES} a 
            JOIN ${Artista.TABLE_NAME_ARTISTAS} ar ON a.${Album.COL_ARTISTA_ID} = ar.${Artista.COL_ID}
            ORDER BY a.${Album.COL_NOMBRE} ASC
        """.trimIndent()

        return db?.rawQuery(query, null)
    }




    fun buscarAlbumesPorArtista(artistaId: Int): Cursor? {
        val columns = arrayOf(Album.COL_ID, Album.COL_NOMBRE, Album.COL_AÑO, Album.COL_ARTISTA_ID)
        return db?.query(
            Album.TABLE_NAME_ALBUMES,
            columns,
            "${Album.COL_ARTISTA_ID}=?",
            arrayOf(artistaId.toString()),
            null,
            null,
            "${Album.COL_NOMBRE} ASC"
        )
    }


    fun cerrar() {
        db?.close()
    }
}