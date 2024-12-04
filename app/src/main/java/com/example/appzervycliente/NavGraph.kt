package com.example.appzervycliente

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appzervycliente.DTOs.CategoriaServicioDTO
import com.example.appzervycliente.DTOs.SolicitudServicioDTO
import com.example.appzervycliente.Routes.ARG_ESPERA
import com.example.appzervycliente.Routes.ARG_PROPUESTA_DETALLE
import com.example.appzervycliente.Routes.ARG_PROPUESTA_INFOSOCIO
import com.example.appzervycliente.Routes.ARG_SOLICITUD_DIA
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.Services.ViewModels.CategoriaServicioViewModel
import com.example.appzervycliente.Services.ViewModels.ClientesViewModel
import com.example.appzervycliente.Services.ViewModels.FotoSolicitudViewModel
import com.example.appzervycliente.Services.ViewModels.InspeccionViewModel
import com.example.appzervycliente.Services.ViewModels.PropuestaServicioViewModel
import com.example.appzervycliente.Services.ViewModels.ResenasViewModel
import com.example.appzervycliente.Services.ViewModels.SocioComercialViewModel
import com.example.appzervycliente.Services.ViewModels.SocioIndividualViewModel
import com.example.appzervycliente.Services.ViewModels.SocioServicioViewModel
import com.example.appzervycliente.Services.ViewModels.SolicitudServicioViewModel
import com.example.appzervycliente.Views.Cliente.AceptacionSolicitudPage
import com.example.appzervycliente.Views.Cliente.ActivacionInspeccionPage
import com.example.appzervycliente.Views.Cliente.AsistenciaAlClientePage
import com.example.appzervycliente.Views.Cliente.CarritoPage
import com.example.appzervycliente.Views.Cliente.CrearPublicacionDeAsistenciaClientePage
import com.example.appzervycliente.Views.Cliente.DireccionesFormPage
import com.example.appzervycliente.Views.Cliente.DireccionesPage
import com.example.appzervycliente.Views.Cliente.EsperaPage
import com.example.appzervycliente.Views.Cliente.InspeccionPage
import com.example.appzervycliente.Views.Cliente.LoginScreen
import com.example.appzervycliente.Views.Cliente.Login
import com.example.appzervycliente.Views.Cliente.ResenaSocioPage
import com.example.appzervycliente.Views.Cliente.MainScreen
import com.example.appzervycliente.Views.Cliente.SignUpScreen
import com.example.appzervycliente.Views.Cliente.SolicitudServicioDia
import com.example.appzervycliente.Views.Cliente.SolicitudServicioDias
import com.example.appzervycliente.Views.Cliente.SplashScreen
import com.example.appzervycliente.Views.Cliente.WelcomeScreen
import com.example.appzervycliente.Views.Cliente.EmailVerificationScreen
import com.example.appzervycliente.Views.Cliente.FacturaPage
import com.example.appzervycliente.Views.Cliente.HistorialInspeccionDetallePage
import com.example.appzervycliente.Views.Cliente.HistorialInspeccionPage
import com.example.appzervycliente.Views.Cliente.ServiceMapsScreen
import com.example.appzervycliente.Views.Cliente.InformacionPersonalPage
import com.example.appzervycliente.Views.Cliente.IngresoTargetaPage
import com.example.appzervycliente.Views.Cliente.MetodosPagoPage
import com.example.appzervycliente.Views.Cliente.PagoPosteriorPage
import com.example.appzervycliente.Views.Cliente.PagoPrevioPage
import com.example.appzervycliente.Views.Cliente.PerfilClientePage
import com.example.appzervycliente.Views.Cliente.PropuestaDetallePage
import com.example.appzervycliente.Views.Cliente.PropuestaInfoSocioPage
import com.example.appzervycliente.Views.Cliente.PropuestaServicioPage
import com.example.appzervycliente.Views.Cliente.RechazoDeServicioPage
import com.example.appzervycliente.Views.Cliente.VistaPagoPrevioVariosDiasPage
import com.example.appzervycliente.Views.Cliente.VistaPagoPosteriorVariosDiasEfectivosPage
import com.example.appzervycliente.Views.Cliente.ModificacionDeServicioPage
import com.example.appzervycliente.Views.Cliente.PropuestaItem
import com.example.appzervycliente.Views.Cliente.PublicacionDeConsultasAsistenciaCliente
import com.example.appzervycliente.Views.Cliente.VistaEsperaActivacionServicioPendientePage
import com.google.gson.Gson
import java.net.URLDecoder
import kotlin.math.log

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    onClose: () -> Unit
){
    val viewModel: ClientesViewModel = viewModel()
    val vmSolicitud: SolicitudServicioViewModel = viewModel()
    val vmFotoSolicitud: FotoSolicitudViewModel = viewModel()
    val vmPropuestas: PropuestaServicioViewModel = viewModel()
    val vmSocio: SocioServicioViewModel = viewModel()
    val vmSocioComercial: SocioComercialViewModel = viewModel()
    val vmSocioIndividual: SocioIndividualViewModel = viewModel()
    val vmResenas: ResenasViewModel = viewModel()
    val vmInspeccion: InspeccionViewModel = viewModel()
    val categoryViewModel: CategoriaServicioViewModel = viewModel()

    val propuestas by vmPropuestas.propuestas

    NavHost(
        navController = navController,
        startDestination = Routes.ArranquePage.route
    ) {
        //--------------------------------------------------------------[VISTAS LOGIN]
        composable(Routes.ArranquePage.route) {
            SplashScreen(navController)
        }
        composable(Routes.InicioPage.route) {
            LoginScreen(navController)
        }
        composable(Routes.LoginPage.route) {
            Login(navController)
        }
        composable(Routes.RegistroPage.route) {
            SignUpScreen(navController)
        }
        composable(Routes.Email.route) {
            EmailVerificationScreen(navController)
        }
        composable(Routes.Welcome.route) {
            WelcomeScreen(navController)
        }

        //--------------------------------------------------------------[VISTA MAIN]
        composable(Routes.MainPage.route) {
            MainScreen(
                viewCategoriaModel = categoryViewModel,
                navController = navController,
                onClose = onClose
            )
        }

        //--------------------------------------------------------------[VISTAS SOLICITUD SERVICIO]
        composable(Routes.SolicitudDiaPage.route) {
            val jsonData = it.arguments?.getString(ARG_SOLICITUD_DIA)
            val decodeJson = URLDecoder.decode(jsonData,"UTF-8")
            val catDto = Gson().fromJson(decodeJson, CategoriaServicioDTO::class.java)
            SolicitudServicioDia(navController, vmSolicitud, vmFotoSolicitud, catDto)
        }
        composable(Routes.SolicitudDiasPage.route) {
            val jsonData = it.arguments?.getString(ARG_SOLICITUD_DIA)
            val decodeJson = URLDecoder.decode(jsonData,"UTF-8")
            val catDto = Gson().fromJson(decodeJson, CategoriaServicioDTO::class.java)
            SolicitudServicioDias(navController, vmSolicitud, vmFotoSolicitud, catDto)
        }

        //--------------------------------------------------------------[VISTA ESPERA]
        composable(Routes.EsperaPage.route) {
            val jsonData = it.arguments?.getString(ARG_ESPERA)
            val decodeJson = URLDecoder.decode(jsonData,"UTF-8")
            val solicitudDto = Gson().fromJson(decodeJson, SolicitudServicioDTO::class.java)
            EsperaPage(navController, vmPropuestas, solicitudDto)
        }

        //--------------------------------------------------------------[VISTA ACEPTACION]
        composable(Routes.AceptacionSolicitudPage.route) {

            if(propuestas.isEmpty()){
                AceptacionSolicitudPage(navController, false)
            }
            else{
                AceptacionSolicitudPage(navController, true)
            }

        }

        //--------------------------------------------------------------[VISTAS PROPUESTA]
        composable(Routes.PropuestaServicioPage.route) {
            val lista = mutableListOf<PropuestaItem>()
            propuestas.forEach { p ->
                val item = PropuestaItem(
                    nombreCliente = p.nombreCliente,
                    tituloCategoria = p.tituloCategoria,
                    descripcionPropuesta = p.descripcionPropuesta,
                    duracionServicio = p.duracionServicio,
                    precioBase = p.precioBase,
                    tipoPago = p.tipoPago,
                    tipoCategoria = p.tipoCategoria,
                )
                p.idSocio?.let { id ->
                    vmSocio.obtenerSocioPorId(id)
                    val socio by vmSocio.socio
                    socio?.let { s ->
                        item.tipoSocio = s.tipoServicio
                        item.idSocio = s.idSocio ?: ""
                        item.experienciaSocio = s.anosExperiencia
                        when{
                            s.idSocioIndividual != null -> {
                                vmSocioIndividual
                                    .obtenerSocioIndividualPorId(s.idSocioIndividual)
                                val socioind by vmSocioIndividual.socioIndividual
                                socioind?.let { si ->
                                    item.nombreSocio = si.nombres
                                    item.fotoSocio = si.foto
                                }
                            }
                            s.idSocioComercial != null ->{
                                vmSocioComercial
                                    .obtenerSocioIndividualPorId(s.idSocioComercial)
                                val sociocom by vmSocioComercial.socioComercial
                                sociocom?.let { sc ->
                                    item.nombreSocio = sc.nombreComercial
                                    item.fotoSocio = sc.fotoComercial
                                }
                            }
                            else -> {}
                        }
                    }
                }
                lista.add(item)
            }
            PropuestaServicioPage(navController, lista)
        }
        composable(Routes.PropuestaDetallePage.route) {
            val jsonData = it.arguments?.getString(ARG_PROPUESTA_DETALLE)
            val decodeJson = URLDecoder.decode(jsonData,"UTF-8")
            val propuestaItem = Gson().fromJson(decodeJson, PropuestaItem::class.java)
            PropuestaDetallePage(navController, propuestaItem)
        }
        composable(Routes.PropuestaInfoSocioPage.route) {
            val jsonData = it.arguments?.getString(ARG_PROPUESTA_INFOSOCIO)
            val decodeJson = URLDecoder.decode(jsonData,"UTF-8")
            val propuestaItem = Gson().fromJson(decodeJson, PropuestaItem::class.java)
            PropuestaInfoSocioPage(navController, propuestaItem, vmResenas, vmInspeccion)
        }

        //--------------------------------------------------------------[VISTA PAGO POSTERIOR]
        composable(Routes.PagoPosteriorPage.route) {
            PagoPosteriorPage(navController)
        }

        //--------------------------------------------------------------[VISTA ACTIVACION]
        composable(Routes.ActivacionInspeccionPage.route) {
            ActivacionInspeccionPage(navController)
        }

        //--------------------------------------------------------------[VISTA CARRITO]
        composable(Routes.CarritoPage.route) {
            viewModel.cliente
            CarritoPage(navController)
        }

        //--------------------------------------------------------------[VISTA PAGO PREVIO]
        composable(Routes.PagoPrevioPage.route) {
            PagoPrevioPage(navController)
        }



        //--------------------------------------------------------------[VISTAS PERFIL CLIENTE]
        composable(Routes.PerfilClientePage.route) {
            PerfilClientePage(navController)
        }
        composable(Routes.InformacionPersonalPage.route) {
            InformacionPersonalPage(navController)
        }
        composable(Routes.DireccionesPage.route) {
            DireccionesPage(navController)
        }
        composable(Routes.DireccionesFormPage.route) {
            DireccionesFormPage(navController)
        }
        composable(Routes.MetodosPagoPage.route) {
            MetodosPagoPage(navController)
        }
        composable(Routes.IngresoTargetaPage.route) {
            IngresoTargetaPage(navController)
        }
        composable(Routes.HistorialInspeccionesPage.route) {
            HistorialInspeccionPage(navController)
        }
        composable(Routes.HistorialInspeccionDetallePage.route) {
            HistorialInspeccionDetallePage(navController)
        }
        composable(Routes.FacturaPage.route) {
            FacturaPage(navController)
        }





        //--------------------------------------------------------------[PENDIENTE DE ENRUTAR]

        composable(Routes.AsistenciaAlClientePage.route) {
            viewModel.cliente
            AsistenciaAlClientePage(navController)
        }

        composable(Routes.InspeccionPage.route) {
            InspeccionPage(navController)
        }

        composable(Routes.MapPage.route) {
            ServiceMapsScreen(navController)
        }

        composable(Routes.ResenaPage.route) {
            ResenaSocioPage(navController)
        }


        // Nuevas vistas (Michael Torres)
        composable(Routes.VistaPagoPosteriorVariosDiasEfectivosPage.route) {
            VistaPagoPosteriorVariosDiasEfectivosPage(navController)
        }
        composable(Routes.VistaPagoPrevioVariosDiasPage.route) {
            VistaPagoPrevioVariosDiasPage(navController)
        }
        composable(Routes.VistaEsperaActivacionServicioPendientePage.route) {
            VistaEsperaActivacionServicioPendientePage(navController)
        }
        composable(Routes.ModificacionDeServicioPage.route) {
            ModificacionDeServicioPage(navController)
        }
        composable(Routes.RechazoDeServicioPage.route) {
            RechazoDeServicioPage(navController)
        }
        composable(Routes.PublicacionesAsistenciaPage.route) {
            PublicacionDeConsultasAsistenciaCliente(navController)
        }
        composable(Routes.CrearPublicacionAsistenciaPage.route) {
            CrearPublicacionDeAsistenciaClientePage(navController)
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