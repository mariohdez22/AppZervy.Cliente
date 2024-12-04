package com.example.appzervycliente.Services.Repository

import com.example.appzervycliente.InstanceApi.InstanceApi
import com.example.appzervycliente.Network.SocioIndividualService

class SocioIndividualRepository {

    private val apiService: SocioIndividualService = InstanceApi.apiSocioIndividual

    suspend fun obtenerSocioPorId(id: String) = apiService.obtenerSocioPorId(id)

}