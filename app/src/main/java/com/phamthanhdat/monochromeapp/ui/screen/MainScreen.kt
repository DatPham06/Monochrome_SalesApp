package com.monochrome.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monochrome.app.data.Constants
import com.monochrome.app.ui.components.*
import com.monochrome.app.viewmodel.MonochromeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MonochromeViewModel) {
    Scaffold(
        topBar = {
            HeaderBar(
                cartItemCount = viewModel.getCartItemCount(),
                onCartClick = { viewModel.toggleCart() }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Hero Section
                item {
                    HeroSection()
                }

                // Products Title
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Divider(
                            modifier = Modifier.width(48.dp),
                            thickness = 1.dp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Curated Objects",
                            fontSize = 28.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "A selection of items defined by texture, form, and the absence of color.",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 32.dp)
                        )
                    }
                }

                // Products Grid
                item {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1200.dp)
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        userScrollEnabled = false
                    ) {
                        items(Constants.PRODUCTS) { product ->
                            ProductCard(
                                product = product,
                                onClick = { viewModel.openProductModal(product) }
                            )
                        }
                    }
                }

                // Newsletter Section
                item {
                    NewsletterSection()
                }

                // Footer
                item {
                    FooterSection()
                }
            }

            // Floating Components
            if (viewModel.isProductModalOpen && viewModel.selectedProduct != null) {
                ProductModal(
                    product = viewModel.selectedProduct!!,
                    onDismiss = { viewModel.closeProductModal() },
                    onAddToCart = {
                        viewModel.addToCart(it)
                        viewModel.closeProductModal()
                    }
                )
            }

            if (viewModel.isCartOpen) {
                CartDrawer(
                    items = viewModel.cartItems,
                    onDismiss = { viewModel.closeCart() },
                    onRemove = { viewModel.removeFromCart(it) },
                    onUpdateQuantity = { id, qty -> viewModel.updateCartItemQuantity(id, qty) },
                    total = viewModel.getCartTotal()
                )
            }
        }
    }
}
