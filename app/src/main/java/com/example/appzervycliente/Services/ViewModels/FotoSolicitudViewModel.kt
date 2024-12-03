package com.example.appzervycliente.Services.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appzervycliente.DTOs.FotoSolicitudDTO
import com.example.appzervycliente.Services.Repository.FotoSolicitudRepository
import kotlinx.coroutines.launch

class FotoSolicitudViewModel: ViewModel() {

    private val _repo = FotoSolicitudRepository()

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun crearFoto(fotoDto: FotoSolicitudDTO){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try{
                val response = _repo.crearFoto(fotoDto)
                if(!response.isSuccessful || response.body()?.success == false){
                    _errorMessage.value = response.body()?.message ?: "Error al crear la propuesta"
                }
            }catch (e: Exception){
                _errorMessage.value = e.message ?: "Error de conexion"
            }finally {
                _isLoading.value = false
            }
        }
    }

}