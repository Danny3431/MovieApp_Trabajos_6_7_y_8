package cl.bootcamp.movieapp.di

import android.content.Context
import androidx.room.Room
import cl.bootcamp.movieapp.data.ApiRest
import cl.bootcamp.movieapp.room.MovieDao
import cl.bootcamp.movieapp.room.MovieDatabase
import cl.bootcamp.movieapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {



    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // Usar constante de `Constants`
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesMovieApiService(retrofit: Retrofit): ApiRest =
        retrofit.create(ApiRest::class.java)

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            Constants.DATABASE_NAME // Usar constante de `Constants`
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesMovieDao(database: MovieDatabase): MovieDao = database.movieDao()
}