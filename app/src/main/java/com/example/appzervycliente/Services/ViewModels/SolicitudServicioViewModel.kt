package com.example.appzervycliente.Services.ViewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appzervycliente.DTOs.SolicitudServicioDTO
import com.example.appzervycliente.Services.Repository.SolicitudServicioRepository
import kotlinx.coroutines.launch

class SolicitudServicioViewModel: ViewModel() {

    private val _repo = SolicitudServicioRepository()

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val _isSuccessfull = mutableStateOf(false)
    val isSuccessfull: State<Boolean> = _isSuccessfull

    private val _isFailed = mutableStateOf(false)
    val isFailed: State<Boolean> = _isFailed

    fun crearSolicitud(solicitud: SolicitudServicioDTO){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try{
                val response = _repo.crearSolicitud(solicitud)
                if(response.isSuccessful && response.body()?.success == true){
                    _isSuccessfull.value = true
                }else{
                    _isFailed.value = true
                    _errorMessage.value = response.body()?.message ?: "Error al crear la propuesta"
                }
            }catch (e: Exception){
                _isFailed.value = true
                _errorMessage.value = e.message ?: "Error de conexion"
            }finally {
                _isLoading.value = false
            }
        }
    }

    fun restartViewModel(){
        _isSuccessfull.value = false
        _isFailed.value = false
    }
}