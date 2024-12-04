package com.example.appzervycliente.Network

import com.example.appzervycliente.ApiResponse.ApiResponse
import com.example.appzervycliente.DTOs.SocioDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SocioService {

    @GET("socios/obtenerSocios")
    suspend fun obtenerSocios(): Response<ApiResponse<List<SocioDTO>>>

    @GET("socios/obtenerSocioId/{id}")
    suspend fun obtenerSocioPorId(@Path("id") id: String): Response<ApiResponse<SocioDTO>>

}