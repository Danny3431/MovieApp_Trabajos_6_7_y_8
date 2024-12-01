package cl.bootcamp.movieapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val voteAverage: Double,
    val voteCount: Int?,
    val popularity: Double?,
    val genreIds: String? // convierte la lista a String para almacenamiento
)