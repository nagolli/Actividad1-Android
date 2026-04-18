package com.viu.actividad1_android.activities.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.viu.actividad1_android.data.order.repository.OrderRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    orderRepository: OrderRepository,
    onCancel: () -> Unit,
    onValidate: () -> Unit
) {

    val viewModel: CheckoutViewModel = viewModel(
        factory = CheckoutViewModelFactory(orderRepository)
    )

    val state by viewModel.state.collectAsState()
    var showMessage by remember { mutableStateOf<String?>(null) }

    val fields = listOf(
        Triple("email", "E-mail", state.email),
        Triple("nombre", "Nombre", state.nombre),
        Triple("apellidos", "Apellidos", state.apellidos),
        Triple("telefono", "Teléfono", state.telefono),
        Triple("direccion", "Dirección", state.direccion),
        Triple("codigoPostal", "Código Postal", state.codigoPostal),
        Triple("poblacion", "Población", state.poblacion)
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("Finalizar pedido") }) }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(fields) { field ->
                val (key, label, value) = field

                Column {

                    OutlinedTextField(
                        value = value,
                        onValueChange = {
                            viewModel.updateField(key, it)
                        },
                        label = { Text(label) },
                        isError = state.errors[key] != null,
                        modifier = Modifier.fillMaxWidth()
                    )

                    state.errors[key]?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            item {

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        viewModel.createOrder(
                            onSuccess = { order ->
                                showMessage = "Pedido #${order.id} creado correctamente"
                            },
                            onError = {
                                showMessage = it
                            }
                        )
                    },
                    enabled = viewModel.isValid(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Hacer pedido")
                }

                showMessage?.let {
                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = it,
                        color = if (it.contains("éxito"))
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.error
                    )
                }
                Spacer(Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}