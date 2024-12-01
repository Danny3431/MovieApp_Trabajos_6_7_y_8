package cl.bootcamp.movieapp.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.movieapp.components.AppBarView
import cl.bootcamp.movieapp.viewmodel.MovieViewModel
import cl.bootcamp.movieapp.components.MovieCard

//import cl.bootcamp.movieapp.components.NavegationBarButtons



@Composable
fun HomeView(navController: NavController, viewModel: MovieViewModel) {
    val movies = viewModel.movies.collectAsState(initial = emptyList())
    val listState = rememberLazyListState()


    Scaffold(
        topBar = {
            AppBarView(
                title = "MobieDB tu App de Películas!",
                showBackButton = false // Sin botón de retroceso
            )
        },
        /*bottomBar = {
            NavegationBarButtons(navController) // Barra de navegación inferior
        },*/
        floatingActionButton = {
            FloatingActionButton(

                onClick = {
                    // Acción para agregar películas
                    viewModel.addMovie()
                    Modifier.offset(y = (-32).dp) // Alineamos el botón un poco hacia arriba
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add, // Ícono de agregar (+)
                    contentDescription = "Agregar Película",
                    tint = Color.White
                )
            }
        }
    ) {
        // Contenido de la pantalla
        Column(modifier = Modifier.padding(it)) {

            // Lista de películas con LazyColumn
            LazyColumn(state = listState) {
                items(movies.value) { movie ->
                    MovieCard(
                        movie = movie,
                        onDeleteClick = { viewModel.deleteMovie(movie.id) },
                        onCardClick = { navController.navigate("movieDetailView/${movie.id}") }
                    )
                }
            }

            // Paginación al llegar al final de la lista
            val isAtEndOfList =
                listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == movies.value.lastIndex

            if (isAtEndOfList) {
                // Cargar la siguiente página
                viewModel.fetchMoviesFromApi()
            }
        }
    }
}
