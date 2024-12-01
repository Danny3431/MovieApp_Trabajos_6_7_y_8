package cl.bootcamp.movieapp.util

class Constants {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val ENDPOINT_MOVIES  = "top_rated"
        const val API_KEY = BuildConfig.api_key
        const val DATABASE_NAME = "movie_database"
    }

}