package com.viu.actividad1_android.data.category

/**
 * Modelo de dominio que representa una categoría dentro de la aplicación.
 *
 * Este modelo es independiente de la estructura del backend. Los datos
 * recibidos desde la API se transforman mediante mapeadores antes de llegar
 * a esta clase, garantizando que la UI y la lógica de dominio permanezcan
 * desacopladas de los DTOs remotos.
 *
 * @param id Identificador único de la categoría.
 * @param name Nombre de la categoría.
 */
data class Category(
    val id: Int,
    val name: String
)
