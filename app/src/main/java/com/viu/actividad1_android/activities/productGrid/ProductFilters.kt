package com.viu.actividad1_android.activities.productGrid

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

@Composable
fun ProductFilters(
    filter: ProductFilterDto,
    onFilterChange: (ProductFilterDto) -> Unit,
    onApply: () -> Unit
) {
    Column {
        TextField(
            value = filter.name ?: "",
            onValueChange = { onFilterChange(filter.copy(name = it)) },
            label = { Text("Nombre") }
        )

        // categoría, proveedor, sliders, etc.

        Button(onClick = onApply) {
            Text("Aplicar filtros")
        }
    }
}
