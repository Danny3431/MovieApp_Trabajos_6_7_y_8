package cl.bootcamp.movieapp.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.movieapp.model.Movie
import cl.bootcamp.movieapp.model.toMovie
import cl.bootcamp.movieapp.repository.MovieRepository
import cl.bootcamp.movieapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private var _nextPage = MutableStateFlow(1)
    val nextPage: Int get() = _nextPage.value

    private var isLoading = false

    init {
        println("Repositorio inyectado: $repository")
        loadMoviesFromDb()
    }

    // Función para cargar las películas desde la base de datos
    fun loadMoviesFromDb() {
        viewModelScope.launch {
            repository.getAllMovieRoom()
                .catch { e -> handleError("Error cargando películas", e) }
                .collect { entities ->
                    _movies.value = entities.map { it.toMovie() }
                }
        }
    }

    // Función para obtener una película por ID
    fun getMovieById(movieId: Int): Movie {
        return movies.value.find { it.id == movieId }
            ?: throw IllegalArgumentException("Película no encontrada")
    }

    // Función para obtener las películas desde la API
    fun fetchMoviesFromApi() {
        if (isLoading) return // Evita solicitudes simultáneas
        isLoading = true

        viewModelScope.launch {
            try {
                Log.d("MovieViewModel", "Cargando página: ${_nextPage.value}")

                // Solicita la página actual
                val movies = repository.fetchMoviesFromApi(Constants.API_KEY, _nextPage.value)

                if (movies.isNotEmpty()) {
                    // Combina las nuevas películas con las ya cargadas
                    val updatedMovies = _movies.value.toMutableList().apply { addAll(movies) }
                    _movies.value = updatedMovies

                    // Guarda las nuevas películas en la base de datos
                    repository.saveMoviesToDb(movies)

                    // Avanza a la siguiente página
                    incrementPage()
                } else {
                    Log.d("MovieViewModel", "No hay más películas disponibles.")
                }
            } catch (e: Exception) {
                handleError("Error al cargar películas desde la API", e)
            } finally {
                isLoading = false
            }
        }
    }

    // Función para incrementar la páginas
    private fun incrementPage() {
        _nextPage.value += 1
        Log.d("MovieViewModel", "Página actual incrementada a: ${_nextPage.value}")
    }

    // Función para agregar una película
    fun addMovie() {
        Log.d("MovieViewModel", "addMovie() fue llamada")
        viewModelScope.launch {
            try {
                Log.d("MovieViewModel", "Iniciando solicitud a la API en página: ${_nextPage.value}")
                val movies = repository.fetchMoviesFromApi(Constants.API_KEY, _nextPage.value)
                Log.d("MovieViewModel", "Películas obtenidas de la API: ${movies.size}")
                if (movies.isNotEmpty()) {
                    repository.saveMoviesToDb(movies)
                    Log.d("MovieViewModel", "Películas guardadas en la base de datos")
                    loadMoviesFromDb()
                    incrementPage()
                } else {
                    Log.d("MovieViewModel", "No se encontraron películas en la API")
                }
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error al agregar películas: ${e.message}", e)
            }
        }
    }

    // Función para eliminar una película
    fun deleteMovie(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteMovieById(id)
                loadMoviesFromDb()
            } catch (e: Exception) {
                handleError("Error eliminando película", e)
            }
        }
    }

    private fun handleError(message: String, exception: Throwable) {
        _error.value = "$message: ${exception.message}"
    }
}