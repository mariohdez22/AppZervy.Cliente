package com.example.appzervycliente.Network

import com.example.appzervycliente.ApiResponse.ApiResponse
import com.example.appzervycliente.DTOs.CategoriaServicioDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoriaServicioApiService {

    @GET("categoriasServicios/obtenerCategoriaServicio")
    suspend fun obtenerCategoriaServicios(): Response<ApiResponse<List<CategoriaServicioDTO>>>

    @POST("categoriasServicios/crearCategoriaServicio")
    suspend fun crearCategoriaServicio(@Body categoria: CategoriaServicioDTO):
            Response<ApiResponse<CategoriaServicioDTO>>
}