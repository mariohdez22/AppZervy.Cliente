package com.example.appzervycliente.Network

import com.example.appzervycliente.ApiResponse.ApiResponse
import com.example.appzervycliente.DTOs.FotoSolicitudDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FotoSolicitudService {

    @POST("fotoSolicitud/crearFotoSolicitud")
    suspend fun crearFoto(@Body foto: FotoSolicitudDTO):
            Response<ApiResponse<FotoSolicitudDTO>>

}