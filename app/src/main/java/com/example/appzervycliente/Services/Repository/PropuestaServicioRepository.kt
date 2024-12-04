package com.example.appzervycliente.Services.Repository

import com.example.appzervycliente.DTOs.PropuestaServicioDTO
import com.example.appzervycliente.InstanceApi.InstanceApi
import com.example.appzervycliente.Network.PropuestaServicioService

class PropuestaServicioRepository {

    private val apiService: PropuestaServicioService = InstanceApi.apiPropuestaServicio

    suspend fun obtenerPropuestas() = apiService.obtenerPropuestas()

    suspend fun crearPropuesta(propuesta: PropuestaServicioDTO) =
        apiService.crearPropuesta(propuesta)

}