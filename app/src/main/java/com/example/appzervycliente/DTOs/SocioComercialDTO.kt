package com.example.appzervycliente.DTOs

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SocioComercialDTO (
   @Json(name = "idSocioComercialDto") val idSocioComercial: String? = null,
   @Json(name = "nombreComercialDto") val nombreComercial: String = "",
   @Json(name = "celularComercialDto") val celularComercial: String = "",
   @Json(name = "correoComercialDto") val correoComercial: String = "",
   @Json(name = "fotoComercialDto") val fotoComercial: String = "",
   @Json(name = "contraseñaDto") val contraseña: String = "",
   @Json(name = "numeroIntegrantesDto") val numeroIntegrantes: Int = 0,
   @Json(name = "nombreRepresentanteDto") val nombreRepresentante: String = "",
   @Json(name = "fechaNacimientoDto") val fechaNacimiento: String = "",
   @Json(name = "correoDto") val correo: String = "",
   @Json(name = "celularDto") val celular: String = "",
   @Json(name = "tipoDocumentoDto") val tipoDocumento: String = "",
   @Json(name = "documentoLegalDto") val documentoLegal: String = "",
   @Json(name = "estadoSocioDto") val estadoSocio: String = ""
)