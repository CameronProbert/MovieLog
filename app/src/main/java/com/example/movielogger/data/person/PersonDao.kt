package com.example.movielogger.data.person

import androidx.room.*

@Dao
interface PersonDao {

    @Query("SELECT id, name FROM person")
    fun getAll(): List<PersonDbo>

    @Query("SELECT * FROM person WHERE id IN (:personIds)")
    fun loadAllByIds(vararg personIds: String): List<PersonDbo>

    @Query("SELECT * FROM person WHERE name LIKE :name LIMIT 5")
    fun findByName(name: String): List<PersonDbo>

    @Update
    fun update(person: PersonDbo)

    @Insert
    fun insertAll(vararg persons: PersonDbo)

    @Delete
    fun delete(person: PersonDbo)
}