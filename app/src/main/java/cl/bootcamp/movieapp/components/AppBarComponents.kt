package cl.bootcamp.movieapp.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.movieapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    title: String,
    showBackButton: Boolean = false, // Agregamos este parámetro
    onBackNavClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(start = 4.dp)
            )
        },
        // Si showBackButton es true, se muestra el ícono de retroceso
        navigationIcon = if (showBackButton) {
            {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        } else {
            @Composable {} // Composable vacío cuando no se necesita el ícono de retroceso
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}
/*@Composable
fun NavegationBarButtons(navController: NavController) {
    val selectedItem = remember { mutableStateOf(0) }
    val items = listOf("Home", "Movie", "Add Movie")
    val icons = listOf(
        Icons.Filled.Home,  // Ícono de Home
        Image(painter = painterResource(id = R.drawable.ic_movie), contentDescription = "Movie Icon"),// Ícono de Movie
        Icons.Filled.Add // Ícono de agregar película
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    when (index) {
                        0 -> Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Inicio"
                        )
                        1 -> Icon(
                            painter = painterResource(id = R.drawable.ic_movie),
                            contentDescription = "Películas"
                        )
                        2 -> Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Agregar Película"
                        )
                    }
                },
                label = { Text(item) },
                selected = selectedItem.value == index,
                onClick = {
                    selectedItem.value = index
                    when (index) {
                        0 -> navController.navigate("home") // Navegar a Home
                        1 -> navController.navigate("movieDetailView") // Navegar a detalle de película
                        2 -> navController.navigate("addMovie") // Navegar a agregar película
                    }
                }
            )
        }
    }
}*/