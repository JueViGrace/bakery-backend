package com.bakery.models.product

import com.bakery.BakeryProduct
import io.ktor.server.http.toHttpDateString

fun BakeryProduct.toDto(): ProductDto = ProductDto(
    productId = product_id,
    name = name,
    description = description,
    price = price,
    category = category,
    stock = stock,
    image = image,
    createdAt = created_at?.toHttpDateString(),
    updatedAt = updated_at?.toHttpDateString(),
    deletedAt = deleted_at?.toHttpDateString()
)

fun ProductDto.toDatabase(): BakeryProduct = BakeryProduct(
    product_id = productId,
    name = name,
    description = description,
    price = price,
    category = category,
    stock = stock,
    image = image,
    created_at = null,
    updated_at = null,
    deleted_at = null
)

fun CreateProductDto.toDatabase(): BakeryProduct = BakeryProduct(
    product_id = 0,
    name = name,
    description = description,
    price = price,
    category = category,
    stock = stock,
    image = image,
    created_at = null,
    updated_at = null,
    deleted_at = null
)
