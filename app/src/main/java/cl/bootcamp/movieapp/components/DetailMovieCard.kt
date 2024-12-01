package cl.bootcamp.movieapp.components

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import cl.bootcamp.movieapp.model.Movie
import cl.bootcamp.movieapp.util.ImageHelper
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import cl.bootcamp.movieapp.R

@Composable
fun DetailMovieCard(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Imagen principal (Backdrop)
            AsyncImage(
                model = ImageHelper.buildImageUrl(movie.backdropPath ?: ""),
                contentDescription = "Imagen de fondo",
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
                modifier = Modifier
                    .height(380.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Título de la película
            Text(
                text = "Título: ${movie.originalTitle}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Fecha de estreno
            Text(
                text = "Fecha de estreno: ${movie.releaseDate}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Promedio de votos
            Text(
                text = "Promedio de votos: ${movie.voteAverage}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Sinopsis
            Text(
                text = "Sinopsis: ${movie.overview ?: "No disponible"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
