package com.example.movielogger.data

interface Dbo<T> {
    fun toDomain(): T
}