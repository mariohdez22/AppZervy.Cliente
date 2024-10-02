package com.example.appzervycliente.Views.ClientesTest

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Services.ViewModels.ClientesViewModel

@Composable
fun ClientesApp() {

    val navController = rememberNavController()
    val viewModel: ClientesViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "clientesList"
    ) {
        composable("clientesList") {
            MostrarClientes(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable("crearCliente") {
            CrearClientes(
                viewModel = viewModel,
                onClienteCreado = {
                    navController.popBackStack()
                }
            )
        }
        composable("editarCliente/{clienteId}") { backStackEntry ->
            val clienteId = backStackEntry.arguments?.getString("clienteId") ?: return@composable
            EditarClientes(
                viewModel = viewModel,
                clienteId = clienteId,
                onClienteActualizado = {
                    navController.popBackStack()
                }
            )
        }
        //        composable("clienteDetalle/{clienteId}") { backStackEntry ->
//            val clienteId = backStackEntry.arguments?.getString("clienteId") ?: return@composable
//            DetalleClienteScreen(
//                viewModel = viewModel,
//                clienteId = clienteId,
//                onEditarCliente = {
//                    navController.navigate("editarCliente/$clienteId")
//                },
//                onEliminarCliente = {
//                    viewModel.eliminarCliente(clienteId)
//                    navController.popBackStack()
//                }
//            )
//        }
    }
}