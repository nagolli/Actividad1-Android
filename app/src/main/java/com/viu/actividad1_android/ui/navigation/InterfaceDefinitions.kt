package com.viu.actividad1_android.ui.navigation

/**
 * Definición centralizada de las rutas de navegación de la aplicación.
 *
 * Cada pantalla se representa como un objeto dentro de esta sealed class,
 * permitiendo una navegación segura y evitando errores por rutas mal escritas.
 *
 * @param route Ruta única asociada a la pantalla.
 */
sealed class InterfaceDefinitions(val route: String) {

    /** Pantalla principal: grid de productos. */
    object ProductGrid : InterfaceDefinitions("product_grid")

    object Screen2 : InterfaceDefinitions("screen_2")

    object Screen3 : InterfaceDefinitions("screen_3")

    /**
     * Ejemplo de pantalla con parámetro.
     */
    object ProductDetail : InterfaceDefinitions("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
}
