package com.example.appzervycliente.Services.ViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.appzervycliente.DTOs.ClienteDTO
import com.example.appzervycliente.Services.Repository.ClientesRepository
import androidx.compose.runtime.State
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class ClientesViewModel : ViewModel() {

    private val repository = ClientesRepository()

    private val _clientes = mutableStateOf<List<ClienteDTO>>(emptyList())
    val clientes: State<List<ClienteDTO>> = _clientes

    private val _cliente = mutableStateOf<ClienteDTO?>(null)
    val cliente: State<ClienteDTO?> = _cliente

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun obtenerClientes() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = repository.obtenerClientes()
                if (response.isSuccessful && response.body()?.success == true) {
                    _clientes.value = response.body()?.data ?: emptyList()
                } else {
                    _errorMessage.value = response.body()?.message ?: "Error al obtener clientes"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error de conexión"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Método para obtener un cliente por ID
    fun obtenerClientePorId(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = repository.obtenerClienteId(id)
                if (response.isSuccessful && response.body()?.success == true) {
                    _cliente.value = response.body()?.data
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

    fun crearCliente(clienteCreateDTO: ClienteDTO) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = repository.crearCliente(clienteCreateDTO)
                if (response.isSuccessful && response.body()?.success == true) {
                    obtenerClientes()
                } else {
                    _errorMessage.value = response.body()?.message ?: "Error al crear el cliente"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error de conexión"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Método para actualizar un cliente
    fun actualizarCliente(clienteUpdateDTO: ClienteDTO) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = repository.actualizarCliente(clienteUpdateDTO)
                if (response.isSuccessful && response.body()?.success == true) {
                    // Puedes actualizar el cliente específico o la lista completa
                    obtenerClientes()
                } else {
                    _errorMessage.value = response.body()?.message ?: "Error al actualizar el cliente"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error de conexión"
            } finally {
                _isLoading.value = false
            }
        }
    }

    //----------------------------------------------------------------------------------------------

    // Método para eliminar un cliente
    fun eliminarCliente(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = repository.eliminarCliente(id)
                if (response.isSuccessful && response.body()?.success == true) {
                    // Actualizar la lista de clientes después de eliminar uno
//                    obtenerClientes()
                } else {
                    _errorMessage.value = response.body()?.message ?: "Error al eliminar el cliente"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error de conexión"
            } finally {
                _isLoading.value = false
            }
        }
    }

    //----------------------------------------------------------------------------------------------

    // Puedes agregar métodos para limpiar el estado si es necesario
    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}