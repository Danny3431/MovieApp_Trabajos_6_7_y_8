package cl.bootcamp.movieapp.util

class ImageHelper {
    companion object {
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"
        const val IMAGE_SIZE = "w500"

        fun buildImageUrl(posterPath: String): String {
            return "$BASE_IMAGE_URL$IMAGE_SIZE$posterPath"
        }
    }
}
