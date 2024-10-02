package com.example.appzervycliente.ApiResponse

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    @Json(name = "success") val success: Boolean = false,
    @Json(name = "message") val message: String = "",
    @Json(name = "data") val data: T?,
    @Json(name = "errors") val errors: List<String>?
)