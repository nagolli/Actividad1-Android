package com.viu.actividad1_android.activities.ordersList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.viu.actividad1_android.R

/**
 * Pantalla de la lista de pedidos.
 *
 * Implementa el patrón MVVM la lista de pedidos del usuario.
 *
 * @param viewModel ViewModel asociado a la pantalla.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderListScreen(
    viewModel: OrderListViewModel = viewModel()
) {
    val state = viewModel.state

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = stringResource(R.string.orders_title),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        OrderList(
            orders = state.orders,
        )
    }
}
