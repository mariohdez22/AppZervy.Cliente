package com.example.appzervycliente.Network

import com.example.appzervycliente.ApiResponse.ApiResponse
import com.example.appzervycliente.DTOs.ClienteDTO
import retrofit2.http.*
import retrofit2.Response

interface ClientesApiService {

    @GET("clientes/obtenerClientes")
    suspend fun obtenerClientes(): Response<ApiResponse<List<ClienteDTO>>>

    @GET("clientes/obtenerClienteId/{id}")
    suspend fun obtenerClientePorId(@Path("id") id: String): Response<ApiResponse<ClienteDTO>>

    @POST("clientes/crearCliente")
    suspend fun crearCliente(@Body cliente: ClienteDTO): Response<ApiResponse<ClienteDTO>>

    @PUT("clientes/actualizarCliente")
    suspend fun actualizarCliente(@Body cliente: ClienteDTO): Response<ApiResponse<ClienteDTO>>

    //----------------------------------------------------------------------------------------------

    @DELETE("clientes/eliminarCliente/{id}")
    suspend fun eliminarCliente(@Path("id") id: String): Response<ApiResponse<Unit>>
}