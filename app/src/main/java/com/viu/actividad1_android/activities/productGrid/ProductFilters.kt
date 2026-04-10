package com.viu.actividad1_android.activities.productGrid

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AssistChip
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
                .clickable { expanded = !expanded }
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
    categories: List<String>,
    suppliers: List<String>,
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
        DropdownSelector(
            label = "Categoría",
            options = categories,
            selected = filter.category,
            onSelected = { onFilterChange(filter.copy(category = it)) }
        )

        // Proveedor
        DropdownSelector(
            label = "Proveedor",
            options = suppliers,
            selected = filter.supplier,
            onSelected = { onFilterChange(filter.copy(supplier = it)) }
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


@Composable
fun PriceRangeChip(
    min: Double?,
    max: Double?,
    maxPrice: Float
) {
    val minValue = min?.toInt() ?: 0
    val maxValue = max?.toInt() ?: maxPrice.toInt()

    AssistChip(
        onClick = {},
        label = {
            Text("$minValue€ - $maxValue€")
        }
    )
}


@Composable
fun DropdownSelector(
    label: String,
    options: List<String>,
    selected: String?,
    onSelected: (String?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = selected ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Cualquiera") },
                onClick = {
                    onSelected(null)
                    expanded = false
                }
            )
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
