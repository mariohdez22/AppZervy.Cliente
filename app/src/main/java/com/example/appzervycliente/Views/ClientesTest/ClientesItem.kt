package com.example.appzervycliente.Views.ClientesTest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appzervycliente.DTOs.ClienteDTO

@Composable
fun ClientesItem(cliente: ClienteDTO, navController: NavController, onClick: (() -> Unit)? = null) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(16.dp)
    ) {
        Text(text = cliente.nombres, style = MaterialTheme.typography.headlineMedium)
        Text(text = "Correo: ${cliente.correo}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Celular: ${cliente.celular}", style = MaterialTheme.typography.bodyMedium)
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        IconButton(onClick = {
            // Mostrar un diálogo de confirmación
            // viewModel.eliminarCliente(cliente.idCliente)
            // onClienteEliminado()
            }
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Eliminar Cliente")
        }
        IconButton(onClick = {
                // Navegar a la pantalla de edición
                navController.navigate("editarCliente/${cliente.idCliente}")
            }
        ) {
            Icon(Icons.Default.Edit, contentDescription = "Editar Cliente")
        }
    }
}