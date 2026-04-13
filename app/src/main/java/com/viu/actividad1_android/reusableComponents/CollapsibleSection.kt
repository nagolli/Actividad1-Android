package com.viu.actividad1_android.reusableComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.viu.actividad1_android.R

/**
 * Sección colapsable reutilizable que permite mostrar u ocultar contenido.
 *
 * Este componente es útil para agrupar información secundaria o filtros
 * que el usuario puede expandir o contraer para mejorar la organización visual.
 *
 * @param title Título visible en el encabezado de la sección.
 * @param content Contenido que se mostrará al expandir la sección.
 */
@Composable
fun CollapsibleSection(
    title: String,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {

        // Encabezado clicable
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = stringResource(R.string.filters_title)
            )
        }

        // Contenido colapsable
        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                content()
            }
        }
    }
}
