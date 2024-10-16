package com.example.appzervycliente.Network

import okhttp3.Interceptor
import okhttp3.Response
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Obtener el ID Token de Firebase de manera síncrona
        val idToken = runBlocking {
            FirebaseAuth.getInstance().currentUser?.getIdToken(true)?.await()?.token
        }

        // Si no hay token, procede sin agregar el header
        val newRequest = if (idToken != null) {
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $idToken")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(newRequest)
    }
}
