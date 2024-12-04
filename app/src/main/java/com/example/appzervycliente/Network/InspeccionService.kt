package com.example.appzervycliente.Network

import com.example.appzervycliente.ApiResponse.ApiResponse
import com.example.appzervycliente.DTOs.InspeccionDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface InspeccionService {

    @GET("inspeccion/obtenerInspeccionPorId/{codInspeccion}")
    suspend fun obtenerInspeccionPorId(@Path("codInspeccion") codInspeccion: String):
            Response<ApiResponse<InspeccionDTO>>

}