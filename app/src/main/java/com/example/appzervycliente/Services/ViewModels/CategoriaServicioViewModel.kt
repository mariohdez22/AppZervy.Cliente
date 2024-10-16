package com.example.appzervycliente.Services.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appzervycliente.DTOs.CategoriaServicioDTO
import com.example.appzervycliente.DTOs.ClienteDTO
import com.example.appzervycliente.Services.Repository.CategoriaServicioRepository
import kotlinx.coroutines.launch

class CategoriaServicioViewModel : ViewModel() {

    private val repository = CategoriaServicioRepository()

    private val _categorias = mutableStateOf<List<CategoriaServicioDTO>>(emptyList())
    val categorias: State<List<CategoriaServicioDTO>> = _categorias

    private val _categoria = mutableStateOf<CategoriaServicioDTO?>(null)
    val categoria: State<CategoriaServicioDTO?> = _categoria

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun obtenerCategoriaServicios() {

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = repository.obtenerCategoriaServicios()
                if (response.isSuccessful && response.body()?.success == true) {
                    _categorias.value = response.body()?.data ?: emptyList()
                } else {
                    _errorMessage.value = response.body()?.message ?: "Error al obtener las categorias"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error de conexión"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Método para crear un nuevo cliente
    fun crearCliente(categoriaServicioDTO: CategoriaServicioDTO) {

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = repository.crearCategoriaServicio(categoriaServicioDTO)
                if (response.isSuccessful && response.body()?.success == true) {
                    // Puedes actualizar la lista de categorias después de crear uno nuevo
                    obtenerCategoriaServicios()
                } else {
                    _errorMessage.value = response.body()?.message ?: "Error al crear la categoria"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error de conexión"
            } finally {
                _isLoading.value = false
            }
        }
    }
}