# APP ZERVY (Cliente)
Codigo oficial de la app movil de Zervy.

## Tabla de contenidos
- [Descripción](#descripción)
- [Dependencias](#dependencias)
- [Rutas](#rutas)
- [Navegación](#navegación)
- [Ejemplo practico](#componentes)

## Descripción
En esta guia tratare de explicar la logica del diseño y vistas, como se puede navegar entre diferentes vistas y asu vez como funcionan los componentes de jetpack compose.

## Dependencias
Dependencias usadas en esta aplicacion.

**Toml (libs.versions)**
```toml
navigationCompose = "2.8.1"
coil = "2.7.0"

androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }
coil = { group = "io.coil-kt", name= "coil-compose", version.ref = "coil" }

```

**Gradle (App)**
```gradle
dependencies {
    implementation(libs.androidx.navigation)
    implementation(libs.coil)
}
```
## Rutas
El proyecto trabaja con rutas, dichas rutas funcionan como una direccion url de una pagina web, nosotros podemos especificar un nombre de ruta para poder acceder a un vista. Las rutas pueden dividirse en diferentes secciones, como un nombre y su argumento.

**Ejemplo de nombre**
```kotlin

const val ROOT_EDITAR_CLIENTE_PAGE = "editar_cliente"

```

**Ejemplo de argumento**
```kotlin

const val ARG_CLIENTE_ID = "cliente_id"

```
Para poder manejar estas rutas dentro del proyecto, he creado una clase que contiene dichas rutas que podran ser utilizadas para acceder a un diseño en especifico.

**Archivo Routes.kt**
```kotlin
sealed class Routes(
    val route : String
){

}
```

Nosotros dentro de dicha clase podemos crear diferentes objetos con una ruta en especifico.

**Archivo Routes.kt**
```kotlin
sealed class Routes(
    val route : String
){
    //ruta sin argumentos
    object Page: Routes("RUTA_SIN_ARGUMENTOS")
    //ruta que posee un argumento
    //dicho argumento puede ser por ejemplo un dto
    object EditarIdArgClientPage: Routes("${ROOT_EDITAR_CLIENTE_PAGE}/{${ARG_CLIENTE_ID}}")
}
```

## Navegación
Para comenzar con la navegacion se necesita de un NavHostController, este objeto nos permitira navegar entre cada una de las vistas despues de haber configurado las rutas y asignarlas a una vista.

**Archivo NavGraph.kt**
Este archivo nos permitira configurar la navegación mediante una funcion de composición, asi como a su vez establecer la ruta y el diseño que se mostrara al tratar de acceder a esa ruta, asi como cualquier logica de programacion antes de mostrar dicha vista.

```kotlin
//metodo que nos permitira asignar las rutas a cada vista
//si no asignamos la ruta, no se podra mostrar la vista que deseamos y podria mostrar una excepcion

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        //ruta de la vista que se mostrara al principio de la app
        startDestination = Routes.Pagina.route
    ) {
        //Asignando la ruta que le pertenecera a la vista que vamos a mostrar
        composable(Routes.Pagina.route) {
            //si se accede a esta ruta se mostrara dicha vista
            //aqui se puede poner cualquier codigo antes de mostrar la vista
            //como obtener una lista de entidades que vamos a mostrar dentro de nuesta vista
            PaginaComposable()
        }
    }
}
```
## Ejemplo practico
Aqui se muestra un ejemplo practico de como debemos navegar entre diferentes vistas.

**Primer paso**
Como primer paso, debemos crear el nombre de la ruta y si es necesario el nombre que identifique el tipo de argumento que vamos a necesitar en nuestra vista

```kotlin

//nombre de la ruta base
const val ROOT_PAGE = "pagina_uno"
cosnt val ROOT_NEXT_PAGE = "pagina_dos"

//nombre del argumento, el nombre puede ser algo como una lista de objetos o algun id de alguna entidad
const val ARG_PAGE = "argumento"

```

**Segundo paso**
Como segundo paso, podemos usar estas variables para crear diferentes rutas si asi lo necesitaramos

```kotlin

//nombre de la ruta base
const val ROOT_PAGE = "pagina_uno"
cosnt val ROOT_NEXT_PAGE = "pagina_dos"

//nombre del argumento, el nombre puede ser algo como una lista de objetos o algun id de alguna entidad
const val ARG_PAGE = "argumento"

sealed class Routes(
    val route : String
){
    //ruta sin argumentos
    object FirstPage: Routes(ROOT_PAGE) //pagina principal
    object SecondPage: Routes(ROOT_NEXT_PAGE) //pagina secundaria

    //Esta es una ruta con argumento
    //dicho argumento puede ser por ejemplo un dto o un id
    object PaginaConArgumento: Routes("${ROOT_NEXT_PAGE}/{${ARG_PAGE}}") //ejemplo de pagina con argumento

    //podemos seguir creando diferentes patrones de rutas como querramos

}

```

**Tercer paso**
Como tercer paso, una vez creadas las rutas podemos asignarlas a una vista dentro del archivo `NavGraph.kt`.

```kotlin

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        //ruta de la vista que se mostrara al principio de la app
        startDestination = Routes.FirstPage.route
    ) {
        //Asignando la ruta que le pertenecera a la vista que vamos a mostrar
      
        composable(Routes.FirstPage.route) { //pagina uno
            //si se accede a esta ruta se mostrara dicha vista
            //aqui se puede poner cualquier codigo antes de mostrar la vista
            //como obtener una lista de entidades que vamos a mostrar dentro de nuesta vista

            //el objeto navController puede ser pasado como parametro para poder navegar desde una vista a otra
            PaginaComposable(navController) 
        }

        composable(Routes.SecondPage.route) { //pagina dos
            //si se accede a esta ruta se mostrara dicha vista
            //aqui se puede poner cualquier codigo antes de mostrar la vista
            //como obtener una lista de entidades que vamos a mostrar dentro de nuesta vista

            //el objeto navController puede ser pasado como parametro para poder navegar desde una vista a otra
            PaginaComposable()
        }

        composable(Routes.PaginaConArgumento.route) { //pagina tres
            //si se accede a esta ruta se mostrara dicha vista
            //aqui se puede poner cualquier codigo antes de mostrar la vista
            //como obtener una lista de entidades que vamos a mostrar dentro de nuesta vista

            //el objeto navController puede ser pasado como parametro para poder navegar desde una vista a otra
            PaginaComposable()
        }
    }
}

```

**Ultimo paso**
Como ultimo paso, ahora podemos navegar desde una vista a otra solo con presionar un boton y usando las rutas declaradas antes.

*Primera vista*
```kotlin
@Composable
fun FirstPage(
    navController: NavHostController
){
            Button(
                modifier = Modifier.fillMaxWidth(0.45f),
                onClick = {
                    //aqui podemos escribir toda la logica necesaria para poder navegar a la otra vista
                    //podemos enviar informacion si tuvieras algun parametro
                    navController.navigate(Routes.SecondPage.route)
                }
            ) {
                Text(
                    text = "Ir a la pagina dos"
                )
            }
}
```
*Segunda vista*
```kotlin
@Composable
fun SecondPage(
    navController: NavHostController
){
            Button(
                modifier = Modifier.fillMaxWidth(0.45f),
                onClick = {
                    //aqui podemos escribir toda la logica necesaria para poder navegar a la otra vista
                    //podemos enviar informacion si tuvieras algun parametro
                    navController.navigate(Routes.FirstPage.route)
                }
            ) {
                Text(
                    text = "Regresar a la pagina uno"
                )
            }
}
```
