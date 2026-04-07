package com.viu.actividad1_android.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viu.actividad1_android.activities.productGrid.ProductGrid
import com.viu.actividad1_android.activities.activity2MVVM.Screen2
import com.viu.actividad1_android.activities.Screen3
import com.viu.actividad1_android.ui.topMenu.TopMenu
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

        Scaffold(
            topBar = {
                TopMenu(
                    onChessClick = { navController.navigate(InterfaceDefinitions.ProductGrid.route) },
                    onCartClick = { navController.navigate(InterfaceDefinitions.Screen2.route) },
                    onUserClick = { navController.navigate(InterfaceDefinitions.Screen3.route) }
                )
            }
        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = InterfaceDefinitions.ProductGrid.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(InterfaceDefinitions.ProductGrid.route) {
                    ProductGrid(navController)
                }
                composable(InterfaceDefinitions.Screen2.route) {
                    Screen2(navController)
                }
                composable(InterfaceDefinitions.Screen3.route) {
                    Screen3(navController)
                }
            }
        }
}
