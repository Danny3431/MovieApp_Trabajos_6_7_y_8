package cl.bootcamp.movieapp.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MovieEntity::class], version = 4)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}