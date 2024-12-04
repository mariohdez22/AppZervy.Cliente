package com.example.appzervycliente.Services.Repository

import com.example.appzervycliente.InstanceApi.InstanceApi
import com.example.appzervycliente.Network.ResenasService

class ResenasRepository {

    private val apiService: ResenasService = InstanceApi.apiResenas

    suspend fun obtenerResenasPorIdSocio(idSocio: String) = apiService
        .obtenerResenasPorIdSocio(idSocio)

}