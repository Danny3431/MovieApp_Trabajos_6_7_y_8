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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.movieapp.components.AppBarView
import cl.bootcamp.movieapp.viewmodel.MovieViewModel
import cl.bootcamp.movieapp.components.MovieCard
import cl.bootcamp.movieapp.model.Movie

//import cl.bootcamp.movieapp.components.NavegationBarButtons



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    movies: List<Movie> = emptyList() // Lista de películas simulada para pruebas
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MovieDB") },
                modifier = Modifier.semantics {
                    contentDescription = "AppBar"
                }
            )
        },
        content = { paddingValues ->
            // Aquí se pasa paddingValues para aplicar el padding
            LazyColumn(
                contentPadding = paddingValues // Aplica el padding aquí
            ) {
                items(movies) { movie ->
                    MovieCard(
                        movie = movie,
                        modifier = Modifier.testTag("MovieCard")
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción para agregar película */ },
                modifier = Modifier.testTag("AddMovieButton")
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Película")
            }
        }
    )
    }