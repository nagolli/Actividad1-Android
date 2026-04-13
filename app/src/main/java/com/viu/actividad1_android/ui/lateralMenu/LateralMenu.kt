package com.viu.actividad1_android.ui.lateralMenu

import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * Menú lateral de la aplicación.
 * Muestra botones para navegar directamente a cada pantalla.
 * Deprecated -> Usar el TopMenu
 */
@Composable
fun LateralMenu(
    navController: NavController,
    closeDrawer: () -> Unit
) {
    val items = emptyList<MenuItem>(
        //listOf(
        //MenuItem("Úrsula", InterfaceDefinitions.ProductGrid.route),
        //MenuItem("Nacho", InterfaceDefinitions.Screen2.route),
        //MenuItem("Vicente", InterfaceDefinitions.Screen3.route)
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