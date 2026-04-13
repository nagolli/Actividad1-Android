package com.viu.actividad1_android.activities.activity2MVVM

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

//Vista de la pantalla 2, implementando el patron MVVM como ejemplo.
//Muestra un saludo y un botón para navegar al siguiente saludo.
@Composable
fun Screen2(
    navController: NavController,
    viewModel: Screen2ViewModel = viewModel()
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
                route = InterfaceDefinitions.OrderList.route,
                navController = navController
            )
        }
    }
}