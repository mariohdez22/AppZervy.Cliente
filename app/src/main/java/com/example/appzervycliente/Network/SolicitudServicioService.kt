package com.example.appzervycliente.Network

import com.example.appzervycliente.ApiResponse.ApiResponse
import com.example.appzervycliente.DTOs.SolicitudServicioDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SolicitudServicioService {
    @POST("solicitudServicio/crearSolicitudServicio")
    suspend fun crearSolicitud(@Body solicitud: SolicitudServicioDTO):
            Response<ApiResponse<SolicitudServicioDTO>>
}