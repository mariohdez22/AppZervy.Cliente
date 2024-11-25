package com.example.appzervycliente.Routes

import okhttp3.Route

//----------------------------------------------------- [LOGIN]
const val ROOT_LOGIN_PAGE = "login_screen"

//----------------------------------------------------- [CARRITO DE COMPRAS]
const val ROOT_CARRITO_COMPRAS_PAGE = "carrito_compra"

//----------------------------------------------------- [INSPECCION]
const val ROOT_INSPECCION_PAGE = "inspeccion"

//----------------------------------------------------- [RESENA]
const val ROOT_RESENA_PAGE = "resena"
//----------------------------------------------------- [REGISTRO]

const val ROOT_REGISTRO_PAGE = "registro"

//----------------------------------------------------- [ARRANQUE]

const val ROOT_ARRANQUE_PAGE = "arranque"

//----------------------------------------------------- [PRINCIPAL]

const val ROOT_MAIN_PAGE = "principal"

//----------------------------------------------------- [SOLICITUD DIA]

const val ROOT_SOLICITUD_DIA_PAGE = "solicitudDia"

//----------------------------------------------------- [SOLICITUD DIAS]

const val ROOT_SOLICITUD_DIAS_PAGE = "solicitudDias"

//----------------------------------------------------- [ESPERA]

const val ROOT_ESPERA_PAGE = "espera"

//----------------------------------------------------- [PROPUESTA SERVICIO]

const val ROOT_PROPUESTA_SERVICIO_PAGE = "propuestaServicio"

//----------------------------------------------------- [PROPUESTA DETALLE]

const val ROOT_PROPUESTA_DETALLE_PAGE = "propuestaDetalle"

//----------------------------------------------------- [PROPUESTA INFO SOCIO]

const val ROOT_PROPUESTA_INFOSOCIO_PAGE = "propuestaInfoSocio"

//----------------------------------------------------- [PAGO POSTERIOR]

const val ROOT_PAGO_POSTERIOR_PAGE = "pagoPosterior"

//----------------------------------------------------- [PAGO PREVIO]

const val ROOT_PAGO_PREVIO_PAGE = "pagoPrevio"

//----------------------------------------------------- [ACTIVACION INSPECCION]

const val ROOT_ACTIVACION_INS_PAGE = "activacionInspeccion"

//----------------------------------------------------- [ACEPTACION SOLICITUD]

const val ROOT_ACEPTACION_SOLICITUD_PAGE = "aceptacionSolicitud"



//----------------------------------------------------- [INICIO]

const val ROOT_INICIO_PAGE = "inicio"

//----------------------------------------------------- [WELCOME]

const val ROOT_WELCOME = "welcome"

//----------------------------------------------------- [EMAIL]

const val ROOT_EMAIL_PAGE = "email"

//----------------------------------------------------- [REGISTRO]

//------------------------------------------------------[PRUEBAS]
//----------------------------------------------------- [LISTA DE CLIENTES]
const val ROOT_CLIENTE_LISTA_PAGE = "clientes_list"

//----------------------------------------------------- [CREAR CLIENTES]
const val ROOT_CREAR_CLIENTE_PAGE = "crear_cliente"

//----------------------------------------------------- [EDITAR CLIENTES]
const val ROOT_EDITAR_CLIENTE_PAGE = "editar_cliente"
const val ARG_CLIENTE_ID = "cliente_id"

//----------------------------------------------------- [PERFIL AGREGAR DIRECCIÓN]
const val ROOT_PERFIL_AGREGAR_DIRECCION_PAGE = "perfil_agregar_direccion"

sealed class Routes(
    val route : String
){
    object CarritoPage: Routes(ROOT_CARRITO_COMPRAS_PAGE)
    object InspeccionPage: Routes(ROOT_INSPECCION_PAGE)
    object LoginPage: Routes(ROOT_LOGIN_PAGE)
    object ResenaPage: Routes(ROOT_RESENA_PAGE)
    object RegistroPage: Routes(ROOT_REGISTRO_PAGE)
    object InicioPage: Routes(ROOT_INICIO_PAGE)
    object ArranquePage: Routes(ROOT_ARRANQUE_PAGE)
    object MainPage: Routes(ROOT_MAIN_PAGE)
    object Welcome: Routes(ROOT_WELCOME)
    object Email: Routes(ROOT_EMAIL_PAGE)
    object SolicitudDiaPage: Routes(ROOT_SOLICITUD_DIA_PAGE)
    object SolicitudDiasPage: Routes(ROOT_SOLICITUD_DIAS_PAGE)
    object EsperaPage: Routes(ROOT_ESPERA_PAGE)
    object AceptacionSolicitudPage: Routes(ROOT_ACEPTACION_SOLICITUD_PAGE)
    object PropuestaServicioPage: Routes(ROOT_PROPUESTA_SERVICIO_PAGE)
    object PropuestaDetallePage: Routes(ROOT_PROPUESTA_DETALLE_PAGE)
    object PropuestaInfoSocioPage: Routes(ROOT_PROPUESTA_INFOSOCIO_PAGE)
    object PagoPosteriorPage: Routes(ROOT_PAGO_POSTERIOR_PAGE)
    object PagoPrevioPage: Routes(ROOT_PAGO_PREVIO_PAGE)
    object ActivacionInspeccionPage: Routes(ROOT_ACTIVACION_INS_PAGE)

    object PerfilAgregarDireccionPage: Routes(ROOT_PERFIL_AGREGAR_DIRECCION_PAGE)


    object CrearClientePage: Routes(ROOT_CREAR_CLIENTE_PAGE)
    object EditarClientPage: Routes("${ROOT_EDITAR_CLIENTE_PAGE}/{${ARG_CLIENTE_ID}}")
}