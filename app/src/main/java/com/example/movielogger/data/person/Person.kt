package com.example.movielogger.data.person

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movielogger.data.Dbo
import kotlinx.serialization.Serializable
import java.util.*

data class Person(
    val id: UUID = UUID.randomUUID(),
    val name: String,
)

@Serializable
@Entity(tableName = "person")
data class PersonDbo(
    @PrimaryKey val id: UUID,
    val name: String,
): Dbo<Person> {
    override fun toDomain(): Person {
        return Person(
            id = id,
            name = name,
        )
    }

    companion object {
        fun fromDomain(person: Person): PersonDbo {
            return PersonDbo(
                id = person.id,
                name = person.name,
            )
        }
    }
}

