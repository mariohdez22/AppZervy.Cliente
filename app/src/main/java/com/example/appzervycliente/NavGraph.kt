package com.example.appzervycliente

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.Services.ViewModels.CategoriaServicioViewModel
import com.example.appzervycliente.Services.ViewModels.ClientesViewModel
import com.example.appzervycliente.Views.Cliente.CarritoPage
import com.example.appzervycliente.Views.Cliente.EsperaPage
import com.example.appzervycliente.Views.Cliente.InspeccionPage
import com.example.appzervycliente.Views.Cliente.LoginScreen
import com.example.appzervycliente.Views.Cliente.Login
import com.example.appzervycliente.Views.Cliente.ResenaSocioPage
import com.example.appzervycliente.Views.Cliente.MainScreen
import com.example.appzervycliente.Views.Cliente.MainScreen
import com.example.appzervycliente.Views.Cliente.SignUpScreen
import com.example.appzervycliente.Views.Cliente.SolicitudServicioDia
import com.example.appzervycliente.Views.Cliente.SolicitudServicioDias
import com.example.appzervycliente.Views.Cliente.SplashScreen
import com.example.appzervycliente.Views.Cliente.WelcomeScreen
import com.example.appzervycliente.Views.Cliente.EmailVerificationPreview
import com.example.appzervycliente.Views.Cliente.EmailVerificationScreen
import com.example.appzervycliente.Views.Cliente.WelcomeScreenPreview

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    val viewModel: ClientesViewModel = viewModel()
    val categoryViewModel: CategoriaServicioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.Email.route
    ) {
        composable(Routes.CarritoPage.route) {
            viewModel.cliente
            CarritoPage(navController)
        }
        composable(Routes.EsperaPage.route) {
            EsperaPage(navController)
        }
        composable(Routes.SolicitudDiaPage.route) {
            SolicitudServicioDia(navController)
        }
        composable(Routes.SolicitudDiasPage.route) {
            SolicitudServicioDias(navController)
        }
        composable(Routes.InspeccionPage.route) {
            InspeccionPage(navController)
        }

        composable(Routes.MainPage.route) {
           MainScreen(
               viewModel = categoryViewModel,
               navController = navController
           )
        }
        composable(Routes.LoginPage.route) {
            Login(navController)
        }
        composable(Routes.InicioPage.route) {
            LoginScreen(navController)
        }
        composable(Routes.RegistroPage.route) {
            SignUpScreen(navController)
        }
        composable(Routes.ArranquePage.route) {
            SplashScreen(navController)
        }

        composable(Routes.ResenaPage.route) {
            ResenaSocioPage(navController)
        }

        composable(Routes.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(Routes.Email.route) {
            EmailVerificationScreen(navController)
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