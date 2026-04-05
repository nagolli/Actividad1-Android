package com.viu.actividad1_android.activities.activity2MVVM

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

//Modelo de la screen2, implementando el patron MVVM como ejemplo.
//Define e importa estado de Screen2State, define las funciones de Screen2Event.
class Screen2ViewModel : ViewModel() {

    private val _state = mutableStateOf(Screen2State())
    val state: State<Screen2State> = _state

    //Funciones de la vista (Ejemplo)
    fun onEvent(event: Screen2Event) {
        when (event) {
            Screen2Event.OnNextClicked -> {
                //Aqui hacer algo
            }
        }
    }
}