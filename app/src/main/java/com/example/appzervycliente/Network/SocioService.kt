package com.example.appzervycliente.Network

import com.example.appzervycliente.ApiResponse.ApiResponse
import com.example.appzervycliente.DTOs.SocioDTO
import retrofit2.Response
import retrofit2.http.GET

interface SocioService {

    @GET("socios/obtenerSocios")
    suspend fun obtenerSocios(): Response<ApiResponse<List<SocioDTO>>>

}