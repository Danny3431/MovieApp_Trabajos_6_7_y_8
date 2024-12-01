package cl.bootcamp.movieapp.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.movieapp.view.DetailView
import cl.bootcamp.movieapp.view.HomeView
import cl.bootcamp.movieapp.viewmodel.MovieViewModel


@Composable
fun AppNavegation(modifier: Modifier = Modifier,
                  viewModel: MovieViewModel,
                  navController: NavHostController) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "MovieListView") {
        composable("movieListView") {

            HomeView(viewModel = viewModel, navController = navController)
        }
        composable(
            route = "movieDetailView/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            DetailView(movieId = id, viewModel = viewModel, navController = navController)
        }


    }
}
