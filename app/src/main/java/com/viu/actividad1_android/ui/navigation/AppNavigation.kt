package com.viu.actividad1_android.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viu.actividad1_android.activities.Screen1
import com.viu.actividad1_android.activities.activity2MVVM.Screen2
import com.viu.actividad1_android.activities.Screen3
import com.viu.actividad1_android.ui.lateralMenu.LateralMenu
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            LateralMenu(
                navController = navController,
                closeDrawer = { scope.launch { drawerState.close() } }
            )
        }
    ) {
        Scaffold(
            //Si queremos agregar menu superior
            topBar = {   }
        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = InterfaceDefinitions.Screen1.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(InterfaceDefinitions.Screen1.route) {
                    Screen1(navController)
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
}
