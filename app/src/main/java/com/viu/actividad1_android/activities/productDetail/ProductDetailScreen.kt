package com.viu.actividad1_android.activities.productDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.viu.actividad1_android.R
import com.viu.actividad1_android.reusableComponents.RatingStars

/**
 * Pantalla que muestra la información detallada de un producto.
 *
 * @param viewModel ViewModel que gestiona el estado de los detalles.
 * @param onBack Accion para volver a la pantalla anterior.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel,
    onBack: () -> Unit
) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                windowInsets = WindowInsets(0.dp),
                title = {
                    Text(
                        text = if (state.product != null) stringResource(R.string.product_detail_back) else stringResource(R.string.loading),
                        fontSize = 14.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.product_detail_back)
                        )
                    }
                }
            )
        }
    ) { padding ->
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (state.error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.error, color = MaterialTheme.colorScheme.error)
            }
        } else {
            state.product?.let { product ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = product.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Fit
                    )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = product.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "${product.price} ${stringResource(R.string.currency_euro)}",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Calificación con estrellas
                        if (state.averageRating != null) {
                            RatingStars(rating = state.averageRating)
                        } else {
                            Text(
                                text = stringResource(R.string.product_detail_no_reviews),
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Selector de cantidad
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.product_detail_quantity_label),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )

                            IconButton(onClick = { viewModel.onEvent(ProductDetailEvent.OnQuantityChanged(state.quantity - 1)) }) {
                                Icon(Icons.Default.Remove, contentDescription = null)
                            }

                            Text(
                                text = state.quantity.toString(),
                                modifier = Modifier.padding(horizontal = 16.dp),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            IconButton(onClick = { viewModel.onEvent(ProductDetailEvent.OnQuantityChanged(state.quantity + 1)) }) {
                                Icon(Icons.Default.Add, contentDescription = null)
                            }
                        }

                        Button(
                            onClick = { viewModel.onEvent(ProductDetailEvent.OnAddToCart) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = stringResource(R.string.product_detail_add_to_cart))
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = stringResource(R.string.product_detail_description_title),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = product.description ?: stringResource(R.string.not_available),
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Sección de Reseñas
                        Text(
                            text = stringResource(R.string.product_detail_reviews_title),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        if (state.reviews.isEmpty()) {
                            Text(
                                text = stringResource(R.string.product_detail_no_reviews_yet),
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                        } else {
                            state.reviews.forEach { review ->
                                ReviewItem(review = review)
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }