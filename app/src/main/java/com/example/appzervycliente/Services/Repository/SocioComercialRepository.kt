package com.example.appzervycliente.Services.Repository

import com.example.appzervycliente.InstanceApi.InstanceApi
import com.example.appzervycliente.Network.SocioComercialService

class SocioComercialRepository {

    private val apiService: SocioComercialService = InstanceApi.apiSocioComercial

    suspend fun obtenerSocioPorId(id: String) = apiService.obtenerSocioPorId(id)

}