package com.viu.actividad1_android.activities.ordersList

import com.viu.actividad1_android.data.order.Order

/**
 * Estado de la pantalla Order List.
 *
 * Representa toda la información necesaria para renderizar la vista,
 * incluyendo la lista de pedidos y estados de carga o error.
 *
 * Este estado es gestionado por el ViewModel siguiendo el patrón MVVM.
 *
 * @param orders Lista de pedidos obtenidos desde el repositorio.
 * @param isLoading Indica si se está realizando una operación de carga.
 * @param error Mensaje de error en caso de fallo en la carga de datos.
 */
data class OrderListState(
    val orders: List<Order> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
