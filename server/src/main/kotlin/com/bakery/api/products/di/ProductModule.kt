package com.bakery.api.products.di

import com.bakery.api.products.service.ProductService
import com.bakery.database.repository.product.ProductRepository
import com.bakery.database.repository.product.ProductRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val productModule = module {
    singleOf(::ProductRepositoryImpl) bind ProductRepository::class

    singleOf(::ProductService)
}
