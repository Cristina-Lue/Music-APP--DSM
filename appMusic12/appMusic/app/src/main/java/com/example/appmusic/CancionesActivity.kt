package com.example.appmusic


import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appmusic.model.CancionManager

class CancionesActivity : AppCompatActivity() {

    private lateinit var listaCanciones: ListView
    private lateinit var cancionManager: CancionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canciones)

        cancionManager = CancionManager(this)
        inicializarLista()

        // ✅ VERIFICAR SI HAY BÚSQUEDA
        val searchQuery = intent.getStringExtra("search_query")
        if (!searchQuery.isNullOrEmpty()) {
            cargarCancionesBusqueda(searchQuery)
            Toast.makeText(this, "Resultados para: $searchQuery", Toast.LENGTH_SHORT).show()
        } else {
            cargarCanciones()
        }
    }

    private fun inicializarLista() {
        listaCanciones = findViewById(R.id.listaCanciones)
    }

    private fun cargarCanciones() {
        val canciones = mutableListOf<String>()
        val cursor: Cursor? = cancionManager.obtenerTodasCanciones()

        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val nombreCancion = it.getString(it.getColumnIndexOrThrow("nombre"))
                    val duracion = it.getInt(it.getColumnIndexOrThrow("duracion"))
                    val albumNombre = it.getString(it.getColumnIndexOrThrow("album_nombre"))
                    val artistaNombre = it.getString(it.getColumnIndexOrThrow("artista_nombre"))

                    canciones.add("$nombreCancion\n${getString(R.string.duracion, duracion)} - ${getString(R.string.album, albumNombre)}\nArtista: $artistaNombre")
                } while (it.moveToNext())
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, canciones)
        listaCanciones.adapter = adapter
    }

    // ✅ NUEVO MÉTODO PARA BÚSQUEDA
    private fun cargarCancionesBusqueda(query: String) {
        val canciones = mutableListOf<String>()
        val cursor: Cursor? = cancionManager.buscarCancionesPorNombre(query)

        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val nombreCancion = it.getString(it.getColumnIndexOrThrow("nombre"))
                    val duracion = it.getInt(it.getColumnIndexOrThrow("duracion"))
                    val albumNombre = it.getString(it.getColumnIndexOrThrow("album_nombre"))
                    val artistaNombre = it.getString(it.getColumnIndexOrThrow("artista_nombre"))

                    canciones.add("$nombreCancion\n${getString(R.string.duracion, duracion)} - ${getString(R.string.album, albumNombre)}\nArtista: $artistaNombre")
                } while (it.moveToNext())
            }
        }

        if (canciones.isEmpty()) {
            canciones.add("No se encontraron resultados para: $query")
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, canciones)
        listaCanciones.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        cancionManager.cerrar()
    }
}