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
import com.example.appzervycliente.Views.Cliente.ResenaSocioPage
import com.example.appzervycliente.Views.Cliente.MainScreen
import com.example.appzervycliente.Views.Cliente.MainScreen
import com.example.appzervycliente.Views.Cliente.SignUpScreen
import com.example.appzervycliente.Views.Cliente.SplashScreen

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
            viewModel.cliente
            CarritoPage(navController)
        }
        composable(Routes.InspeccionPage.route) {
            InspeccionPage(navController)
        }

        composable(Routes.MainPage.route) {
           MainScreen(navController)
        }
        composable(Routes.LoginPage.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Routes.MainPage.route)
                },
                onSignUpClick = {
                    navController.navigate(Routes.RegistroPage.route)
                },
                navController = rememberNavController()
            )
        }
        composable(Routes.RegistroPage.route) {
            SignUpScreen(
                onLoginClick = {
                    navController.navigate(Routes.MainPage.route)
                },
                onSignUpClick = {
                    navController.navigate(Routes.LoginPage.route)
                },
                navController = rememberNavController()
            )
        }
        composable(Routes.ArranquePage.route) {
            SplashScreen(navController)
        }

        composable(Routes.ResenaPage.route) {
            ResenaSocioPage(navController)
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