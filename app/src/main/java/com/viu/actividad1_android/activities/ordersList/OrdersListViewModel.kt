package com.viu.actividad1_android.activities.ordersList

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.viu.actividad1_android.data.order.repository.OrderRepository
import kotlinx.coroutines.launch

/**
 * ViewModel de la pantalla Order List.
 *
 * Gestiona el estado [OrderListState], procesa los eventos [OrderListEvent]
 * y coordina la carga de datos desde los repositorios siguiendo el patrón MVVM.
 *
 * @param OrderRepository Repositorio de pedidos.
 */
class OrderListViewModel(
    private val ordersRepository: OrderRepository
) : ViewModel() {

    /** Estado observable de la pantalla. */
    var state by mutableStateOf(OrderListState())
        private set

    init {
        onEvent(OrderListEvent.LoadOrders)
    }

    /**
     * Procesa los eventos enviados desde la UI.
     */
    fun onEvent(event: OrderListEvent) {
        when (event) {

            OrderListEvent.LoadOrders -> loadOrders()
        }
    }

    /**
     * Carga la lista completa de pedidos desde el repositorio.
     */
    private fun loadOrders() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val orders = ordersRepository.getOrders()

                state = state.copy(
                    orders = orders,
                    isLoading = false
                )

                Log.d("OrderListVM", "Pedidos cargadas: ${orders.size}")

            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
                Log.e("OrderListVM", "Error cargando pedidos", e)
            }
        }
    }
}

/**
 * Factory para crear instancias de [OrderListViewModel] con parámetros.
 */
class OrderListViewModelFactory(
    private val orders: OrderRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderListViewModel::class.java)) {
            return OrderListViewModel(
                orders
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
