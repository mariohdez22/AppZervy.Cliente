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
import com.example.appzervycliente.Routes.ROOT_CARRITO_COMPRAS_PAGE
import com.example.appzervycliente.Routes.ROOT_INSPECCION_PAGE
import com.example.appzervycliente.Routes.ROOT_MAIN_PAGE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val servicesList = listOf(
        Service("Instalación de TV", R.drawable.tv),
        Service("Ensamblaje de Muebles", R.drawable.mueble),
        Service("Cuidado del Jardín", R.drawable.jardin),
        Service("Cuidado del Jardín", R.drawable.carpinteria)
        // Agrega más servicios si es necesario
    )

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
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(servicesList) { service ->
                    ServiceItem(service = service, onClick = {
                        // Manejar el clic en el servicio
                        // Por ejemplo, navegar a la pantalla de detalles del servicio
                        // navController.navigate("service_detail/${service.title}")
                    })
                }
            }
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
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        // Nombre de usuario
        Text(
            text = "Mario H.",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
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
fun ServiceItem(service: Service, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = service.imageRes),
                contentDescription = "Imagen del servicio",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = service.title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
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
            navController = rememberNavController(),

        )
    }
}
