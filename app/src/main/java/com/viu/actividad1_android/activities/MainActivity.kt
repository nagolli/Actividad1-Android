package com.viu.actividad1_android.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.viu.actividad1_android.ui.navigation.AppNavigation
import com.viu.actividad1_android.ui.theme.Actividad1AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Actividad1AndroidTheme {
                AppNavigation()
            }
        }
    }
}