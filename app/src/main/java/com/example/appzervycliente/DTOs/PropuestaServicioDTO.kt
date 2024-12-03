package com.example.appzervycliente.DTOs

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PropuestaServicioDTO(
    @Json(name = "idPropuestaDTO") val idPropuesta: String? = null,
    @Json(name = "idSocioDTO") val idSocio: String? = null,
    @Json(name = "idSolicitudDTO") val idSolicitud: String? = null,
    @Json(name = "nombreClienteDTO") val nombreCliente: String = "",
    @Json(name = "tituloCategoriaDTO") val tituloCategoria: String = "",
    @Json(name = "fotoDTO") val foto: String = "",
    @Json(name = "presupuestoDTO") val presupuesto: String? = null,
    @Json(name = "tipoPagoDTO") val tipoPago: String = "",
    @Json(name = "tipoCategoriaDTO") val tipoCategoria: String = "",
    @Json(name = "tituloPropuestaDTO") val tituloPropuesta: String = "",
    @Json(name = "descripcionPropuestaDTO") val descripcionPropuesta: String = "",
    @Json(name = "duracionServicioDTO") val duracionServicio: String = "",
    @Json(name = "estadoPropuestaDTO") val estadoPropuesta: String = "",
    @Json(name = "precioBaseDTO") val precioBase: String = "",
)