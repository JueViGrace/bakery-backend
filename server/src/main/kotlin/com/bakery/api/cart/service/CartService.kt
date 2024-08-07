package com.bakery.api.cart.service

import com.bakery.database.repository.cart.CartRepository
import com.bakery.database.repository.order.OrderRepository
import com.bakery.database.repository.product.ProductRepository
import com.bakery.models.cart.AddToCartDto
import com.bakery.models.cart.CartDto
import com.bakery.models.cart.itemsToDatabase
import com.bakery.models.cart.toDatabase
import com.bakery.models.cart.toDatabaseOrder
import com.bakery.models.cart.toDto
import com.bakery.models.cart.toOrderProducts
import com.bakery.models.response.AppResponse
import com.bakery.models.response.DefaultHttpResponse
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CartService(
    private val cartRepository: CartRepository,
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val coroutineContext: CoroutineContext,
) {
    suspend fun getCart(id: Int): AppResponse<CartDto> {
        return try {
            withContext(coroutineContext) {
                val cart = findCart(id) ?: return@withContext DefaultHttpResponse.notFound(
                    error = "Cart with id $id was not found"
                )

                return@withContext DefaultHttpResponse.ok(cart)
            }
        } catch (e: Exception) {
            error(e)
        }
    }

    suspend fun checkout(id: Int): AppResponse<String> {
        return try {
            withContext(coroutineContext) {
                val cart = findCart(id) ?: return@withContext DefaultHttpResponse.notFound(
                    error = "Cart with id $id was not found"
                )

                if (cart.items.isEmpty()) {
                    return@withContext DefaultHttpResponse.badRequest(message = "Cart is empty")
                }

                orderRepository.createOrder(cart.toDatabaseOrder(), cart.toOrderProducts())
                    ?: return@withContext DefaultHttpResponse.badRequest(message = "Failed to create order")

                cartRepository.removeItem(id = cart.cartId, e = cart.itemsToDatabase())

                DefaultHttpResponse.created("Order created")
            }
        } catch (e: Exception) {
            error(e)
        }
    }

    // TODO: receive cart id from token
    // TODO: get payment method in cart
    suspend fun addItem(addToCartDto: AddToCartDto): AppResponse<String> {
        return try {
            withContext(coroutineContext) {
                findCart(addToCartDto.cartId)
                    ?: return@withContext DefaultHttpResponse.notFound(
                        error = "Cart with id ${addToCartDto.cartId} was not found"
                    )

                if (addToCartDto.items.map { productRepository.findOneById(it.productId) }.contains(null)) {
                    return@withContext DefaultHttpResponse.badRequest(message = "Invalid products in request")
                }

                cartRepository.addItemsToCart(
                    id = addToCartDto.cartId,
                    e = addToCartDto.items.toDatabase(addToCartDto.cartId)
                )

                DefaultHttpResponse.ok("Added!")
            }
        } catch (e: Exception) {
            error(e)
        }
    }

    suspend fun removeItem(addToCartDto: AddToCartDto): AppResponse<String> {
        return try {
            withContext(coroutineContext) {
                findCart(addToCartDto.cartId) ?: return@withContext DefaultHttpResponse.notFound(
                    error = "Cart with id ${addToCartDto.cartId} was not found"
                )

                cartRepository.removeItem(
                    id = addToCartDto.cartId,
                    e = addToCartDto.items.toDatabase(addToCartDto.cartId)
                )

                DefaultHttpResponse.ok("Removed!")
            }
        } catch (e: Exception) {
            error(e)
        }
    }

    private suspend fun findCart(id: Int): CartDto? {
        return withContext(coroutineContext) {
            cartRepository.findCartById(id).ifEmpty {
                return@withContext null
            }.toDto()
        }
    }
}
