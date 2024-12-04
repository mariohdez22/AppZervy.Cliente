package com.example.appzervycliente.Services.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appzervycliente.DTOs.SocioComercialDTO
import com.example.appzervycliente.Services.Repository.SocioComercialRepository
import kotlinx.coroutines.launch

class SocioComercialViewModel: ViewModel() {

    private val _repo = SocioComercialRepository()

    private val _socioComercial = mutableStateOf<SocioComercialDTO?>(null)
    val socioComercial: State<SocioComercialDTO?> = _socioComercial

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val _time = mutableLongStateOf(0)
    val time: State<Long> = _time

    fun obtenerSocioIndividualPorId(id: String){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val startTime = System.currentTimeMillis()

                val response = _repo.obtenerSocioPorId(id)
                if (response.isSuccessful && response.body()?.success == true) {
                    _socioComercial.value = response.body()?.data
                } else {
                    _errorMessage.value =
                        response.body()?.message ?: "Socio comercial no encontrado"
                }

                val finalTime = System.currentTimeMillis()
                _time.longValue = finalTime - startTime
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error de conexión"
            } finally {
                _isLoading.value = false
            }
        }

    }

}