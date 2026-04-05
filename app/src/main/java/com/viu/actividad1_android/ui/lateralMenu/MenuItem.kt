package com.viu.actividad1_android.ui.lateralMenu

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val title: String,
    val route: String,
    val icon: ImageVector? = null
)