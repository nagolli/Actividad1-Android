package com.viu.actividad1_android.activities.ordersList

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.viu.actividad1_android.data.order.Order

/**
 * Lista reutilizable que muestra una lista de pedidos en vertical.
 *
 * Cada pedido se representa mediante un [OrderItem]
 *
 * @param products Lista de productos a mostrar.
 */
@Composable
fun OrderList(
    orders: List<Order>
) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(orders) { order ->
            OrderItem(order)
        }
    }
}
