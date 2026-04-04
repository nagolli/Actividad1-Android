package com.viu.actividad1_android.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.viu.actividad1_android.reusableComponents.Greeting
import com.viu.actividad1_android.reusableComponents.NavButton
import com.viu.actividad1_android.ui.navigation.InterfaceDefinitions

@Composable
fun Screen2(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Greeting(
                name = "Nacho",
                modifier = Modifier.padding(innerPadding)
            )
            NavButton(
                text = "Siguiente",
                route = InterfaceDefinitions.Screen3.route,
                navController = navController
            )
        }
    }
}