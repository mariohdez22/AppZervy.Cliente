package com.example.appzervycliente.InstanceApi

import com.example.appzervycliente.Network.AuthInterceptor
import com.example.appzervycliente.Network.CategoriaServicioApiService
import com.example.appzervycliente.Network.ClientesApiService
import com.example.appzervycliente.Network.FotoSolicitudService
import com.example.appzervycliente.Network.PropuestaServicioService
import com.example.appzervycliente.Network.SocioService
import com.example.appzervycliente.Network.SolicitudServicioService
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

    val apiPropuestaServicio: PropuestaServicioService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(PropuestaServicioService::class.java)
    }

    val apiSocio: SocioService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(SocioService::class.java)
    }

    val apiSolicitudServicio: SolicitudServicioService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(SolicitudServicioService::class.java)
    }

    val apiFotoSolicitud: FotoSolicitudService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(FotoSolicitudService::class.java)
    }
}
