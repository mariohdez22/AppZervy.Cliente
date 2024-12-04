package com.example.appzervycliente.DTOs

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InspeccionDTO (
   @Json(name = "codInspeccionDTO") val codInspeccionDTO: String? = null,
   @Json(name = "idPropuestaDTO") val idPropuestaDTO: String? = null,
   @Json(name = "estadoInspeccionDTO") val estadoInspeccionDTO: String? = "",
   @Json(name = "tokenInspeccionDTO") val tokenInspeccionDTO: String? = "",
   @Json(name = "duracionInspeccionDTO") val duracionInspeccionDTO: String? = "",
   @Json(name = "horaInicioDTO") val horaInicioDTO: String? = "",
   @Json(name = "horaFinalDTO") val horaFinalDTO: String? = ""
)