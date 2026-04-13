package com.viu.actividad1_android.data

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Configuración centralizada de acceso al backend con gestión de estado.
 */
object ApiConfig {

    /** URL base del backend real (10.0.2.2 para el emulador). */
    const val BASE_URL = "http://10.0.2.2:8000/api/"

    /** Endpoint usado para comprobar disponibilidad del backend. */
    private const val HEALTHCHECK_URL = "${BASE_URL}permission"

    /** Estado cacheado de la disponibilidad del backend. Null significa no comprobado. */
    private var isBackendReachable: Boolean? = null

    /** Cliente HTTP con timeout aumentado para backends lentos (WSL2/Sail). */
    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    /**
     * Comprueba si el backend está disponible (con caché).
     */
    fun isBackendAvailable(): Boolean {
        // ... (resto igual)
        isBackendReachable?.let {
            Log.d("ApiConfig", "Usando estado cacheado: $it")
            return it
        }

        Log.d("ApiConfig", "Iniciando comprobación (timeout 10s): $HEALTHCHECK_URL")

        var success = false
        val thread = Thread {
            try {
                val request = Request.Builder().url(HEALTHCHECK_URL).build()
                client.newCall(request).execute().use { response ->
                    success = response.isSuccessful
                    Log.d("ApiConfig", "Respuesta del servidor: ${response.code}")
                }
            } catch (e: Exception) {
                Log.e("ApiConfig", "Error de conexión (${e.javaClass.simpleName}): ${e.message}")
            }
        }

        try {
            thread.start()
            // Esperamos hasta 10 segundos a que el back responda
            thread.join(10000)
        } catch (e: InterruptedException) {
            Log.e("ApiConfig", "Comprobación interrumpida")
        }

        isBackendReachable = success
        return success
    }

    /**
     * Fuerza a que la próxima llamada a isBackendAvailable() realice una petición real.
     */
    fun resetCache() {
        isBackendReachable = null
    }

    /**
     * Crea una instancia de Retrofit para el tipo T.
     */
    inline fun <reified T> createApi(): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }
}
