package com.example.appzervycliente.Network

import com.example.appzervycliente.ApiResponse.ApiResponse
import com.example.appzervycliente.DTOs.SocioComercialDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SocioComercialService {

    @GET("sociosComerciales/obtenerSocioComercialId/{id}")
    suspend fun obtenerSocioPorId(@Path("id") id: String): Response<ApiResponse<SocioComercialDTO>>

}