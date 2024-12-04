package com.example.appzervycliente.Network

import com.example.appzervycliente.ApiResponse.ApiResponse
import com.example.appzervycliente.DTOs.PropuestaServicioDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PropuestaServicioService {

    @GET("propuestas/obtenerPropuestas")
    suspend fun obtenerPropuestas(): Response<ApiResponse<List<PropuestaServicioDTO>>>

    @POST("propuestas/crearPropuesta")
    suspend fun crearPropuesta(@Body propuesta: PropuestaServicioDTO):
            Response<ApiResponse<PropuestaServicioDTO>>


}