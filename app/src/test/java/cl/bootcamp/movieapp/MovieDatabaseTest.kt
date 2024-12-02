package cl.bootcamp.movieapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import cl.bootcamp.movieapp.room.MovieDao
import cl.bootcamp.movieapp.room.MovieDatabase
import cl.bootcamp.movieapp.room.MovieEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieDatabaseTest {

    private lateinit var movieDao: MovieDao
    private lateinit var db: MovieDatabase

    @Before
    fun setUp() {
        // Asegúrate de que la base de datos se inicialice correctamente
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries() // Agrega esta línea para ejecutar en el hilo principal durante pruebas
            .build()
        movieDao = db.movieDao()
    }

    @After
    fun closeDb() {
        // Cierra la base de datos después de cada prueba
        db.close()
    }

    @Test
    fun testInsertMovie() = runBlocking {
        // Crear instancia de MovieEntity para la prueba
        val movieEntity = MovieEntity(
            id = 1,
            title = "The Lord of the Rings",
            originalTitle = "El Señor de los Anillos",
            posterPath = "/path_to_image.jpg",
            backdropPath = "/backdrop_path.jpg",
            overview = "Una película épica basada en la obra de J.R.R. Tolkien.",
            releaseDate = "2001-12-18",
            voteAverage = 8.4,
            voteCount = 10000,
            popularity = 90.5,
            genreIds = "[12, 14, 28]"
        )

        // Insertar película en la base de datos
        movieDao.insertMovies(listOf(movieEntity))

        // Recuperar la película insertada
        val retrievedMovie = movieDao.getMovieById(1)

        // Verificar que los datos coincidan
        assertNotNull(retrievedMovie)
        assertEquals(movieEntity.id, retrievedMovie.id)
        assertEquals(movieEntity.title, retrievedMovie.title)
        assertEquals(movieEntity.voteAverage, retrievedMovie.voteAverage, 0.01)
    }

    @Test
    fun testDeleteMovie() = runBlocking {
        // Crear instancia de MovieEntity para la prueba
        val movieEntity = MovieEntity(
            id = 1,
            title = "The Lord of the Rings",
            originalTitle = "El Señor de los Anillos",
            posterPath = "/path_to_image.jpg",
            backdropPath = "/backdrop_path.jpg",
            overview = "Una película épica basada en la obra de J.R.R. Tolkien.",
            releaseDate = "2001-12-18",
            voteAverage = 8.4,
            voteCount = 10000,
            popularity = 90.5,
            genreIds = "[12, 14, 28]"
        )

        // Insertar película
        movieDao.insertMovies(listOf(movieEntity))

        // Eliminar película
        movieDao.deleteMovieById(1)

        // Intentar recuperar la película eliminada
        val deletedMovie = movieDao.getMovieById(1)

        // Verificar que no existe
        assertNull(deletedMovie)
    }
}