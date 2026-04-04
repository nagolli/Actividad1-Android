package com.viu.actividad1_android.reusableComponents

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun NavButton(
    text: String,
    route: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { navController.navigate(route) },
        modifier = modifier
    ) {
        Text(text = text)
    }
}
