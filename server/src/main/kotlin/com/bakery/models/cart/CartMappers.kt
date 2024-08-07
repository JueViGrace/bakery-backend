package com.bakery.models.cart

import com.bakery.BakeryCart
import com.bakery.BakeryCartWithProducts
import com.bakery.BakeryOrder
import com.bakery.BakeryOrderWithProducts
import com.bakery.FindCartById
import com.bakery.models.order.OrderStatus
import com.bakery.models.product.ProductDto
import io.ktor.server.http.toHttpDateString

fun CartDto.toDatabase(): BakeryCart = BakeryCart(
    cart_id = cartId,
    total_amount = totalAmount
)

fun CartDto.toDatabaseOrder(): BakeryOrder = BakeryOrder(
    order_id = 0,
    total_amount = totalAmount,
    payment_method = "cash",
    status = OrderStatus.PLACED.value,
    order_user_id = cartId,
    created_at = null,
    updated_at = null,
    deleted_at = null
)

fun CartDto.toOrderProducts(): List<BakeryOrderWithProducts> {
    return items.map { item ->
        BakeryOrderWithProducts(
            order_products_id = 0,
            product_order_id = item.productDto.productId,
            price = item.productDto.price,
            quantity = item.quantity
        )
    }
}

fun CartDto.itemsToDatabase(): List<BakeryCartWithProducts> {
    return items.map { item ->
        BakeryCartWithProducts(
            cart_products_id = cartId,
            product_cart_id = item.productDto.productId,
            quantity = item.quantity
        )
    }
}

fun List<AddToCartItemsDto>.toDatabase(cartId: Int): List<BakeryCartWithProducts> {
    return this.map { item ->
        BakeryCartWithProducts(
            cart_products_id = cartId,
            product_cart_id = item.productId,
            quantity = item.quantity,
        )
    }
}

fun List<FindCartById>.toDto(): CartDto {
    val cartItems = this.groupBy { cart ->
        CartDto(
            cartId = cart.cart_id,
            totalAmount = cart.total_amount,
            items = emptyList()
        )
    }

    var cartDto = CartDto()

    cartItems.forEach { (key, value) ->
        when {
            value.map { it.product_id }.first() == null -> {
                cartDto = cartDto.copy(
                    cartId = key.cartId,
                    totalAmount = key.totalAmount,
                    items = key.items
                )
            }

            else -> {
                cartDto = cartDto.copy(
                    cartId = key.cartId,
                    totalAmount =
                    value.sumOf { (it.quantity ?: 0) * (it.price ?: 0.0) },
                    items =
                    value.map { product ->
                        CartItemsDto(
                            quantity = product.quantity ?: 0,
                            productDto = ProductDto(
                                productId = product.product_id ?: 0,
                                name = product.name ?: "",
                                description = product.description ?: "",
                                price = product.price ?: 0.0,
                                category = product.category ?: "",
                                stock = product.stock ?: 0,
                                image = product.image ?: "",
                                createdAt = product.created_at?.toHttpDateString(),
                                updatedAt = product.updated_at?.toHttpDateString(),
                                deletedAt = product.deleted_at?.toHttpDateString()
                            )
                        )
                    }
                )
            }
        }
    }

    return cartDto
}
