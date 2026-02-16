package com.monochrome.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.monochrome.app.model.CartItem
import com.monochrome.app.model.Product

class MonochromeViewModel : ViewModel() {
    // Cart state
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> = _cartItems

    var isCartOpen by mutableStateOf(false)
        private set

    // Product modal state
    var selectedProduct by mutableStateOf<Product?>(null)
        private set

    var isProductModalOpen by mutableStateOf(false)
        private set

    // Cart functions
    fun addToCart(product: Product) {
        val existingIndex = _cartItems.indexOfFirst { it.product.id == product.id }
        if (existingIndex != -1) {
            val existing = _cartItems[existingIndex]
            _cartItems[existingIndex] = existing.copy(quantity = existing.quantity + 1)
        } else {
            _cartItems.add(CartItem(product, 1))
        }
        isCartOpen = true
    }

    fun removeFromCart(productId: String) {
        _cartItems.removeAll { it.product.id == productId }
    }

    fun updateCartItemQuantity(productId: String, quantity: Int) {
        val index = _cartItems.indexOfFirst { it.product.id == productId }
        if (index != -1) {
            if (quantity <= 0) {
                removeFromCart(productId)
            } else {
                _cartItems[index] = _cartItems[index].copy(quantity = quantity)
            }
        }
    }

    fun toggleCart() {
        isCartOpen = !isCartOpen
    }

    fun closeCart() {
        isCartOpen = false
    }

    fun getCartItemCount(): Int {
        return _cartItems.sumOf { it.quantity }
    }

    fun getCartTotal(): Int {
        return _cartItems.sumOf { it.product.price * it.quantity }
    }

    // Product modal functions
    fun openProductModal(product: Product) {
        selectedProduct = product
        isProductModalOpen = true
    }

    fun closeProductModal() {
        isProductModalOpen = false
    }
}
