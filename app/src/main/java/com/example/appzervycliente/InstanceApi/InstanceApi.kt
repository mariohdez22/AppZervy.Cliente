package com.example.appzervycliente.InstanceApi

import com.example.appzervycliente.Network.AuthInterceptor
import com.example.appzervycliente.Network.CategoriaServicioApiService
import com.example.appzervycliente.Network.ClientesApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object InstanceApi {

    private const val BASE_URL = "http://10.0.2.2:8080/" // Ajusta la URL si es necesario

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val apiClient: ClientesApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(ClientesApiService::class.java)
    }

    val apiCategory: CategoriaServicioApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(CategoriaServicioApiService::class.java)
    }
}
