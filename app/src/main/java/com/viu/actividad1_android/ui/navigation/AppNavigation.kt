package com.viu.actividad1_android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viu.actividad1_android.activities.Screen1
import com.viu.actividad1_android.activities.activity2MVVM.Screen2
import com.viu.actividad1_android.activities.Screen3

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = InterfaceDefinitions.Screen1.route
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
