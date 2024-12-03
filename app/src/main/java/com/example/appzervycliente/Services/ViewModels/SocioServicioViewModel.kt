package com.example.appzervycliente.Services.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appzervycliente.DTOs.SocioDTO
import com.example.appzervycliente.Services.Repository.SocioRepository
import kotlinx.coroutines.launch

class SocioServicioViewModel: ViewModel() {

    private val _repo = SocioRepository()

    private val _socios = mutableStateOf<List<SocioDTO>>(emptyList())
    val socios: State<List<SocioDTO>> = _socios

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun obtenerSocios() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try{
                val response = _repo.obtenerSocios()
                if(response.isSuccessful && response.body()?.success == true){
                    _socios.value = response.body()?.data ?: emptyList()
                }else{
                    _errorMessage.value = response.body()?.message ?: "Error al obtener socios"
                }
            }catch (e: Exception){
                _errorMessage.value = e.message ?: "Error de conexión"
            }finally {
                _isLoading.value = false
            }
        }
    }

}