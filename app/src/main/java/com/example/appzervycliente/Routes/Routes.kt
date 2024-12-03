package com.example.appzervycliente.Routes


//**************************************************************************************************
//----------------------------------------------------- [ARRANQUE]
const val ROOT_ARRANQUE_PAGE = "arranque"

//----------------------------------------------------- [INICIO]
const val ROOT_INICIO_PAGE = "inicio"

//----------------------------------------------------- [LOGIN]
const val ROOT_LOGIN_PAGE = "login_screen"

//----------------------------------------------------- [REGISTRO]
const val ROOT_REGISTRO_PAGE = "registro"

//----------------------------------------------------- [EMAIL]
const val ROOT_EMAIL_PAGE = "email"

//----------------------------------------------------- [WELCOME]
const val ROOT_WELCOME = "welcome"
//**************************************************************************************************



//**************************************************************************************************
//----------------------------------------------------- [PRINCIPAL]
const val ROOT_MAIN_PAGE = "principal"
//**************************************************************************************************



//**************************************************************************************************
//----------------------------------------------------- [SOLICITUD DIA]
const val ROOT_SOLICITUD_DIA_PAGE = "solicitudDia"
const val ARG_SOLICITUD_DIA = "argCat"

//----------------------------------------------------- [SOLICITUD DIAS]
const val ROOT_SOLICITUD_DIAS_PAGE = "solicitudDias"
//**************************************************************************************************



//**************************************************************************************************
//----------------------------------------------------- [ESPERA]
const val ROOT_ESPERA_PAGE = "espera"
const val ARG_ESPERA = "argSoli"
//**************************************************************************************************



//**************************************************************************************************
//----------------------------------------------------- [ACEPTACION SOLICITUD]
const val ROOT_ACEPTACION_SOLICITUD_PAGE = "aceptacionSolicitud"
//**************************************************************************************************



//**************************************************************************************************
//----------------------------------------------------- [PROPUESTA SERVICIO]
const val ROOT_PROPUESTA_SERVICIO_PAGE = "propuestaServicio"

//----------------------------------------------------- [PROPUESTA DETALLE]
const val ROOT_PROPUESTA_DETALLE_PAGE = "propuestaDetalle"

//----------------------------------------------------- [PROPUESTA INFO SOCIO]
const val ROOT_PROPUESTA_INFOSOCIO_PAGE = "propuestaInfoSocio"
//**************************************************************************************************



//**************************************************************************************************
//----------------------------------------------------- [PAGO POSTERIOR]
const val ROOT_PAGO_POSTERIOR_PAGE = "pagoPosterior"
//**************************************************************************************************



//**************************************************************************************************
//----------------------------------------------------- [ACTIVACION INSPECCION]
const val ROOT_ACTIVACION_INS_PAGE = "activacionInspeccion"
//**************************************************************************************************



//**************************************************************************************************
//----------------------------------------------------- [CARRITO DE COMPRAS]
const val ROOT_CARRITO_COMPRAS_PAGE = "carrito_compra"
//**************************************************************************************************



//**************************************************************************************************
//----------------------------------------------------- [PAGO PREVIO]
const val ROOT_PAGO_PREVIO_PAGE = "pagoPrevio"
//**************************************************************************************************



//**************************************************************************************************
//**************************************************************************************************


//----------------------------------------------------- [INSPECCION]
const val ROOT_INSPECCION_PAGE = "inspeccion"

//----------------------------------------------------- [RESENA]
const val ROOT_RESENA_PAGE = "resena"

















//----------------------------------------------------- [PERFIL CLIENTE]

const val ROOT_PERFIL_CLIENTE_PAGE = "perfilCliente"



//----------------------------------------------------- [INFORMACION PERSONAL]

const val ROOT_INFORMACION_PERSONAL_PAGE = "informacionPersonal"

//----------------------------------------------------- [DIRECCIONES]

const val ROOT_DIRECCIONES_PAGE = "direccionesPage"

//----------------------------------------------------- [DIRECCIONES FORM]

const val ROOT_DIRECCIONES_FORM_PAGE = "direccionesFormPage"

//----------------------------------------------------- [METODOS PAGO]

const val ROOT_METODOS_PAGO_PAGE = "metodosPago"

//----------------------------------------------------- [INGRESO TARGETA]

const val ROOT_INGRESAR_TARGETA_PAGE = "ingresoTargeta"

//----------------------------------------------------- [HISTORIAL INSPECCION]

const val ROOT_HISTORIAL_INSPECCION_PAGE = "historialInspeccion"

//----------------------------------------------------- [HISTORIAL INSPECCION DETALLE]

const val ROOT_HISTORIAL_INSPECCION_DETALLE_PAGE = "historialInspeccionDetalle"

//----------------------------------------------------- [FACTURA]

const val ROOT_FACTURA_PAGE = "factura"








//----------------------------------------------------- [NUEVAS VISTAS] - Michael Torres
const val ROOT_VISTA_PAGO_POSTERIOR_VARIOS_DIAS_PAGE = "vistaPagoPosteriorVariosDias"
const val ROOT_VISTA_PAGO_PREVIO_VARIOS_DIAS_PAGE = "vistaPagoPrevioVariosDias"
const val ROOT_VISTA_ESPERA_ACTIVACION_SERVICIO_PAGE = "vistaEsperaActivacionServicio"
const val ROOT_MODIFICACION_DE_SERVICIO_PAGE = "modificacionDeServicio"
const val ROOT_RECHAZO_DE_SERVICIO_PAGE = "rechazoDeServicio"

//----------------------------------------------------- [EMAIL]

const val ROOT_MAP_PAGE = "map"

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

// ----------------------------------------------------- [ASISTENCIA AL CLIENTE] - Michael Torres
const val ROOT_ASISTENCIA_AL_CLIENTE_PAGE = "asistenciaAlCliente"

//----------------------------------------------------- [PUBLICACIONES DE ASISTENCIA] - Michael Torres
const val ROOT_PUBLICACIONES_ASISTENCIA_PAGE = "publicacionesAsistenciaPage"

//----------------------------------------------------- [CREAR PUBLICACION DE ASISTENCIA] - Michael Torres
const val ROOT_CREAR_PUBLICACION_ASISTENCIA_PAGE = "crearPublicacionAsistencia"


sealed class Routes(
    val route: String
) {
    object ArranquePage : Routes(ROOT_ARRANQUE_PAGE)
    object InicioPage : Routes(ROOT_INICIO_PAGE)
    object LoginPage : Routes(ROOT_LOGIN_PAGE)
    object RegistroPage : Routes(ROOT_REGISTRO_PAGE)
    object Email : Routes(ROOT_EMAIL_PAGE)
    object Welcome : Routes(ROOT_WELCOME)

    object MainPage : Routes(ROOT_MAIN_PAGE)

    object SolicitudDiaPage : Routes(route = "${ROOT_SOLICITUD_DIA_PAGE}/{${ARG_SOLICITUD_DIA}}")
    object SolicitudDiasPage : Routes(ROOT_SOLICITUD_DIAS_PAGE)

    object EsperaPage : Routes(route = "$ROOT_ESPERA_PAGE/{${ARG_ESPERA}}")

    object AceptacionSolicitudPage : Routes(ROOT_ACEPTACION_SOLICITUD_PAGE)

    object PropuestaServicioPage : Routes(ROOT_PROPUESTA_SERVICIO_PAGE)
    object PropuestaDetallePage : Routes(ROOT_PROPUESTA_DETALLE_PAGE)
    object PropuestaInfoSocioPage : Routes(ROOT_PROPUESTA_INFOSOCIO_PAGE)

    object PagoPosteriorPage : Routes(ROOT_PAGO_POSTERIOR_PAGE)

    object ActivacionInspeccionPage : Routes(ROOT_ACTIVACION_INS_PAGE)

    object CarritoPage : Routes(ROOT_CARRITO_COMPRAS_PAGE)

    object PagoPrevioPage : Routes(ROOT_PAGO_PREVIO_PAGE)




    object InspeccionPage : Routes(ROOT_INSPECCION_PAGE)
    object ResenaPage : Routes(ROOT_RESENA_PAGE)
    object PerfilClientePage : Routes(ROOT_PERFIL_CLIENTE_PAGE)
    object InformacionPersonalPage : Routes(ROOT_INFORMACION_PERSONAL_PAGE)
    object DireccionesPage : Routes(ROOT_DIRECCIONES_PAGE)
    object MapPage : Routes(ROOT_MAP_PAGE)
    object MetodosPagoPage: Routes(ROOT_METODOS_PAGO_PAGE)
    object IngresoTargetaPage: Routes(ROOT_INGRESAR_TARGETA_PAGE)
    object HistorialInspeccionesPage: Routes(ROOT_HISTORIAL_INSPECCION_PAGE)
    object HistorialInspeccionDetallePage: Routes(ROOT_HISTORIAL_INSPECCION_DETALLE_PAGE)
    object FacturaPage: Routes(ROOT_FACTURA_PAGE)

    // Nuevas rutas - Michael Torres
    object VistaPagoPosteriorVariosDiasEfectivosPage : Routes(ROOT_VISTA_PAGO_POSTERIOR_VARIOS_DIAS_PAGE)
    object VistaPagoPrevioVariosDiasPage : Routes(ROOT_VISTA_PAGO_PREVIO_VARIOS_DIAS_PAGE)
    object VistaEsperaActivacionServicioPendientePage : Routes(ROOT_VISTA_ESPERA_ACTIVACION_SERVICIO_PAGE)
    object ModificacionDeServicioPage : Routes(ROOT_MODIFICACION_DE_SERVICIO_PAGE)
    object RechazoDeServicioPage : Routes(ROOT_RECHAZO_DE_SERVICIO_PAGE)
    object AsistenciaAlClientePage : Routes(ROOT_ASISTENCIA_AL_CLIENTE_PAGE)
    object PublicacionesAsistenciaPage : Routes(ROOT_PUBLICACIONES_ASISTENCIA_PAGE)
    object CrearPublicacionAsistenciaPage : Routes(ROOT_CREAR_PUBLICACION_ASISTENCIA_PAGE)

    object DireccionesFormPage: Routes(ROOT_DIRECCIONES_FORM_PAGE)

    object PerfilAgregarDireccionPage : Routes(ROOT_PERFIL_AGREGAR_DIRECCION_PAGE)

    object CrearClientePage : Routes(ROOT_CREAR_CLIENTE_PAGE)
    object EditarClientPage : Routes("${ROOT_EDITAR_CLIENTE_PAGE}/{${ARG_CLIENTE_ID}}")
}
