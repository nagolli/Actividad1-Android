package com.viu.actividad1_android.data

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val client = OkHttpClient.Builder()
    .connectTimeout(1, TimeUnit.SECONDS)
    .readTimeout(1, TimeUnit.SECONDS)
    .build()

fun isBackendAvailable(): Boolean {
    val url = "https://10.0.2.2:8443/api/permission"

    println("🔍 [CHECK] Probando conexión con backend: $url")

    val request = Request.Builder()
        .url(url)
        .build()

    return try {
        client.newCall(request).execute().use { response ->
            println("📡 [CHECK] Código HTTP: ${response.code}")
            println("📡 [CHECK] isSuccessful: ${response.isSuccessful}")
            println("📡 [CHECK] Mensaje: ${response.message}")

            response.isSuccessful
        }
    } catch (e: Exception) {
        println("❌ [CHECK] Error conectando al backend: ${e.message}")
        false
    }
}


inline fun <reified T> getEndpoint(): T {
    return createApi("https://10.0.2.2:8443/api/")
}

inline fun <reified T> createApi(baseUrl: String): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(T::class.java)
}