package com.example.appzervycliente.Network

import com.example.appzervycliente.ApiResponse.ApiResponse
import com.example.appzervycliente.DTOs.SocioIndividualDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SocioIndividualService {

    @GET("sociosIndividuales/obtenerSocioIndividualId/{id}")
    suspend fun obtenerSocioPorId(@Path("id") id: String): Response<ApiResponse<SocioIndividualDTO>>

}