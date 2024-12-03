package com.example.appzervycliente.DTOs

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SocioDTO(
   @Json(name = "idSocioDto") val idSocio: String? = null,
   @Json(name = "tipoSocioDto") val tipoSocio: String = "", // "Individual" o "Comercial"
   @Json(name = "idSocioIndividualDto") val idSocioIndividual: String? = null,
   @Json(name = "idSocioComercialDto") val idSocioComercial: String? = null,
   @Json(name = "tipoServicioDto") val tipoServicio: String = "",
   @Json(name = "añosExperienciaDto") val anosExperiencia: String = "",
   @Json(name = "puntajeGeneralDto") val puntajeGeneral: String = ""
)