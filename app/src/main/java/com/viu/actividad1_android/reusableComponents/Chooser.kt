package com.viu.actividad1_android.reusableComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.viu.actividad1_android.R

/**
 * Componente reutilizable que permite seleccionar un elemento de una lista
 * mediante un modal inferior (BottomSheet).
 *
 * @param label Texto que describe el tipo de selección (ej. "Categoría").
 * @param selected ID actualmente seleccionado, o null si no hay selección.
 * @param options Lista de opciones disponibles en formato (id, nombre).
 * @param onSelected Callback que devuelve la opción seleccionada o null.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chooser(
    label: String,
    selected: Int?,
    options: List<Pair<Int, String>>,
    onSelected: (Pair<Int, String>?) -> Unit
) {
    var modalVisible by remember { mutableStateOf(false) }

    val selectedText = options.firstOrNull { it.first == selected }?.second
    val finalText = selectedText?.let { "$label: $it" } ?: label

    // Selector visible
    Text(
        text = finalText,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .clickable { modalVisible = true }
            .padding(vertical = 8.dp)
    )

    // Modal con opciones
    if (modalVisible) {
        ModalBottomSheet(
            onDismissRequest = { modalVisible = false }
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            // Opción "Cualquiera"
            ListItem(
                headlineContent = { Text(stringResource(R.string.option_any)) },
                modifier = Modifier.clickable {
                    onSelected(null)
                    modalVisible = false
                }
            )

            Divider()

            // Opciones reales
            options.forEach { option ->
                ListItem(
                    headlineContent = { Text(option.second) },
                    modifier = Modifier.clickable {
                        onSelected(option)
                        modalVisible = false
                    }
                )
            }
        }
    }
}
