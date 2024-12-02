package cl.bootcamp.movieapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import cl.bootcamp.movieapp.R
import cl.bootcamp.movieapp.model.Movie
import cl.bootcamp.movieapp.util.ImageHelper


@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier, // Añadir el parámetro modifier aquí
    onCardClick: () -> Unit = {},  // Asegúrate de incluir estos parámetros también
    onDeleteClick: () -> Unit = {}
) {
    // Aquí puedes usar el modifier recibido
    Column(
        modifier = modifier
    ) {
        // Tu contenido para la tarjeta de película
        Text(movie.title)
        // Resto de la UI de la MovieCard
    }
}
