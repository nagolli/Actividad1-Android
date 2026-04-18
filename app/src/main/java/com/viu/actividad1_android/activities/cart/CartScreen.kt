package com.viu.actividad1_android.activities.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onCheckout: () -> Unit
) {

    val state = viewModel.state.value

    if (state.items.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Tu carrito está vacío")
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
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text("Cantidad: ${item.quantity}")
                            Text("Precio: ${item.price}€")

                            Text(
                                text = "Subtotal: ${item.price * item.quantity}€",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }

                        Button(
                            onClick = { viewModel.removeItem(item.id) }
                        ) {
                            Text("Quitar")
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

                Text(
                    text = "Total: ${
                        state.items.sumOf { it.price * it.quantity }
                    }€",
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
                        Text("Vaciar")
                    }

                    Button(
                        onClick = onCheckout
                    ) {
                        Text("Hacer pedido")
                    }
                }
            }
        }
    }
}