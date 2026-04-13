package com.viu.actividad1_android.activities.ordersList

/**
 * Representa los eventos que pueden ocurrir en la pantalla de Order List.
 *
 * Estos eventos son procesados por el ViewModel para cargar datos.
 */
sealed class OrderListEvent {

    /** Solicita la carga inicial de productos. */
    object LoadOrders : OrderListEvent()
}
