package com.example.appzervycliente.DTOs

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReseñasDTO(
   @Json(name = "idReseñas") val idReseñas : String? = null,
   @Json(name = "idInscripccion") val idInscripccion: String ? = null,
   @Json(name = "idSocio") val idSocio: String ? = null,
   @Json(name = "idCliente") val idCliente: String ? = null,
   @Json(name = "tituloReseña") val tituloReseña: String = "",
   @Json(name = "cuerpoReseña") val cuerpoReseña: String = "",
   @Json(name = "fechaReseña") val fechaReseña: String = "",
   @Json(name = "puntuajeReseña") val puntuajeReseña: String = ""
)