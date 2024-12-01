package cl.bootcamp.movieapp.repository


import android.util.Log
import cl.bootcamp.movieapp.data.ApiRest
import cl.bootcamp.movieapp.model.Movie
import cl.bootcamp.movieapp.model.toEntity
import cl.bootcamp.movieapp.model.toMovie
import cl.bootcamp.movieapp.room.MovieDao
import cl.bootcamp.movieapp.room.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject


class MovieDaoRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val apiRest: ApiRest
) : MovieRepository { // Implementamos la interfaz MovieRepository

    // Obtener películas desde la API
    override suspend fun fetchMoviesFromApi(apiKey: String, page: Int): List<Movie> {
        Log.d("MovieRepository", "fetchMoviesFromApi() llamado con apiKey=$apiKey y page=$page")
        return try {
            val response = apiRest.getTopRatedMovies(apiKey = apiKey, page = page)
            if (response.isSuccessful) {
                Log.d("MovieRepository", "Respuesta exitosa de la API, código: ${response.code()}")
                response.body()?.results?.map { it } ?: emptyList()
            } else {
                Log.e("MovieRepository", "Error en la respuesta de la API: ${response.message()}")
                throw Exception("Error al obtener películas: ${response.message()}")
            }
        } catch (e: HttpException) {
            Log.e("MovieRepository", "Excepción HTTP: ${e.message}", e)
            emptyList()
        }
    }

    // Guardar películas en la base de datos
    override suspend fun saveMoviesToDb(movies: List<Movie>) {
        val movieEntities = movies.map { it.toEntity() }
        movieDao.insertMovies(movieEntities)
    }

    // Obtener películas desde la base de datos
    override suspend fun getMoviesFromDb(): List<Movie> {
        return movieDao.getAllMovies()
            .map { entities -> entities.map { it.toMovie() } }
            .first() // Convertir Flow a List<Movie>
    }

    // Obtener una película por ID
    override suspend fun getMovieById(id: Int): Movie {
        return movieDao.getMovieById(id).toMovie()
    }

    // Obtener todas las películas en Room como Flow
    override fun getAllMovieRoom(): Flow<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    // Limpiar el estado de la base de datos
    override suspend fun cleanState() {
        movieDao.cleanState()
    }
    // Eliminar película
    override suspend fun deleteMovieById(id: Int) {
        movieDao.deleteMovieById( id )
    }
}