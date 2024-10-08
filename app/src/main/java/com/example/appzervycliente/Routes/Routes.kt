package com.example.appzervycliente.Routes

//----------------------------------------------------- [CARRITO DE COMPRAS]
const val ROOT_CARRITO_COMPRAS_PAGE = "carrito_compra"

//----------------------------------------------------- [INSPECCION]
const val ROOT_INSPECCION_PAGE = "inspeccion"

//----------------------------------------------------- [INICIO]

const val ROOT_LOGIN_SCREEN_PAGE = "login_screen"

//----------------------------------------------------- [REGISTRO]

const val ROOT_REGISTRO_PAGE = "registro"

//----------------------------------------------------- [ARRANQUE]

const val ROOT_ARRANQUE_PAGE = "arranque"

//----------------------------------------------------- [REGISTRO]

//------------------------------------------------------[PRUEBAS]
//----------------------------------------------------- [LISTA DE CLIENTES]
const val ROOT_MAIN_PAGE = "clientes_list"

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
    object LoginScreen: Routes(ROOT_LOGIN_SCREEN_PAGE)
    object RegistroPage: Routes(ROOT_REGISTRO_PAGE)
    object ArranquePage: Routes(ROOT_ARRANQUE_PAGE)

    object PerfilAgregarDireccionPage: Routes(ROOT_PERFIL_AGREGAR_DIRECCION_PAGE)

    object MainPage: Routes(ROOT_MAIN_PAGE)
    object CrearClientePage: Routes(ROOT_CREAR_CLIENTE_PAGE)
    object EditarClientPage: Routes("${ROOT_EDITAR_CLIENTE_PAGE}/{${ARG_CLIENTE_ID}}")
}