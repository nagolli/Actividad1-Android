package com.viu.actividad1_android.activities.productGrid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.viu.actividad1_android.R
import com.viu.actividad1_android.reusableComponents.Chooser
/**
 * Componente que muestra los filtros disponibles para la búsqueda de productos.
 *
 * Incluye filtros por nombre, categoría, proveedor y rango de precio.
 * Cada cambio en un filtro actualiza el estado mediante [onFilterChange].
 *
 * @param filter Estado actual de los filtros.
 * @param categories Lista de categorías disponibles (id, nombre).
 * @param suppliers Lista de proveedores disponibles (id, nombre).
 * @param onFilterChange Callback que notifica cambios en los filtros.
 * @param maxPrice Precio máximo permitido para el slider.
 */
@Composable
fun ProductFilters(
    filter: ProductFilterDto,
    categories: List<Pair<Int, String>>,
    suppliers: List<Pair<Int, String>>,
    onFilterChange: (ProductFilterDto) -> Unit,
    maxPrice: Float
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Filtro por nombre
        TextField(
            value = filter.name.orEmpty(),
            onValueChange = { onFilterChange(filter.copy(name = it)) },
            label = { Text(stringResource(R.string.filter_name_label)) },
            modifier = Modifier.fillMaxWidth()
        )

        // Filtro por categoría
        Chooser(
            label = (stringResource(R.string.filter_category_label)),
            selected = filter.category,
            options = categories,
            onSelected = { selected ->
                onFilterChange(filter.copy(category = selected?.first))
            }
        )

        // Filtro por proveedor
        Chooser(
            label = (stringResource(R.string.filter_supplier_label)),
            selected = filter.supplier,
            options = suppliers,
            onSelected = { selected ->
                onFilterChange(filter.copy(supplier = selected?.first))
            }
        )

        // Filtro por precio
        Text(
            text = stringResource(R.string.filter_price_label),
            style = MaterialTheme.typography.titleSmall
        )

        val currentRange = (filter.min?.toFloat() ?: 0f)..(filter.max?.toFloat() ?: maxPrice)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "${currentRange.start.toInt()}${stringResource(R.string.currency_euro)}",
                modifier = Modifier.padding(end = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            RangeSlider(
                value = currentRange,
                onValueChange = { newRange ->
                    onFilterChange(
                        filter.copy(
                            min = newRange.start.toDouble(),
                            max = newRange.endInclusive.toDouble()
                        )
                    )
                },
                valueRange = 0f..maxPrice,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "${currentRange.endInclusive.toInt()}${stringResource(R.string.currency_euro)}",
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}