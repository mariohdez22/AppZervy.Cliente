package com.example.appzervycliente.Services.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appzervycliente.DTOs.InspeccionDTO
import com.example.appzervycliente.Services.Repository.InspeccionRepository
import kotlinx.coroutines.launch

class InspeccionViewModel: ViewModel() {

    private val repository = InspeccionRepository()

    private val _inspeccion = mutableStateOf<InspeccionDTO?>(null)
    val inspeccion: State<InspeccionDTO?> = _inspeccion

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun obtenerInspeccionPorId(idInspeccion: String){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = repository.obtenerInspeccionPorId(idInspeccion)
                if (response.isSuccessful && response.body()?.success == true) {
                    _inspeccion.value = response.body()?.data
                } else {
                    _errorMessage.value = response.body()?.message ?: "Cliente no encontrado"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error de conexión"
            } finally {
                _isLoading.value = false
            }
        }
    }

}