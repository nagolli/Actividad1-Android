package com.viu.actividad1_android.activities.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.viu.actividad1_android.R
import kotlinx.coroutines.launch
import java.text.NumberFormat

@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onCheckout: () -> Unit
) {

    val state = viewModel.state.value
    val scope = rememberCoroutineScope()

    if (state.items.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(R.string.cart_empty))
        }
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // LISTA DE PRODUCTOS
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.items) { item ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        AsyncImage(
                            model = item.imageUrl,
                            contentDescription = item.name,
                            modifier = Modifier
                                .size(80.dp)
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(text = stringResource(R.string.quantity, item.quantity))
                            Text(text = stringResource(R.string.price, item.price))

                            val subtotal = item.price * item.quantity
                            Text(
                                text = stringResource(R.string.subtotal, subtotal),
                                style = MaterialTheme.typography.labelMedium
                            )
                        }

                        Button(
                            onClick = { viewModel.removeItem(item.id) }
                        ) {
                            Text(text = stringResource(R.string.remove))
                        }
                    }
                }
            }
        }

        // TOTAL + ACCIONES
        Surface(
            tonalElevation = 3.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                val total = state.items.sumOf { it.price * it.quantity }
                val formattedTotal = NumberFormat.getCurrencyInstance().format(total)

                Text(
                    text = stringResource(R.string.total, formattedTotal),
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    OutlinedButton(
                        onClick = { viewModel.clearCart() }
                    ) {
                        Text(text = stringResource(R.string.clear))
                    }

                    Button(
                    onClick = {
                        scope.launch {
                            viewModel.placeOrder()
                            onCheckout()
                        }
                    }
                    ) {
                        Text(text = stringResource(R.string.place_order))
                    }
                }
            }
        }
    }
}