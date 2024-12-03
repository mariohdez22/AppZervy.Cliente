package com.example.appzervycliente.DTOs

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FotoSolicitudDTO(
   @Json(name = "idFotoSolicitud") var idFotoSolicitud: String? = null,
   @Json(name = "idSolicitud") var idSolicitud: String? = null,
   @Json(name = "foto") var foto: String = "",
   @Json(name = "descripcionFoto") var descripcionFoto: String = ""
)