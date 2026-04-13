package com.viu.actividad1_android.data

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Configuración centralizada de acceso al backend.
 *
 * Permite comprobar si el backend está disponible y crear instancias
 * de Retrofit para cualquier endpoint, facilitando el uso de datos
 * fake o reales según disponibilidad.
 */
object ApiConfig {

    /** URL base del backend real. */
    const val BASE_URL = "https://10.0.2.2:8443/api/"

    /** Endpoint usado para comprobar disponibilidad del backend. */
    private const val HEALTHCHECK_URL = "${BASE_URL}permission"

    /** Cliente HTTP con timeout reducido para detección rápida. */
    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .build()

    /**
     * Comprueba si el backend está disponible realizando una petición rápida.
     *
     * @return true si el backend responde con éxito, false en caso contrario.
     */
    fun isBackendAvailable(): Boolean {
        Log.d("ApiConfig", "Probando conexión con backend: $HEALTHCHECK_URL")

        val request = Request.Builder()
            .url(HEALTHCHECK_URL)
            .build()

        return try {
            client.newCall(request).execute().use { response ->
                Log.d("ApiConfig", "HTTP ${response.code} - ${response.message}")
                response.isSuccessful
            }
        } catch (e: Exception) {
            Log.e("ApiConfig", "Error conectando al backend: ${e.message}")
            false
        }
    }

    /**
     * Crea una instancia de Retrofit para el tipo T.
     *
     * @param T Interfaz del servicio Retrofit.
     */
    inline fun <reified T> createApi(): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }
}
