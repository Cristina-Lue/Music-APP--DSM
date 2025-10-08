package com.example.appmusic

import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.appmusic.model.AlbumManager


class AlbumesActivity : AppCompatActivity() {

    private lateinit var listaAlbumes: ListView
    private lateinit var albumManager: AlbumManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albumes)

        albumManager = AlbumManager(this)
        inicializarLista()
        cargarAlbumes()
    }

    private fun inicializarLista() {
        listaAlbumes = findViewById(R.id.listaAlbumes)
    }

    private fun cargarAlbumes() {
        val albumes = mutableListOf<String>()
        val cursor: Cursor? = albumManager.obtenerTodosAlbumes()

        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val nombreAlbum = it.getString(it.getColumnIndexOrThrow("nombre"))
                    val año = it.getInt(it.getColumnIndexOrThrow("año"))
                    val artistaNombre = it.getString(it.getColumnIndexOrThrow("artista_nombre"))

                    albumes.add("$nombreAlbum\n${getString(R.string.año, año)} - ${getString(R.string.artista, artistaNombre)}")
                } while (it.moveToNext())
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albumes)
        listaAlbumes.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        albumManager.cerrar()
    }
}