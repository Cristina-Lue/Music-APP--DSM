package com.example.appmusic.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appmusic.model.Album
import com.example.appmusic.model.Artista
import com.example.appmusic.model.Cancion

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MusicCatalog.sqlite"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {




        db.execSQL(Artista.CREATE_TABLE_ARTISTAS)
        db.execSQL(Album.CREATE_TABLE_ALBUMES)
        db.execSQL(Cancion.CREATE_TABLE_CANCIONES)


        insertarDatosEjemplo(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${Cancion.TABLE_NAME_CANCIONES}")
        db.execSQL("DROP TABLE IF EXISTS ${Album.TABLE_NAME_ALBUMES}")
        db.execSQL("DROP TABLE IF EXISTS ${Artista.TABLE_NAME_ARTISTAS}")
        onCreate(db)
    }

    private fun insertarDatosEjemplo(db: SQLiteDatabase) {




        val artistaIds = mutableListOf<Long>()





        val artistas = listOf(
            Pair("Ariana Grande", "Pop"),
            Pair("Nicki Minaj", "Hip Hop"),
            Pair("Twenty One Pilots", "Rock Alternativo"),
            Pair("Lana Del Rey", "Pop Alternativo"),
            Pair("Ozuna", "Reggaeton"),
            Pair("J Balvin", "Reggaeton"),
            Pair("Justin Bieber", "Pop"),
            Pair("The Weeknd", "R&B"),
            Pair("Karol G", "Reggaeton"),
            Pair("Eminem", "Hip Hop"),
            Pair("KATSEYE", "K-Pop"),
            Pair("Sia", "Pop")
        )



        artistas.forEach { (nombre, genero) ->
            val valores = ContentValues().apply {
                put(Artista.COL_NOMBRE, nombre)
                put(Artista.COL_GENERO, genero)
            }
            val id = db.insert(Artista.TABLE_NAME_ARTISTAS, null, valores)
            artistaIds.add(id)
        }





        val albumes = listOf(
            // Ariana Grande (artistaIds[0])
            Triple("My Everything", 2014, artistaIds[0]),
            Triple("Thank U, Next", 2019, artistaIds[0]),
            Triple("Dangerous Woman", 2016, artistaIds[0]),
            Triple("Positions", 2020, artistaIds[0]),



            // Nicki Minaj (artistaIds[1])
            Triple("The Pinkprint", 2014, artistaIds[1]),
            Triple("Queen", 2018, artistaIds[1]),
            Triple("Pink Friday", 2010, artistaIds[1]),

            // Twenty One Pilots (artistaIds[2])
            Triple("Blurryface", 2015, artistaIds[2]),
            Triple("Clancy", 2024, artistaIds[2]),
            Triple("Trench", 2018, artistaIds[2]),

            // Lana Del Rey (artistaIds[3])
            Triple("Did you know that theres a tunnel under Ocean Blvd", 2023, artistaIds[3]),
            Triple("Lust for Life", 2017, artistaIds[3]),
            Triple("Norman Fucking Rockwell", 2019, artistaIds[3]),
            Triple("Ultraviolence", 2014, artistaIds[3]),

            // Ozuna (artistaIds[4])
            Triple("Aura", 2018, artistaIds[4]),
            Triple("Odisea", 2017, artistaIds[4]),
            Triple("Enoc", 2020, artistaIds[4]),

            // J Balvin (artistaIds[5])
            Triple("Energía", 2016, artistaIds[5]),
            Triple("Colores", 2020, artistaIds[5]),
            Triple("Jose", 2021, artistaIds[5]),

            // Justin Bieber (artistaIds[6])
            Triple("Purpose", 2015, artistaIds[6]),
            Triple("Justice", 2021, artistaIds[6]),
            Triple("Swag 2", 2010, artistaIds[6]),

            // The Weeknd (artistaIds[7])
            Triple("Starboy", 2016, artistaIds[7]),
            Triple("Dawn FM", 2022, artistaIds[7]),
            Triple("Beauty Behind the Madness", 2015, artistaIds[7]),

            // Karol G (artistaIds[8])
            Triple("Mañana Será Bonito", 2023, artistaIds[8]),

            // Eminem (artistaIds[9])
            Triple("The Eminem Show", 2002, artistaIds[9]),
            Triple("The Slim Shady LP", 1999, artistaIds[9]),
            Triple("Recovery", 2010, artistaIds[9]),

            // KATSEYE (artistaIds[10])
            Triple("Beautiful Chaos", 2024, artistaIds[10]),


            Triple("This Is Acting", 2016, artistaIds[11]),
            Triple("1000 Forms of Fear", 2014, artistaIds[11]),
            Triple("Everyday Is Christmas", 2017, artistaIds[11])
        )




        val albumIds = mutableListOf<Long>()
        albumes.forEach { (nombre, año, artistaId) ->
            val valores = ContentValues().apply {
                put(Album.COL_NOMBRE, nombre)
                put(Album.COL_AÑO, año)
                put(Album.COL_ARTISTA_ID, artistaId)
            }
            val id = db.insert(Album.TABLE_NAME_ALBUMES, null, valores)
            albumIds.add(id)
        }





        val canciones = listOf(
            // Ariana Grande - My Everything (albumIds[0])
            listOf("Problem", 213, albumIds[0], 1, artistaIds[0]),
            listOf("Break Free", 218, albumIds[0], 1, artistaIds[0]),
            listOf("Love Me Harder", 236, albumIds[0], 1, artistaIds[0]),

            // Ariana Grande - Thank U, Next (albumIds[1])
            listOf("Thank U, Next", 207, albumIds[1], 1, artistaIds[0]),
            listOf("7 Rings", 179, albumIds[1], 1, artistaIds[0]),
            listOf("Break Up With Your Girlfriend", 191, albumIds[1], 1, artistaIds[0]),

            // Ariana Grande - Dangerous Woman (albumIds[2])
            listOf("Dangerous Woman", 236, albumIds[2], 1, artistaIds[0]),
            listOf("Side To Side", 226, albumIds[2], 1, artistaIds[0]),
            listOf("Into You", 245, albumIds[2], 1, artistaIds[0]),

            // Ariana Grande - Positions (albumIds[3])
            listOf("Positions", 173, albumIds[3], 1, artistaIds[0]),
            listOf("34+35", 173, albumIds[3], 1, artistaIds[0]),
            listOf("POV", 217, albumIds[3], 1, artistaIds[0]),

            // Nicki Minaj - The Pinkprint (albumIds[4])
            listOf("Anaconda", 260, albumIds[4], 2, artistaIds[1]),
            listOf("Only", 323, albumIds[4], 2, artistaIds[1]),
            listOf("The Night Is Still Young", 219, albumIds[4], 2, artistaIds[1]),

            // Nicki Minaj - Queen (albumIds[5])
            listOf("Chun-Li", 227, albumIds[5], 2, artistaIds[1]),
            listOf("Barbie Dreams", 279, albumIds[5], 2, artistaIds[1]),
            listOf("Good Form", 193, albumIds[5], 2, artistaIds[1]),

            // Nicki Minaj - Pink Friday (albumIds[6])
            listOf("Super Bass", 200, albumIds[6], 2, artistaIds[1]),
            listOf("Moment 4 Life", 266, albumIds[6], 2, artistaIds[1]),
            listOf("Check It Out", 229, albumIds[6], 2, artistaIds[1]),

            // Twenty One Pilots - Blurryface (albumIds[7])
            listOf("Stressed Out", 202, albumIds[7], 3, artistaIds[2]),
            listOf("Ride", 226, albumIds[7], 3, artistaIds[2]),
            listOf("Tear In My Heart", 193, albumIds[7], 3, artistaIds[2]),

            // Twenty One Pilots - Clancy (albumIds[8])
            listOf("Overcompensate", 245, albumIds[8], 3, artistaIds[2]),
            listOf("Next Semester", 218, albumIds[8], 3, artistaIds[2]),
            listOf("Backslide", 222, albumIds[8], 3, artistaIds[2]),

            // Twenty One Pilots - Trench (albumIds[9])
            listOf("My Blood", 218, albumIds[9], 3, artistaIds[2]),
            listOf("Chlorine", 337, albumIds[9], 3, artistaIds[2]),
            listOf("Nico and the Niners", 247, albumIds[9], 3, artistaIds[2]),

            // Lana Del Rey - Ocean Blvd (albumIds[10])
            listOf("A&W", 420, albumIds[10], 4, artistaIds[3]),
            listOf("The Grants", 287, albumIds[10], 4, artistaIds[3]),
            listOf("Candy Necklace", 321, albumIds[10], 4, artistaIds[3]),

            // Lana Del Rey - Lust for Life (albumIds[11])
            listOf("Love", 274, albumIds[11], 4, artistaIds[3]),
            listOf("Lust for Life", 262, albumIds[11], 4, artistaIds[3]),
            listOf("Summer Bummer", 258, albumIds[11], 4, artistaIds[3]),

            // Lana Del Rey - Norman Rockwell (albumIds[12])
            listOf("Doin Time", 211, albumIds[12], 4, artistaIds[3]),
            listOf("Mariners Apartment Complex", 241, albumIds[12], 4, artistaIds[3]),
            listOf("Venice Bitch", 569, albumIds[12], 4, artistaIds[3]),

            // Lana Del Rey - Ultraviolence (albumIds[13])
            listOf("West Coast", 265, albumIds[13], 4, artistaIds[3]),
            listOf("Ultraviolence", 247, albumIds[13], 4, artistaIds[3]),
            listOf("Shades of Cool", 345, albumIds[13], 4, artistaIds[3]),

            // Ozuna - Aura (albumIds[14])
            listOf("Te Boté", 367, albumIds[14], 5, artistaIds[4]),
            listOf("Vaina Loca", 195, albumIds[14], 5, artistaIds[4]),
            listOf("Ibiza", 218, albumIds[14], 5, artistaIds[4]),

            // Ozuna - Odisea (albumIds[15])
            listOf("Se Preparó", 213, albumIds[15], 5, artistaIds[4]),
            listOf("Tu Foto", 213, albumIds[15], 5, artistaIds[4]),
            listOf("Dile Que Tu Me Quieres", 213, albumIds[15], 5, artistaIds[4]),

            // Ozuna - Enoc (albumIds[16])
            listOf("Caramelo", 221, albumIds[16], 5, artistaIds[4]),
            listOf("Despeinada", 201, albumIds[16], 5, artistaIds[4]),
            listOf("Del Mar", 223, albumIds[16], 5, artistaIds[4]),

            // J Balvin - Energía (albumIds[17])
            listOf("Ginza", 212, albumIds[17], 5, artistaIds[5]),
            listOf("Bobo", 203, albumIds[17], 5, artistaIds[5]),
            listOf("Safari", 224, albumIds[17], 5, artistaIds[5]),

            // J Balvin - Colores (albumIds[18])
            listOf("Rojo", 141, albumIds[18], 5, artistaIds[5]),
            listOf("Amarillo", 138, albumIds[18], 5, artistaIds[5]),
            listOf("Blanco", 134, albumIds[18], 5, artistaIds[5]),

            // J Balvin - Jose (albumIds[19])
            listOf("Qué Más Pues?", 218, albumIds[19], 5, artistaIds[5]),
            listOf("In Da Getto", 193, albumIds[19], 5, artistaIds[5]),
            listOf("Una Nota", 151, albumIds[19], 5, artistaIds[5]),

            // Justin Bieber - Purpose (albumIds[20])
            listOf("Sorry", 201, albumIds[20], 1, artistaIds[6]),
            listOf("What Do You Mean?", 207, albumIds[20], 1, artistaIds[6]),
            listOf("Love Yourself", 233, albumIds[20], 1, artistaIds[6]),

            // Justin Bieber - Justice (albumIds[21])
            listOf("Peaches", 198, albumIds[21], 1, artistaIds[6]),
            listOf("Holy", 207, albumIds[21], 1, artistaIds[6]),
            listOf("Lonely", 152, albumIds[21], 1, artistaIds[6]),

            // Justin Bieber - Swag 2 (albumIds[22])
            listOf("Baby", 214, albumIds[22], 1, artistaIds[6]),
            listOf("Somebody To Love", 203, albumIds[22], 1, artistaIds[6]),
            listOf("U Smile", 208, albumIds[22], 1, artistaIds[6]),

            // The Weeknd - Starboy (albumIds[23])
            listOf("Starboy", 230, albumIds[23], 6, artistaIds[7]),
            listOf("Party Monster", 252, albumIds[23], 6, artistaIds[7]),
            listOf("Reminder", 221, albumIds[23], 6, artistaIds[7]),

            // The Weeknd - Dawn FM (albumIds[24])
            listOf("Take My Breath", 339, albumIds[24], 6, artistaIds[7]),
            listOf("Sacrifice", 188, albumIds[24], 6, artistaIds[7]),
            listOf("Out of Time", 193, albumIds[24], 6, artistaIds[7]),

            // The Weeknd - Beauty Behind the Madness (albumIds[25])
            listOf("The Hills", 242, albumIds[25], 6, artistaIds[7]),
            listOf("Can't Feel My Face", 213, albumIds[25], 6, artistaIds[7]),
            listOf("Often", 249, albumIds[25], 6, artistaIds[7]),

            // Karol G - Mañana Será Bonito (albumIds[26])
            listOf("TQG", 221, albumIds[26], 5, artistaIds[8]),
            listOf("Mientras Me Curo del Cora", 177, albumIds[26], 5, artistaIds[8]),
            listOf("Provenza", 213, albumIds[26], 5, artistaIds[8]),

            // Eminem - The Eminem Show (albumIds[27])
            listOf("Without Me", 290, albumIds[27], 2, artistaIds[9]),
            listOf("Cleanin Out My Closet", 297, albumIds[27], 2, artistaIds[9]),
            listOf("Sing for the Moment", 339, albumIds[27], 2, artistaIds[9]),

            // Eminem - The Slim Shady LP (albumIds[28])
            listOf("My Name Is", 268, albumIds[28], 2, artistaIds[9]),
            listOf("Guilty Conscience", 213, albumIds[28], 2, artistaIds[9]),
            listOf("Role Model", 205, albumIds[28], 2, artistaIds[9]),

            // Eminem - Recovery (albumIds[29])
            listOf("Love The Way You Lie", 263, albumIds[29], 2, artistaIds[9]),
            listOf("Not Afraid", 248, albumIds[29], 2, artistaIds[9]),
            listOf("No Love", 300, albumIds[29], 2, artistaIds[9]),

            // KATSEYE - Beautiful Chaos (albumIds[30])
            listOf("Gnarly", 180, albumIds[30], 7, artistaIds[10]),
            listOf("Gabriela", 195, albumIds[30], 7, artistaIds[10]),
            listOf("M.I.A", 188, albumIds[30], 7, artistaIds[10]),

            // Sia - This Is Acting (albumIds[31])
            listOf("Alive", 257, albumIds[31], 1, artistaIds[11]),
            listOf("Cheap Thrills", 225, albumIds[31], 1, artistaIds[11]),
            listOf("The Greatest", 220, albumIds[31], 1, artistaIds[11]),

            // Sia - 1000 Forms of Fear (albumIds[32])
            listOf("Chandelier", 216, albumIds[32], 1, artistaIds[11]),
            listOf("Elastic Heart", 266, albumIds[32], 1, artistaIds[11]),
            listOf("Big Girls Cry", 222, albumIds[32], 1, artistaIds[11]),

            // Sia - Everyday Is Christmas (albumIds[33])
            listOf("Santa's Coming For Us", 217, albumIds[33], 1, artistaIds[11]),
            listOf("Snowman", 165, albumIds[33], 1, artistaIds[11]),
            listOf("Ho Ho Ho", 209, albumIds[33], 1, artistaIds[11])
        )

        canciones.forEach { cancion ->
            val valores = ContentValues().apply {
                put(Cancion.COL_NOMBRE, cancion[0] as String)
                put(Cancion.COL_DURACION, cancion[1] as Int)
                put(Cancion.COL_ALBUM_ID, cancion[2] as Long)
                put(Cancion.COL_GENERO_ID, cancion[3] as Int)
                put(Cancion.COL_ARTISTA_ID, cancion[4] as Long)
            }
            db.insert(Cancion.TABLE_NAME_CANCIONES, null, valores)
        }
    }
}