package com.example.appmusic

import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.appmusic.model.ArtistaManager



class ArtistasActivity : AppCompatActivity() {

    private lateinit var listaArtistas: ListView
    private lateinit var artistaManager: ArtistaManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artistas)

        artistaManager = ArtistaManager(this)
        inicializarLista()
        cargarArtistas()
    }

    private fun inicializarLista() {
        listaArtistas = findViewById(R.id.listaArtistas)
    }

    private fun cargarArtistas() {
        val artistas = mutableListOf<String>()
        val cursor: Cursor? = artistaManager.obtenerTodosArtistas()

        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val nombre = it.getString(it.getColumnIndexOrThrow("nombre"))
                    val genero = it.getString(it.getColumnIndexOrThrow("genero"))
                    artistas.add("$nombre\n${getString(R.string.genero, genero)}")
                } while (it.moveToNext())
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, artistas)
        listaArtistas.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        artistaManager.cerrar()
    }
}