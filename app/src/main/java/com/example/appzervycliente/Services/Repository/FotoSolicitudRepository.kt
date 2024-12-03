package com.example.appzervycliente.Services.Repository

import com.example.appzervycliente.DTOs.FotoSolicitudDTO
import com.example.appzervycliente.InstanceApi.InstanceApi
import com.example.appzervycliente.Network.FotoSolicitudService

class FotoSolicitudRepository {

    private val apiService: FotoSolicitudService = InstanceApi.apiFotoSolicitud

    suspend fun crearFoto(foto: FotoSolicitudDTO) =
        apiService.crearFoto(foto)

}