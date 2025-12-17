package com.example.movielogger.data.movie

import com.example.movielogger.data.DataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.util.UUID
import javax.inject.Inject

interface IMovieRepo {
    fun getAll(): List<MovieSummary>
    fun get(id: UUID): Movie
    fun store(movie: Movie)

}

class MovieRepo @Inject constructor(
    val dao: MovieDao
) : IMovieRepo {

    override fun getAll(): List<MovieSummary> {
        return DataSource.loadMovies().map { it.summary }
        // TODO: return dao.getAll().map { it.toDomain().summary }
    }

    override fun get(id: UUID): Movie {
        return dao.loadAllByIds(id.toString()).first().toDomain()
    }

    override fun store(movie: Movie) {
        dao.update(MovieDbo.fromDomain(movie))
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieRepoModule {

    @Binds
    abstract fun bindMovieRepoModule(
        movieRepo: MovieRepo
    ): IMovieRepo

}