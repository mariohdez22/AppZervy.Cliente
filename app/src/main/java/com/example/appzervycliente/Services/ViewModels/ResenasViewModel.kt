package com.example.appzervycliente.Services.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appzervycliente.DTOs.ReseñasDTO
import com.example.appzervycliente.Services.Repository.ResenasRepository
import kotlinx.coroutines.launch

class ResenasViewModel: ViewModel() {

    private val repository = ResenasRepository()

    private val _resenasSocios = mutableStateOf<List<ReseñasDTO>>(emptyList())
    val resenasSocios: State<List<ReseñasDTO>> = _resenasSocios

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun obtenerResenasPorIdSocio(idSocio: String){
        viewModelScope.launch {
            viewModelScope.launch {
                _isLoading.value = true
                _errorMessage.value = null
                try {
                    val response = repository.obtenerResenasPorIdSocio(idSocio)
                    if (response.isSuccessful && response.body()?.success == true) {
                        _resenasSocios.value = response.body()?.data ?: emptyList()
                    } else {
                        _errorMessage.value = response.body()?.message ?: "Reseñas no encontradas"
                    }
                } catch (e: Exception) {
                    _errorMessage.value = e.message ?: "Error de conexión"
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }

}