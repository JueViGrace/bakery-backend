package com.bakery.database.source

interface DataSource<T> {
    suspend fun findAll(): List<T> = emptyList()
    suspend fun findOneById(id: Int): T? = null
    suspend fun insert(e: T): T? = null
    suspend fun update(id: Int, e: T): T? = null
    suspend fun softDelete(id: Int): T? = null
    suspend fun deleteById(id: Int): T? = null
    suspend fun delete(): T? = null
}
