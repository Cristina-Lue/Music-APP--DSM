package com.example.appmusic

import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.appmusic.model.AlbumManager
import com.example.appmusic.model.ArtistaManager
import com.example.appmusic.model.CancionManager





class BusquedaActivity : AppCompatActivity() {

    private lateinit var listaResultados: ListView
    private lateinit var tituloResultados: TextView
    private lateinit var artistaManager: ArtistaManager
    private lateinit var albumManager: AlbumManager
    private lateinit var cancionManager: CancionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busqueda)

        artistaManager = ArtistaManager(this)
        albumManager = AlbumManager(this)
        cancionManager = CancionManager(this)

        inicializarVistas()
        realizarBusqueda()
    }

    private fun inicializarVistas() {
        listaResultados = findViewById(R.id.listaResultados)
        tituloResultados = findViewById(R.id.tituloResultados)
    }








    private fun realizarBusqueda() {
        val query = intent.getStringExtra("search_query") ?: ""
        val resultados = mutableListOf<String>()

        tituloResultados.text = "Resultados para: $query"





        val cursorArtistas: Cursor? = artistaManager.buscarArtistaPorNombre(query)
        cursorArtistas?.use {
            if (it.moveToFirst()) {
                resultados.add("🎤 ARTISTAS:")
                do {
                    val nombre = it.getString(it.getColumnIndexOrThrow("nombre"))
                    val genero = it.getString(it.getColumnIndexOrThrow("genero"))
                    resultados.add("   $nombre - $genero")
                } while (it.moveToNext())
                resultados.add("")
            }
        }




        val cursorAlbumes: Cursor? = albumManager.obtenerTodosAlbumes()
        cursorAlbumes?.use {
            if (it.moveToFirst()) {
                val albumesFiltrados = mutableListOf<String>()
                do {
                    val nombreAlbum = it.getString(it.getColumnIndexOrThrow("nombre"))
                    val año = it.getInt(it.getColumnIndexOrThrow("año"))
                    val artistaNombre = it.getString(it.getColumnIndexOrThrow("artista_nombre"))

                    if (nombreAlbum.contains(query, true) || artistaNombre.contains(query, true)) {
                        albumesFiltrados.add("   $nombreAlbum ($año) - $artistaNombre")
                    }
                } while (it.moveToNext())

                if (albumesFiltrados.isNotEmpty()) {
                    resultados.add("💿 ÁLBUMES:")
                    resultados.addAll(albumesFiltrados)
                    resultados.add("")
                }
            }
        }




        val cursorCanciones: Cursor? = cancionManager.buscarCancionesPorNombre(query)
        cursorCanciones?.use {
            if (it.moveToFirst()) {
                resultados.add("🎵 CANCIONES:")
                do {
                    val nombreCancion = it.getString(it.getColumnIndexOrThrow("nombre"))
                    val duracion = it.getInt(it.getColumnIndexOrThrow("duracion"))
                    val albumNombre = it.getString(it.getColumnIndexOrThrow("album_nombre"))
                    val artistaNombre = it.getString(it.getColumnIndexOrThrow("artista_nombre"))

                    resultados.add("   $nombreCancion - $albumNombre ($artistaNombre)")
                } while (it.moveToNext())
            }
        }








        if (resultados.isEmpty()) {
            resultados.add("No se encontraron resultados para: $query")
            resultados.add("Intenta con otro término de búsqueda")
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultados)
        listaResultados.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        artistaManager.cerrar()
        albumManager.cerrar()
        cancionManager.cerrar()
    }
}