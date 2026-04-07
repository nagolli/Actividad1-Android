package com.viu.actividad1_android.ui.navigation

sealed class InterfaceDefinitions(val route: String) {
    object ProductGrid : InterfaceDefinitions("ProductGrid")
    object Screen2 : InterfaceDefinitions("s2")
    object Screen3 : InterfaceDefinitions("s3")
}