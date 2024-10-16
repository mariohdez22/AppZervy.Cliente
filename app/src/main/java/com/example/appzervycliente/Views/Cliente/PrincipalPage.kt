package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.appzervycliente.DTOs.CategoriaServicioDTO
import com.example.appzervycliente.Routes.ROOT_CARRITO_COMPRAS_PAGE
import com.example.appzervycliente.Routes.ROOT_INSPECCION_PAGE
import com.example.appzervycliente.Routes.ROOT_MAIN_PAGE
import com.example.appzervycliente.Services.ViewModels.CategoriaServicioViewModel
import com.example.appzervycliente.Views.ClientesTest.ClientesItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: CategoriaServicioViewModel,navController: NavHostController) {

    val categorias by viewModel.categorias
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    LaunchedEffect(Unit) {
        viewModel.obtenerCategoriaServicios()
    }

//    val servicesList = listOf(
//        Service("Instalación de TV", R.drawable.tv),
//        Service("Ensamblaje de Muebles", R.drawable.mueble),
//        Service("Cuidado del Jardín", R.drawable.jardin),
//        Service("Cuidado del Jardín", R.drawable.carpinteria)
//        // Agrega más servicios si es necesario
//    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HeaderSection()

            Text(
                text = "Servicios Disponibles",
                fontSize = 22.sp,
                fontWeight = FontWeight.W300,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 10.dp)
            )

            //--------------------------------------------------------------------------------------

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

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(categorias) { categoria ->
                            ServiceItem(
                                categoria = categoria,
                                onClick = {
                                // Manejar el clic en el servicio
                                // Por ejemplo, navegar a la pantalla de detalles del servicio
                                // navController.navigate("service_detail/${service.title}")
                                })
                        }
                    }

                }
            }

            //--------------------------------------------------------------------------------------

        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Imagen de perfil
        Image(
            painter = painterResource(id = R.drawable.profile_placeholder),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        // Nombre de usuario
        Text(
            text = "Mario H.",
            fontSize = 18.sp,
            fontWeight = FontWeight.W300,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        // Icono de configuración
        IconButton(onClick = {
            // Manejar clic en configuración
        }) {
            Icon(
                painter = painterResource(id = R.drawable.configuracionicon),
                contentDescription = "Configuración"
            )
        }
        // Icono del carrito
        IconButton(onClick = {
            // Manejar clic en carrito
        }) {
            Icon(
                painter = painterResource(id = R.drawable.inboxicon),
                contentDescription = "Carrito"
            )
        }
    }
}

@Composable
fun ServiceItem(categoria: CategoriaServicioDTO, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = categoria.foto),
                contentDescription = "Imagen del servicio",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color.Black.copy(alpha = 0.7f), Color.Transparent),
                            startX = 0f,
                            endX = 800f // Ajusta el tamaño del gradiente según el efecto deseado
                        )
                    )
            )

            Text(
                text = categoria.tituloCategoria,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.W300,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 16.dp)
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Home", R.drawable.homeicon, ROOT_MAIN_PAGE),
        BottomNavItem("Stores", R.drawable.historyicon, "stores"),
        BottomNavItem("Cart", R.drawable.carticon, ROOT_CARRITO_COMPRAS_PAGE),
        BottomNavItem("History", R.drawable.shopicon, ROOT_INSPECCION_PAGE)
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                },
                label = { Text(text = item.title) },
                selected = false, // Puedes manejar el estado seleccionado
                onClick = {
                    // Manejar la navegación
                    navController.navigate(item.route)
                }
            )
        }
    }
}

data class Service(
    val title: String,
    val imageRes: Int
)

data class BottomNavItem(
    val title: String,
    val icon: Int,
    val route: String
)

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AppZervyClienteTheme(
        dynamicColor = true
    ) {
        MainScreen(
            viewModel = CategoriaServicioViewModel(),
            navController = rememberNavController()
        )
    }
}
