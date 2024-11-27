package com.example.appzervycliente.Network

import com.example.appzervycliente.ApiResponse.ApiResponse
import com.example.appzervycliente.DTOs.CategoriaServicioDTO
import retrofit2.Response
import retrofit2.http.Body
import com.example.appzervycliente.Services.Repository.DirectionsResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface DirectionsApiService {
    @GET("directions/json")
    suspend fun getDirections(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") apiKey: String
    ): DirectionsResponse
}
