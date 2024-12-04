package com.example.appzervycliente.DTOs

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SocioIndividualDTO(
  @Json(name = "idSocioIndividualDto") val idSocioIndividual: String? = null,
  @Json(name = "nombresDto") val nombres: String = "",
  @Json(name = "celularDto") val celular: String = "",
  @Json(name = "correoDto") val correo: String = "",
  @Json(name = "contraseñaDto") val contraseña: String = "",
  @Json(name = "fotoDto") val foto: String = "",
  @Json(name = "fechaNacimientoDto") val fechaNacimiento: String = "", // Formatear adecuadamente
  @Json(name = "edadDto") val edad: Int = 0,
  @Json(name = "tipoDocumentoDto") val tipoDocumento: String = "",
  @Json(name = "documentoLegalDto") val documentoLegal: String = "",
  @Json(name = "estadoSocioDto") val estadoSocio: String = "" // Ejemplo: "Activo", "Inactivo"
)