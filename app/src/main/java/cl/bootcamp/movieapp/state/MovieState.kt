package cl.bootcamp.movieapp.state

data class MovieState(
    val id:  Int=0,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview : String? = null,
    val popularity: Double = 0.0,
    val poster_path: String,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean = false,
    val vote_average : Double = 0.0,
    val vote_count: Int=0

)
