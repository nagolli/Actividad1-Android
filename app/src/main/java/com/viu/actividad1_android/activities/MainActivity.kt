package com.viu.actividad1_android.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.viu.actividad1_android.ui.navigation.AppNavigation
import com.viu.actividad1_android.ui.theme.Actividad1AndroidTheme

/**
 * Actividad principal de la aplicación.
 *
 * Configura el tema visual y delega la gestión de pantallas al componente
 * de navegación [AppNavigation]. Esta actividad actúa como punto de entrada
 * de la aplicación siguiendo la arquitectura recomendada por Android.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("MainActivity", "Inicio actividad")

        enableEdgeToEdge()

        setContent {
            Actividad1AndroidTheme {
                AppNavigation()
            }
        }
    }
}
