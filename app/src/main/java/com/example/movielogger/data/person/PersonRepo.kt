package com.example.movielogger.data.person

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

interface IPersonRepo {
    suspend fun getAll(): Result<List<Person>>
    suspend fun get(id: UUID): Result<Person?>
    suspend fun findByName(name: String): Result<List<Person>>
    suspend fun insert(person: Person): Result<Unit>
    suspend fun store(person: Person): Result<Unit>
}

class PersonRepo @Inject constructor(
    val dao: PersonDao
) : IPersonRepo {

    override suspend fun getAll(): Result<List<Person>> {
        return withContext(Dispatchers.IO) {
            Result.runCatching { dao.getAll().map { it.toDomain() } }
        }
    }

    override suspend fun get(id: UUID): Result<Person?> {
        return withContext(Dispatchers.IO) {
            Result.runCatching { dao.loadAllByIds(id.toString()).firstOrNull()?.toDomain() }
        }
    }

    override suspend fun findByName(name: String): Result<List<Person>> {
        return withContext(Dispatchers.IO) {
            Result.runCatching { dao.findByName(name).map { it.toDomain() } }
        }
    }

    override suspend fun insert(person: Person): Result<Unit> {
        return withContext(Dispatchers.IO) {
            Result.runCatching { dao.insertAll(PersonDbo.fromDomain(person)) }
        }
    }

    override suspend fun store(person: Person): Result<Unit> {
        return withContext(Dispatchers.IO) {
            Result.runCatching { dao.update(PersonDbo.fromDomain(person)) }
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class PersonRepoModule {

    @Binds
    abstract fun bindPersonRepoModule(
        personRepo: PersonRepo
    ): IPersonRepo

}