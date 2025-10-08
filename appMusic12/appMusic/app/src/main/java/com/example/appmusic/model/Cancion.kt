package com.example.appmusic.model


import android.content.ContentValues

data class Cancion(
    val id: Int,
    val nombre: String,
    val duracion: Int,
    val albumId: Int,
    val generoId: Int,
    val artistaId: Int
) {
    companion object {


        const val TABLE_NAME_CANCIONES = "canciones"




        const val COL_ID = "id"
        const val COL_NOMBRE = "nombre"
        const val COL_DURACION = "duracion"
        const val COL_ALBUM_ID = "albumId"
        const val COL_GENERO_ID = "generoId"
        const val COL_ARTISTA_ID = "artistaId"





        const val CREATE_TABLE_CANCIONES = (
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME_CANCIONES (" +
                        "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "$COL_NOMBRE TEXT NOT NULL," +
                        "$COL_DURACION INTEGER," +
                        "$COL_ALBUM_ID INTEGER," +
                        "$COL_GENERO_ID INTEGER," +
                        "$COL_ARTISTA_ID INTEGER" +
                        ");"
                )
    }
}




fun generarContentValuesCancion(nombre: String, duracion: Int, albumId: Int, generoId: Int, artistaId: Int): ContentValues {
    val valores = ContentValues()
    valores.put(Cancion.COL_NOMBRE, nombre)
    valores.put(Cancion.COL_DURACION, duracion)
    valores.put(Cancion.COL_ALBUM_ID, albumId)
    valores.put(Cancion.COL_GENERO_ID, generoId)
    valores.put(Cancion.COL_ARTISTA_ID, artistaId)
    return valores
}