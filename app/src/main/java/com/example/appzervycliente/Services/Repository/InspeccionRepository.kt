package com.example.appzervycliente.Services.Repository

import com.example.appzervycliente.InstanceApi.InstanceApi
import com.example.appzervycliente.Network.InspeccionService

class InspeccionRepository {

    private val apiService: InspeccionService = InstanceApi.apiInspeccion

    suspend fun obtenerInspeccionPorId(codInspeccion: String) = apiService
        .obtenerInspeccionPorId(codInspeccion)

}