package com.example.appzervycliente.Services.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appzervycliente.DTOs.PropuestaServicioDTO
import com.example.appzervycliente.Services.Repository.PropuestaServicioRepository
import kotlinx.coroutines.launch

class PropuestaServicioViewModel: ViewModel() {

    private val _repo = PropuestaServicioRepository()

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun crearPropuesta(propuestaDto: PropuestaServicioDTO){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try{
                val response = _repo.crearPropuesta(propuestaDto)
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