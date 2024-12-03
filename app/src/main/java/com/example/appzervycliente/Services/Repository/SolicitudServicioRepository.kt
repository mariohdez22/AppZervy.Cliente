package com.example.appzervycliente.Services.Repository

import com.example.appzervycliente.DTOs.SolicitudServicioDTO
import com.example.appzervycliente.InstanceApi.InstanceApi
import com.example.appzervycliente.Network.SolicitudServicioService


class SolicitudServicioRepository {

    private val apiService: SolicitudServicioService = InstanceApi.apiSolicitudServicio

    suspend fun crearSolicitud(solicitud: SolicitudServicioDTO) =
        apiService.crearSolicitud(solicitud)

}