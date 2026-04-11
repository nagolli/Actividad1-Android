package com.viu.actividad1_android.activities.productGrid

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.viu.actividad1_android.reusableComponents.Chooser

@Composable
fun CollapsibleFilters(
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = !expanded
                }
                .padding(16.dp),
        ) {
            Text(
                text = "Filtros",
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }

        // Contenido colapsable
        AnimatedVisibility(visible = expanded) {
            Column(Modifier.padding(horizontal = 16.dp)) {
                content()
            }
        }
    }
}


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

        // Nombre
        TextField(
            value = filter.name.orEmpty(),
            onValueChange = { onFilterChange(filter.copy(name = it)) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        // Categoría
        Chooser(
            label = "Categoría",
            selected = filter.category,
            options = categories,
            onSelected = { onFilterChange(filter.copy(category = it?.first)) }
        )

        // Proveedor
        Chooser(
            label = "Proveedor",
            selected = filter.supplier,
            options = suppliers,
            onSelected = { onFilterChange(filter.copy(supplier = it?.first)) }
        )


        // Precio
        Text("Precio (euros)")

        // Rango actual desde el filtro
        val currentRange = (filter.min?.toFloat() ?: 0f)..(filter.max?.toFloat() ?: maxPrice)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Valor mínimo
            Text(
                text = "${currentRange.start.toInt()}€",
                modifier = Modifier.padding(end = 8.dp)
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

            // Valor máximo
            Text(
                text = "${currentRange.endInclusive.toInt()}€",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}