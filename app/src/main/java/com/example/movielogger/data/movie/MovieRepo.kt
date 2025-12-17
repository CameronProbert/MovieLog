package com.example.movielogger.data.movie

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

interface IMovieRepo {
    suspend fun getAll(): Result<List<MovieSummary>>
    suspend fun get(id: UUID): Result<Movie?>
    suspend fun store(movie: Movie): Result<Unit>

}

class MovieRepo @Inject constructor(
    val dao: MovieDao
) : IMovieRepo {

    override suspend fun getAll(): Result<List<MovieSummary>> {
//        return DataSource.loadMovies().map { it.summary }

        return withContext(Dispatchers.IO) {
            Result.runCatching { dao.getAll().map { it.toDomain().summary } }
        }
    }

    override suspend fun get(id: UUID): Result<Movie?> {
        return withContext(Dispatchers.IO) {
            Result.runCatching { dao.loadAllByIds(id.toString()).firstOrNull()?.toDomain() }
        }
    }

    override suspend fun store(movie: Movie): Result<Unit> {
        return withContext(Dispatchers.IO) {
            Result.runCatching { dao.update(MovieDbo.fromDomain(movie)) }
        }
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