package com.example.appmusic.model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.appmusic.db.DatabaseHelper

class CancionManager(context: Context) {
    private var helper: DatabaseHelper? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = DatabaseHelper(context)
        db = helper!!.writableDatabase
    }




    fun obtenerTodasCanciones(): Cursor? {
        val query = """
            SELECT c.${Cancion.COL_ID}, 
                   c.${Cancion.COL_NOMBRE}, 
                   c.${Cancion.COL_DURACION}, 
                   al.${Album.COL_NOMBRE} as album_nombre,
                   ar.${Artista.COL_NOMBRE} as artista_nombre
            FROM ${Cancion.TABLE_NAME_CANCIONES} c 
            JOIN ${Album.TABLE_NAME_ALBUMES} al ON c.${Cancion.COL_ALBUM_ID} = al.${Album.COL_ID}
            JOIN ${Artista.TABLE_NAME_ARTISTAS} ar ON c.${Cancion.COL_ARTISTA_ID} = ar.${Artista.COL_ID}
            ORDER BY c.${Cancion.COL_NOMBRE} ASC
        """.trimIndent()

        return db?.rawQuery(query, null)
    }




    fun buscarCancionesPorAlbum(albumId: Int): Cursor? {
        val columns = arrayOf(
            Cancion.COL_ID,
            Cancion.COL_NOMBRE,
            Cancion.COL_DURACION,
            Cancion.COL_ALBUM_ID
        )
        return db?.query(
            Cancion.TABLE_NAME_CANCIONES,
            columns,
            "${Cancion.COL_ALBUM_ID}=?",
            arrayOf(albumId.toString()),
            null,
            null,
            "${Cancion.COL_NOMBRE} ASC"
        )
    }



    fun buscarCancionesPorNombre(nombre: String): Cursor? {
        val query = """
            SELECT c.${Cancion.COL_ID}, 
                   c.${Cancion.COL_NOMBRE}, 
                   c.${Cancion.COL_DURACION}, 
                   al.${Album.COL_NOMBRE} as album_nombre,
                   ar.${Artista.COL_NOMBRE} as artista_nombre
            FROM ${Cancion.TABLE_NAME_CANCIONES} c 
            JOIN ${Album.TABLE_NAME_ALBUMES} al ON c.${Cancion.COL_ALBUM_ID} = al.${Album.COL_ID}
            JOIN ${Artista.TABLE_NAME_ARTISTAS} ar ON c.${Cancion.COL_ARTISTA_ID} = ar.${Artista.COL_ID}
            WHERE c.${Cancion.COL_NOMBRE} LIKE ?
            ORDER BY c.${Cancion.COL_NOMBRE} ASC
        """.trimIndent()

        return db?.rawQuery(query, arrayOf("%$nombre%"))
    }




    fun cerrar() {
        db?.close()
    }
}