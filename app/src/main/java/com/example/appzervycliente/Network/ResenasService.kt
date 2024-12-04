package com.example.appzervycliente.Network

import com.example.appzervycliente.ApiResponse.ApiResponse
import com.example.appzervycliente.DTOs.ReseñasDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ResenasService {

    @GET("reseñas/obtenerReseñasPorSocio/{idSocio}")
    suspend fun obtenerResenasPorIdSocio(@Path("idSocio") idSocio: String):
            Response<ApiResponse<List<ReseñasDTO>>>

}