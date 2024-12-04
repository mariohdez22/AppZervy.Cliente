package com.example.appzervycliente.Services.Repository

import com.example.appzervycliente.InstanceApi.InstanceApi
import com.example.appzervycliente.Network.SocioService

class SocioRepository {

    private val apiService: SocioService = InstanceApi.apiSocio

    suspend fun obtenerSocios() = apiService.obtenerSocios()

    suspend fun obtenerSocioPorId(id: String) = apiService.obtenerSocioPorId(id)

}