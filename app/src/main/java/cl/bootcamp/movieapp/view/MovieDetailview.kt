package cl.bootcamp.movieapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cl.bootcamp.movieapp.components.AppBarView
import cl.bootcamp.movieapp.components.DetailMovieCard
//import cl.bootcamp.movieapp.components.NavegationBarButtons
import cl.bootcamp.movieapp.viewmodel.MovieViewModel

@Composable
fun DetailView(movieId: Int, viewModel: MovieViewModel, navController: NavHostController) {
    val movie = viewModel.getMovieById(movieId)

    // Barra superior con el título de la película
    Scaffold(
        topBar = {
            AppBarView(
                title = "Detalles de la Película",
                showBackButton = true, // Mostrar botón de retroceso
                onBackNavClicked = { navController.popBackStack() }
            )
        },
        /*bottomBar = {
            NavegationBarButtons(navController)
        }*/
    ) {
        Column(modifier = Modifier.padding(it)) {
            val movie = viewModel.getMovieById(movieId)
            DetailMovieCard(movie = movie)
        }
    }
}
