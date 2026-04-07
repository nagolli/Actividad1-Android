package com.viu.actividad1_android.activities.productGrid

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.viu.actividad1_android.reusableComponents.Greeting
import com.viu.actividad1_android.reusableComponents.NavButton
import com.viu.actividad1_android.ui.navigation.InterfaceDefinitions

//Vista del grid de productos, implementando el patron MVVM.
//Primero presenta unos filtros y luego el grid de productos filtrados.
@Composable
fun ProductGrid(
    navController: NavController,
    viewModel: ProductGridViewModel = viewModel()
) {
    //Importar el estado de la vista
    val state = viewModel.state.value

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        //Modo de organización del contenedor
        Column(modifier = Modifier.padding(innerPadding)) {
            //Componentes reutilizables
            Greeting(
                name = state.name
            )
            NavButton(
                text = "Siguiente",
                route = InterfaceDefinitions.Screen2.route,
                navController = navController
            )
        }
    }
}