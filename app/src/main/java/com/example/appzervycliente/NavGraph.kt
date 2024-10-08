package com.example.appzervycliente

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.Services.ViewModels.ClientesViewModel
import com.example.appzervycliente.Views.Cliente.CarritoPage
import com.example.appzervycliente.Views.Cliente.InspeccionPage
import com.example.appzervycliente.Views.Cliente.LoginScreen
import com.example.appzervycliente.Views.Cliente.RegistroPreview
import com.example.appzervycliente.Views.Cliente.SignUpScreen
import com.example.appzervycliente.Views.Cliente.SplashScreen
import com.example.appzervycliente.Views.ClientesTest.CrearClientes
import com.example.appzervycliente.Views.ClientesTest.EditarClientes
import com.example.appzervycliente.Views.ClientesTest.MostrarClientes

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    val viewModel: ClientesViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.ArranquePage.route
    ) {
        composable(Routes.CarritoPage.route) {
            CarritoPage(navController)
        }
        composable(Routes.InspeccionPage.route) {
            InspeccionPage(navController)
        }
        composable(Routes.LoginScreen.route) {
            LoginScreen(
                onLoginClick = {},
                onSignUpClick = {},
                navController = rememberNavController()
            )
        }
        composable(Routes.RegistroPage.route) {
            SignUpScreen(
                onLoginClick = {},
                onSignUpClick = {},
                navController = rememberNavController()
            )
        }
        composable(Routes.ArranquePage.route) {
            SplashScreen(navController)
        }

//-------------------------------------------------------------[PRUEBAS]
//        composable(Routes.MainPage.route) {
//            MostrarClientes(
//                viewModel = viewModel,
//                navController = navController
//            )
//        }
//        composable(Routes.CrearClientePage.route) {
//            CrearClientes(
//                viewModel = viewModel,
//                onClienteCreado = {
//                    navController.popBackStack()
//                }
//            )
//        }
//        composable(Routes.EditarClientPage.route) { navback ->
//            val clienteId = navback
//                .arguments?.getString("clienteId") ?: return@composable
//            EditarClientes(
//                viewModel = viewModel,
//                clienteId = clienteId,
//                onClienteActualizado = {
//                    navController.popBackStack()
//                }
//            )
//        }
    }
}