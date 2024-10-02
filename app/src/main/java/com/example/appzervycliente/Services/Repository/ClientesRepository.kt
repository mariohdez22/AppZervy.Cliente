package com.example.appzervycliente.Services.Repository

import com.example.appzervycliente.DTOs.ClienteDTO
import com.example.appzervycliente.InstanceApi.InstanceApi
import com.example.appzervycliente.Network.ClientesApiService

class ClientesRepository {

    private val apiService: ClientesApiService = InstanceApi.api

    suspend fun obtenerClientes() = apiService.obtenerClientes()

    suspend fun obtenerClienteId(id: String) = apiService.obtenerClientePorId(id)

    suspend fun crearCliente(cliente: ClienteDTO) = apiService.crearCliente(cliente)

    suspend fun actualizarCliente(cliente: ClienteDTO) = apiService.actualizarCliente(cliente)

    //----------------------------------------------------------------------------------------------

    suspend fun eliminarCliente(id: String) = apiService.eliminarCliente(id)
}