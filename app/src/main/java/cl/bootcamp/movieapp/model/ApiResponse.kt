package cl.bootcamp.movieapp.model


import cl.bootcamp.movieapp.room.MovieEntity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("genre_ids") val genreIds: List<Int>?
)

data class DetailMovie(
    @SerializedName("adult") val adult: Boolean = false,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("genre_ids") val genreIds: List<Int> = listOf(),
    @SerializedName("id") val id: Int = 0,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("popularity") val popularity: Double = 0.0,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("video") val video: Boolean = false,
    @SerializedName("vote_average") val voteAverage: Double = 0.0,
    @SerializedName("vote_count") val voteCount: Int = 0
)

// Convertir de MovieEntity a Movie
fun MovieEntity.toMovie(): Movie {
    val genres = this.genreIds?.let {
        Gson().fromJson(it, Array<Int>::class.java)?.toList()
    } ?: emptyList()

    return Movie(
        id = id ?: 0,
        title = title ?: "Título desconocido",
        originalTitle = originalTitle ?: "Título original desconocido",
        posterPath = posterPath ?: "",
        backdropPath = backdropPath,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        popularity = popularity ?: 0.0,
        genreIds = genres
    )
}

// Convertir de Movie a MovieEntity
fun Movie.toEntity(): MovieEntity {
    val genreString = Gson().toJson(genreIds ?: emptyList<Int>()) // Especifica el tipo de la lista vacía
    return MovieEntity(
        id = id,
        title = title,
        originalTitle = originalTitle,
        posterPath = posterPath,
        backdropPath = backdropPath,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        popularity = popularity,
        genreIds = genreString
    )
}