package com.example.appzervycliente.Views.ClientesTest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appzervycliente.DTOs.ClienteDTO
import com.example.appzervycliente.Services.ViewModels.ClientesViewModel
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@Composable
fun CrearClientes(
    viewModel: ClientesViewModel,
    onClienteCreado: () -> Unit
) {

    val nombres = remember { mutableStateOf("") }
    val celular = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }
    val contraseña = remember { mutableStateOf("") }
    val foto = remember { mutableStateOf("") }
    val dui = remember { mutableStateOf("") }

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
            value = contraseña.value,
            onValueChange = { contraseña.value = it },
            label = { Text("Contraseña") },
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
                val nuevoCliente = ClienteDTO(
                    nombres = nombres.value,
                    celular = celular.value,
                    correo = correo.value,
                    contraseña = contraseña.value,
                    foto = foto.value,
                    dui = dui.value
                )
                viewModel.crearCliente(nuevoCliente)
                onClienteCreado()
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Crear Cliente")
        }

        if (isLoading) {
            CircularProgressIndicator()
        }

        errorMessage?.let { mensaje ->
            Text(text = mensaje, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CrearClientePage(){
    AppZervyClienteTheme(
        dynamicColor = true
    ) {
        CrearClientes(ClientesViewModel(), {})
    }
}