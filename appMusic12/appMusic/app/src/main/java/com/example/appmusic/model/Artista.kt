package com.example.appmusic.model


import android.content.ContentValues

data class Artista(
    val id: Int,
    val nombre: String,
    val genero: String
) {
    companion object {


        const val TABLE_NAME_ARTISTAS = "artistas"



        const val COL_ID = "id"
        const val COL_NOMBRE = "nombre"
        const val COL_GENERO = "genero"

        // Sentencia SQL para crear la tabla
        const val CREATE_TABLE_ARTISTAS = (
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME_ARTISTAS (" +
                        "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "$COL_NOMBRE TEXT NOT NULL," +
                        "$COL_GENERO TEXT" +
                        ");"
                )
    }
}


fun generarContentValuesArtista(nombre: String, genero: String): ContentValues {
    val valores = ContentValues()
    valores.put(Artista.COL_NOMBRE, nombre)
    valores.put(Artista.COL_GENERO, genero)
    return valores
}