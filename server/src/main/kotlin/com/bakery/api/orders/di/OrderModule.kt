package com.bakery.api.orders.di

import com.bakery.api.orders.service.OrderService
import com.bakery.database.repository.order.OrderRepository
import com.bakery.database.repository.order.OrderRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val orderModule = module {
    singleOf(::OrderRepositoryImpl) bind OrderRepository::class

    singleOf(::OrderService)
}
