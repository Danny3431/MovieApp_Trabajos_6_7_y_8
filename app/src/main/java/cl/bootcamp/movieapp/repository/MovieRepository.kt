package cl.bootcamp.movieapp.repository


import cl.bootcamp.movieapp.model.Movie
import cl.bootcamp.movieapp.room.MovieEntity
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    suspend fun fetchMoviesFromApi(apiKey: String, page: Int): List<Movie>
    suspend fun saveMoviesToDb(movies: List<Movie>)
    suspend fun getMoviesFromDb(): List<Movie>
    suspend fun getMovieById(id: Int): Movie
    fun getAllMovieRoom(): Flow<List<MovieEntity>>
    suspend fun cleanState()
    suspend fun deleteMovieById(id: Int)


}