package com.bakery.database.repository.order

import com.bakery.BakeryOrder
import com.bakery.BakeryOrderWithProducts
import com.bakery.FindAllByUser
import com.bakery.FindAllOrders
import com.bakery.FindOneOrder
import com.bakery.database.helper.DbHelper
import com.bakery.database.source.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

interface OrderRepository : DataSource<BakeryOrder> {
    suspend fun findAllWithRelation(): List<FindAllOrders>
    suspend fun findAllByUser(id: Int): List<FindAllByUser>
    suspend fun findOneWithRelation(id: Int): List<FindOneOrder>
    suspend fun createOrder(order: BakeryOrder, orderWithProducts: List<BakeryOrderWithProducts>): BakeryOrder?
    suspend fun updateStatus(id: Int, status: String): BakeryOrder?
}

class OrderRepositoryImpl(
    private val scope: CoroutineScope,
    private val coroutineContext: CoroutineContext
) : OrderRepository {
    override suspend fun findAllWithRelation(): List<FindAllOrders> {
        return scope.async(coroutineContext) {
            DbHelper.withDatabase { db ->
                db.transactionWithResult {
                    db.bakeryOrderQueries
                        .findAllOrders()
                        .executeAsList()
                }
            }
        }.await()
    }

    override suspend fun findAllByUser(id: Int): List<FindAllByUser> {
        return scope.async(coroutineContext) {
            DbHelper.withDatabase { db ->
                db.transactionWithResult {
                    db.bakeryOrderQueries
                        .findAllByUser(id)
                        .executeAsList()
                }
            }
        }.await()
    }

    override suspend fun findOneWithRelation(id: Int): List<FindOneOrder> {
        return scope.async(coroutineContext) {
            DbHelper.withDatabase { db ->
                db.transactionWithResult {
                    db.bakeryOrderQueries
                        .findOneOrder(id)
                        .executeAsList()
                }
            }
        }.await()
    }

    override suspend fun findOneById(id: Int): BakeryOrder? {
        return DbHelper.withDatabase { db ->
            db.transactionWithResult {
                db.bakeryOrderQueries
                    .findOneById(id)
                    .executeAsOneOrNull()
            }
        }
    }

    override suspend fun createOrder(
        order: BakeryOrder,
        orderWithProducts: List<BakeryOrderWithProducts>
    ): BakeryOrder? {
        return scope.async(coroutineContext) {
            DbHelper.withDatabase { db ->
                db.transactionWithResult {
                    val savedOrder = db.bakeryOrderQueries
                        .insert(order)
                        .executeAsOneOrNull()

                    if (savedOrder != null) {
                        orderWithProducts.forEach { orderProducts ->
                            db.bakeryOrderWithProductsQueries
                                .insert(orderProducts.copy(order_products_id = savedOrder.order_id))
                                .executeAsList()
                        }
                    }

                    savedOrder
                }
            }
        }.await()
    }

    override suspend fun updateStatus(id: Int, status: String): BakeryOrder? {
        return DbHelper.withDatabase { db ->
            db.transactionWithResult {
                db.bakeryOrderQueries
                    .updateStatus(
                        id = id,
                        status = status
                    )
                    .executeAsOneOrNull()
            }
        }
    }

    override suspend fun softDelete(id: Int): BakeryOrder? {
        return DbHelper.withDatabase { db ->
            db.transactionWithResult {
                db.bakeryOrderQueries
                    .softDelete(id)
                    .executeAsOneOrNull()
            }
        }
    }
}
