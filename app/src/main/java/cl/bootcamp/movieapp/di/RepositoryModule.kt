package cl.bootcamp.movieapp.di



import cl.bootcamp.movieapp.data.ApiRest
import cl.bootcamp.movieapp.repository.MovieDaoRepositoryImpl
import cl.bootcamp.movieapp.repository.MovieRepository
import cl.bootcamp.movieapp.room.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        apiRest: ApiRest,
        movieDao: MovieDao
    ): MovieRepository {
        return MovieDaoRepositoryImpl(movieDao, apiRest)
    }
}