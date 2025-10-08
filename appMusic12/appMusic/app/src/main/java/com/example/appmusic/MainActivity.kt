package com.example.appmusic


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.appmusic.db.DatabaseHelper

class MainActivity : AppCompatActivity() {

    private lateinit var botonArtistas: Button
    private lateinit var botonAlbumes: Button
    private lateinit var botonCanciones: Button
    private lateinit var editTextBuscar: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ✅ FORZAR CREACIÓN DE LA BASE DE DATOS
        val dbHelper = DatabaseHelper(this)
        val database = dbHelper.writableDatabase
        database.close()

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        botonArtistas = findViewById(R.id.botonArtistas)
        botonAlbumes = findViewById(R.id.botonAlbumes)
        botonCanciones = findViewById(R.id.botonCanciones)
        editTextBuscar = findViewById(R.id.editTextBuscar)
    }

    private fun setupClickListeners() {
        botonArtistas.setOnClickListener {
            val intent = Intent(this, ArtistasActivity::class.java)
            startActivity(intent)
        }

        botonAlbumes.setOnClickListener {
            val intent = Intent(this, AlbumesActivity::class.java)
            startActivity(intent)
        }

        botonCanciones.setOnClickListener {
            val intent = Intent(this, CancionesActivity::class.java)
            startActivity(intent)
        }


        editTextBuscar.setOnEditorActionListener { _, _, _ ->
            performSearch()
            true
        }
    }

    private fun performSearch() {
        val query = editTextBuscar.text.toString().trim()
        if (query.isNotEmpty()) {

            val intent = Intent(this, BusquedaActivity::class.java)
            intent.putExtra("search_query", query)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Escribe algo para buscar", Toast.LENGTH_SHORT).show()
        }
    }
}