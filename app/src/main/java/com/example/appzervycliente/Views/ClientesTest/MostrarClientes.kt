package com.example.appzervycliente.Views.ClientesTest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Services.ViewModels.ClientesViewModel
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarClientes(viewModel: ClientesViewModel, navController: NavController) {

    val clientes by viewModel.clientes
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    LaunchedEffect(Unit) {
        viewModel.obtenerClientes()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Clientes") })
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 20.dp,
                    end = 20.dp
                ),
        ) {
            when {
                isLoading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
                errorMessage != null -> {
                    Text(
                        text = errorMessage ?: "Error desconocido",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
                else -> {

                    Button(
                        onClick = { navController.navigate("crearCliente") },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Magenta,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Agregar Cliente")
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(750.dp)
                            .padding(top = 0.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clipToBounds()
                    ) {
                        LazyColumn(
                            modifier = Modifier.padding(top = 0.dp),
                        ) {
                            items(clientes) { cliente ->
                                ClientesItem(
                                    cliente = cliente,
                                    navController = navController,
                                    viewModel = viewModel,
                                    onClienteEliminado = {
                                        navController.navigate("clientesList")
                                    })
                                HorizontalDivider()
                            }
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MostrarClientePreview(){
    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        MostrarClientes(ClientesViewModel(), rememberNavController())
    }
}