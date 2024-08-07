package com.bakery.database.helper

import com.bakery.BakeryDb
import com.bakery.database.driver.DriverFactory
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal object DbHelper {
    private val driver = DriverFactory.initDatabase()

    private var db: BakeryDb? = null

    private val mutex = Mutex()

    suspend fun <Result : Any?> withDatabase(block: (BakeryDb) -> Result): Result = mutex.withLock {
        if (db == null) {
            db = createDb()
        }

        return@withLock block(db!!)
    }

    private fun createDb(): BakeryDb {
        return BakeryDb(driver = driver)
    }
}
