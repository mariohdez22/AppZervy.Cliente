package com.example.appzervycliente.DTOs

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SolicitudServicioDTO(
   @Json(name = "idSolicitud") var idSolicitud: String? = null,
   @Json(name = "idCliente") var idCliente: String? = null,
   @Json(name = "idCategoriaServicio") var idCategoriaServicio: String? = null,
   @Json(name = "nombreCliente") var nombreCliente: String = "",
   @Json(name = "foto") var foto: String = "",
   @Json(name = "tituloCategoria") var tituloCategoria: String = "",
   @Json(name = "tipoCategoria") var tipoCategoria: String = "",
   @Json(name = "tituloSolicitud") var tituloSolicitud: String = "",
   @Json(name = "descripcionSolicitud") var descripcionSolicitud: String = "",
   @Json(name = "fechaSolicitud") var fechaSolicitud: String = "",
   @Json(name = "presupuesto") var presupuesto: Double = 0.0,
   @Json(name = "tipoPago") var tipoPago: String = "",
   @Json(name = "estadoSolicitud") var estadoSolicitud: String = ""
)