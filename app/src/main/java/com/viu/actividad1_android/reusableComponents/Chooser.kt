package com.viu.actividad1_android.reusableComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
    val finalText = if (selectedText != null) "$label: $selectedText" else label

    // Selector visible
    Text(
        text = finalText,
        modifier = Modifier.clickable {
            modalVisible = true
        }
    )

    // Modal
    if (modalVisible) {
        ModalBottomSheet(
            onDismissRequest = { modalVisible = false }
        ) {
            Text(label, Modifier.padding(16.dp))

            ListItem(
                headlineContent = { Text("Cualquiera") },
                modifier = Modifier.clickable {
                    onSelected(null)
                    modalVisible = false
                }
            )

            Divider()

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
