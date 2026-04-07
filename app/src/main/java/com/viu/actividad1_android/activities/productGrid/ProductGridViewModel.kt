package com.viu.actividad1_android.activities.productGrid

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

//Modelo de ProductGrid, implementando el patron MVVM como ejemplo.
//Define e importa estado de ProductGridState, define las funciones de ProductGridEvent.
class ProductGridViewModel : ViewModel() {

    private val _state = mutableStateOf(ProductGridState())
    val state: State<ProductGridState> = _state

    //Funciones de la vista (Ejemplo)
    /*fun onEvent(event: ProductGridEvent) {
        when (event) {
            //ProductGridEvent.OnNextClicked -> {
                //Aqui hacer algo
            //}
        }
    }*/
}