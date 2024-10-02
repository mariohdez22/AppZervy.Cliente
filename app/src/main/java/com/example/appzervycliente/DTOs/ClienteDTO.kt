package com.example.appzervycliente.DTOs

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClienteDTO (

    @Json(name = "idClienteDto") val idCliente: String? = null,
    @Json(name = "nombresDto") val nombres: String,
    @Json(name = "celularDto") val celular: String? = "",
    @Json(name = "correoDto") val correo: String? = "",
    @Json(name = "contraseñaDto") val contraseña: String? = "",
    @Json(name = "fotoDto") val foto: String? = "",
    @Json(name = "duiDto") val dui: String? = ""
)