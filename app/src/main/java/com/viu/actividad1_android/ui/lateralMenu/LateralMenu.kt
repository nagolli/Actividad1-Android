package com.viu.actividad1_android.ui.lateralMenu

import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.viu.actividad1_android.ui.navigation.InterfaceDefinitions

/**
 * Menú lateral de la aplicación.
 * Muestra botones para navegar directamente a cada pantalla.
 */
@Composable
fun LateralMenu(
    navController: NavController,
    closeDrawer: () -> Unit
) {
    val items = listOf(
        MenuItem("Úrsula", InterfaceDefinitions.Screen1.route),
        MenuItem("Nacho", InterfaceDefinitions.Screen2.route),
        MenuItem("Vicente", InterfaceDefinitions.Screen3.route)
    )

    ModalDrawerSheet {
        items.forEach { item ->
            NavigationDrawerItem(
                label = { Text(item.title) },
                selected = false,
                onClick = {
                    navController.navigate(item.route)
                    closeDrawer()
                }
            )
        }
    }
}