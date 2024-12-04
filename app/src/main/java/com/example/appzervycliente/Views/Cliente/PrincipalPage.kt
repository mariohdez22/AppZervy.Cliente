package com.example.appzervycliente.Views.Cliente

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.appzervycliente.DTOs.CategoriaServicioDTO
import com.example.appzervycliente.MainActivity
import com.example.appzervycliente.Routes.ROOT_CARRITO_COMPRAS_PAGE
import com.example.appzervycliente.Routes.ROOT_INSPECCION_PAGE
import com.example.appzervycliente.Routes.ROOT_MAIN_PAGE
import com.example.appzervycliente.Routes.ROOT_SOLICITUD_DIA_PAGE
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.Services.ViewModels.CategoriaServicioViewModel
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewCategoriaModel: CategoriaServicioViewModel,
    navController: NavHostController,
    onClose: () -> Unit
) {

    val categorias by viewCategoriaModel.categorias
    val isLoading by viewCategoriaModel.isLoading
    val errorMessage by viewCategoriaModel.errorMessage
    val context = LocalContext.current

    var showInspeccionActiva by remember { mutableStateOf(false) }

    BackHandler(
        enabled = true
    ) {
        Firebase.auth.signOut()
        onClose()
    }

    LaunchedEffect(Unit) {
        viewCategoriaModel.obtenerCategoriaServicios()
    }


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
            HeaderSection(navController)

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "!Bienvenido¡",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W500,
                )
                Text(
                    text = "Que servicio desea el dia de hoy?",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W300,
                    color = Color.DarkGray
                )
            }

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

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Oops! Ha ocurrido un error, estamos trabajando en ello",
                            fontWeight = FontWeight.W300,
                            fontSize = 20.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                else -> {

                    if(showInspeccionActiva){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 12.dp, end = 12.dp, bottom = 5.dp)
                        ){
                            ServiceActive(navController)
                        }
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(categorias) { categoria ->
                            ServiceItem(
                                categoria = categoria,
                                onClick = {
                                    val jsonArg = Gson().toJson(categoria)
                                    val encodeJson = URLEncoder.encode(jsonArg,"UTF-8")
                                    if(categoria.tipoCategoria == "Servicio de un solo dia"){
                                        navController.navigate(
                                            "${ROOT_SOLICITUD_DIA_PAGE}/${encodeJson}"
                                        )
                                    }else{
                                        navController.navigate(Routes.SolicitudDiasPage.route)
                                    }
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
fun HeaderSection(
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(top = 2.5.dp, bottom = 2.5.dp)
                .clickable {
                    navController.navigate(Routes.PerfilClientePage.route)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_placeholder),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.primary)
            )
            Text(
                text = "Mario H.",
                fontSize = 18.sp,
                fontWeight = FontWeight.W300,
                color = Color.Black
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            IconButton(onClick = {
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Configuración"
                )
            }
            IconButton(onClick = {
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.inboxicon),
                    contentDescription = "Carrito"
                )
            }
        }
    }
}

@Composable
fun ServiceActive(
    navController: NavHostController
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.cardInsepccionActiva)
        ),
        onClick = {
            navController.navigate(Routes.InspeccionPage.route)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.65f)
                    .align(Alignment.CenterEnd),
                painter = painterResource(R.drawable.backservicioactivo),
                contentDescription = "background",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = "Inspeccion Activa",
                    fontWeight = FontWeight.W500,
                    fontSize = 21.sp,
                    color = Color.White
                )
                Text(
                    text = "Tiempo transcurrido",
                    fontWeight = FontWeight.W300,
                    fontSize = 12.sp,
                    color = Color.White
                )
                Text(
                    text = "01 : 00 Horas",
                    fontWeight = FontWeight.W500,
                    fontSize = 14.5.sp,
                    color = Color.White
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
                Text(
                    text = "Tiempo transcurrido",
                    fontWeight = FontWeight.W300,
                    fontSize = 12.sp,
                    color = Color.White
                )
                Text(
                    text = "Varios dias",
                    fontWeight = FontWeight.W500,
                    fontSize = 14.5.sp,
                    color = Color.White
                )
            }
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

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = categoria.tituloCategoria,
                    color = Color.White,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.W400,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        Icon(
                            modifier = Modifier.size(16.5.dp),
                            painter = painterResource(R.drawable.time),
                            contentDescription = "time",
                            tint = Color.White
                        )
                        Text(
                            text = categoria.horarioServicio ?: "Sin horario establecido",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        Icon(
                            modifier = Modifier.size(16.5.dp),
                            imageVector = Icons.Filled.Info,
                            contentDescription = "time",
                            tint = Color.White
                        )
                        Text(
                            text = categoria.tipoCategoria ?: "Indefinido",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Home", R.drawable.homeicon, ROOT_MAIN_PAGE),
        BottomNavItem("Search", R.drawable.search, ROOT_SOLICITUD_DIA_PAGE),
        BottomNavItem("Cart", R.drawable.carticon, ROOT_CARRITO_COMPRAS_PAGE),
        BottomNavItem("History", R.drawable.history, ROOT_INSPECCION_PAGE)
    )

    val context = LocalContext.current

    NavigationBar{
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                },
                label = { Text(text = item.title) },
                selected = false, // Puedes manejar el estado seleccionado
                onClick = {
                    // Manejar la navegación
                    if(item.title == "Search" || item.title == "History"){
                        Toast.makeText(
                            context,
                            "Opcion en mantenimiento",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        navController.navigate(item.route)
                    }
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
            viewCategoriaModel = CategoriaServicioViewModel(),
            navController = rememberNavController(),
        ){}
    }
}
