package com.example.appzervycliente.DTOs

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriaServicioDTO(

    @Json(name = "idCategoriaServicio") val idCategoriaServicio: String? = null,
    @Json(name = "foto") val foto: String,
    @Json(name = "tituloCategoria") val tituloCategoria: String,
    @Json(name = "descripcion") val descripcion: String? = "",
    @Json(name = "horarioServicio") val horarioServicio: String?,
    @Json(name = "tipoCategoria") val tipoCategoria: String?,
)
