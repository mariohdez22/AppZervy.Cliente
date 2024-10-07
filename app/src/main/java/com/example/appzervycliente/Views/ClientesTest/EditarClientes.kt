package com.example.appzervycliente.Views.ClientesTest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appzervycliente.DTOs.ClienteDTO
import com.example.appzervycliente.Services.ViewModels.ClientesViewModel

@Composable
fun EditarClientes(
    viewModel: ClientesViewModel,
    clienteId: String,
    onClienteActualizado: () -> Unit
) {
    val cliente by viewModel.cliente

    LaunchedEffect(clienteId) {
        viewModel.obtenerClientePorId(clienteId)
    }

    cliente?.let { clienteData ->
        val nombres = remember { mutableStateOf(clienteData.nombres ?: "") }
        val celular = remember { mutableStateOf(clienteData.celular ?: "") }
        val correo = remember { mutableStateOf(clienteData.correo ?: "") }
        val foto = remember { mutableStateOf(clienteData.foto ?: "") }
        val dui = remember { mutableStateOf(clienteData.dui ?: "") }

        val isLoading by viewModel.isLoading
        val errorMessage by viewModel.errorMessage

        Column(modifier = Modifier.padding(top = 60.dp, start = 20.dp, end = 20.dp)) {

            TextField(
                value = nombres.value,
                onValueChange = { nombres.value = it },
                label = { Text("Nombres") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = celular.value,
                onValueChange = { celular.value = it },
                label = { Text("Celular") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = correo.value,
                onValueChange = { correo.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = foto.value,
                onValueChange = { foto.value = it },
                label = { Text("Foto") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = dui.value,
                onValueChange = { dui.value = it },
                label = { Text("DUI") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val clienteActualizado = ClienteDTO(
                        idCliente = clienteId,
                        nombres = nombres.value,
                        celular = celular.value,
                        correo = correo.value,
                        foto = foto.value,
                        dui = dui.value
                    )
                    viewModel.actualizarCliente(clienteActualizado)
                    onClienteActualizado()
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Actualizar Cliente")
            }

            if (isLoading) {
                CircularProgressIndicator()
            }

            errorMessage?.let { mensaje ->
                Text(text = mensaje, color = MaterialTheme.colorScheme.error)
            }
        }
    } ?: run {
        Text("Cargando datos del cliente...")
    }
}