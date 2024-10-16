package com.example.appzervycliente.Services.Repository

import com.example.appzervycliente.DTOs.CategoriaServicioDTO
import com.example.appzervycliente.InstanceApi.InstanceApi
import com.example.appzervycliente.Network.CategoriaServicioApiService

class CategoriaServicioRepository {

    private val apiService: CategoriaServicioApiService = InstanceApi.apiCategory

    suspend fun obtenerCategoriaServicios() = apiService.obtenerCategoriaServicios()

    suspend fun crearCategoriaServicio(categoriaServicio: CategoriaServicioDTO) = apiService.crearCategoriaServicio(categoriaServicio)
}