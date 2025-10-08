package com.example.appmusic.model


import android.content.ContentValues

data class Album(
    val id: Int,
    val nombre: String,
    val año: Int,
    val artistaId: Int
) {
    companion object {


        const val TABLE_NAME_ALBUMES = "albumes"


        const val COL_ID = "id"
        const val COL_NOMBRE = "nombre"
        const val COL_AÑO = "año"
        const val COL_ARTISTA_ID = "artistaId"




        // Sentencia SQL para crear la tabla
        const val CREATE_TABLE_ALBUMES = (
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME_ALBUMES (" +
                        "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "$COL_NOMBRE TEXT NOT NULL," +
                        "$COL_AÑO INTEGER," +
                        "$COL_ARTISTA_ID INTEGER" +
                        ");"
                )
    }
}




fun generarContentValuesAlbum(nombre: String, año: Int, artistaId: Int): ContentValues {
    val valores = ContentValues()
    valores.put(Album.COL_NOMBRE, nombre)
    valores.put(Album.COL_AÑO, año)
    valores.put(Album.COL_ARTISTA_ID, artistaId)
    return valores
}