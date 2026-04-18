package com.viu.actividad1_android.activities.checkout

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.viu.actividad1_android.data.order.Order
import com.viu.actividad1_android.data.order.repository.OrderRepository

class CheckoutViewModel (private val orderRepository: OrderRepository): ViewModel() {

    private val _state = MutableStateFlow(CheckoutUiState())
    val state: StateFlow<CheckoutUiState> = _state

    fun updateField(field: String, value: String) {
        _state.update { current ->
            val newState = when (field) {
                "email" -> current.copy(email = value)
                "nombre" -> current.copy(nombre = value)
                "apellidos" -> current.copy(apellidos = value)
                "telefono" -> current.copy(telefono = value)
                "codigoPostal" -> current.copy(codigoPostal = value)
                "direccion" -> current.copy(direccion = value)
                "poblacion" -> current.copy(poblacion = value)
                else -> current
            }

            newState.copy(errors = validate(newState))
        }
    }

    fun isValid(): Boolean {
        return _state.value.errors.isEmpty()
    }

    fun createOrder(
        onSuccess: (Order) -> Unit,
        onError: (String) -> Unit
    ) {
        val current = _state.value
        val errors = validate(current)

        if (errors.isNotEmpty()) {
            _state.update { it.copy(errors = errors) }
            onError("Revisa el formulario")
            return
        }

        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true) }

                val order = orderRepository.placeOrder(
                    products = current.cartItems,
                    email = current.email
                )

                _state.update { it.copy(isLoading = false) }

                onSuccess(order)

            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false) }
                onError("Error al crear el pedido")
            }
        }
    }

    private suspend fun fakeApiCall(state: CheckoutUiState) {
        kotlinx.coroutines.delay(1000) // simulamos red
    }

    private fun validate(state: CheckoutUiState): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        if (!state.email.contains("@")) errors["email"] = "Email inválido"
        if (state.nombre.isBlank()) errors["nombre"] = "Obligatorio"
        if (state.telefono.length < 9) errors["telefono"] = "Teléfono inválido"
        if (state.codigoPostal.length < 5) errors["codigoPostal"] = "CP inválido"
        if (state.direccion.isBlank()) errors["direccion"] = "Obligatorio"
        if (state.poblacion.isBlank()) errors["poblacion"] = "Obligatorio"

        return errors
    }
}
class CheckoutViewModelFactory(
    private val orderRepository: OrderRepository
) : androidx.lifecycle.ViewModelProvider.Factory {

    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckoutViewModel::class.java)) {
            return CheckoutViewModel(orderRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}