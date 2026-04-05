package com.viu.actividad1_android.activities.activity2MVVM

//Definir enum de funciones de la vista 2
sealed class Screen2Event {
    object OnNextClicked : Screen2Event()
}