package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.appzervycliente.Components.common.IconLabelHorizontalSection
import com.example.appzervycliente.Components.common.IconTextHorizontalSection
import com.example.appzervycliente.DTOs.ReseñasDTO
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.Services.ViewModels.InspeccionViewModel
import com.example.appzervycliente.Services.ViewModels.ResenasViewModel
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@Composable
fun PropuestaInfoSocioPage(
    navController: NavHostController,
    propuestaItem: PropuestaItem? = null,
    vmResenas: ResenasViewModel,
    vmInspeccion: InspeccionViewModel
){
    val scrollState = rememberScrollState()

    val resenas by vmResenas.resenasSocios
    val isLoading by vmResenas.isLoading
    val errorMessage by vmResenas.errorMessage

    LaunchedEffect(Unit) {
        vmResenas.obtenerResenasPorIdSocio(propuestaItem?.idSocio ?: "")
    }

    Scaffold(
        topBar = { TopBar(navController) },
        containerColor = Color.White
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding(), bottom = 25.dp)
                .background(color = Color.White)
                .verticalScroll(scrollState)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Header(
                nombreSocio = propuestaItem?.nombreSocio ?: "Sin nombre",
                tipoSocio = propuestaItem?.tipoSocio ?: "Indefinido",
                foto = propuestaItem?.fotoSocio ?: stringResource(R.string.imgNotFound)
            )
            HorizontalDivider(thickness = 1.dp)
            DatosSocio(
                tipoSocio = propuestaItem?.tipoSocio ?: "Indefinido",
                experiencia = propuestaItem?.experienciaSocio ?: "Sin experiencia"
            )
            HorizontalDivider(thickness = 1.dp)
            when{
                isLoading -> {
                    Column(
                        modifier = Modifier
                            .weight(1f, fill = true)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
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
                    Column(
                        modifier = Modifier.padding(start = 25.dp, end = 25.dp)
                    ) {
                        Text(
                            text = "Reseña de inspecciones",
                            fontWeight = FontWeight.W500
                        )
                    }
                    if(resenas.isEmpty()){
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "No hay reseñas de momento, :)",
                                fontWeight = FontWeight.W300,
                                fontSize = 20.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        }
                    }else{
                        resenas.forEach { r ->
                            Resena(r, propuestaItem, vmInspeccion)
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview(){
    AppZervyClienteTheme {
        PropuestaInfoSocioPage(rememberNavController(), null, viewModel(), viewModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navController: NavHostController,
){
    TopAppBar(
        title = { Text("") },
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(R.color.btnCarritoTopBar)
                ),
                onClick = {
                    navController.popBackStack(
                        Routes.PropuestaDetallePage.route,
                        inclusive = false
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = Color.Black
                )
            }
        },
        actions = {
            IconButton(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(R.color.btnCarritoTopBar)
                ),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "settings",
                    tint = Color.Black
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
        ),
        windowInsets = WindowInsets(left = 25.dp, right = 25.dp, top = 23.dp)
    )
}

@Composable
private fun Header(
    nombreSocio: String,
    tipoSocio: String,
    foto: String,
){

    var rating by remember { mutableStateOf(5) }

    Column(
        modifier = Modifier
            .padding(top = 20.dp,start = 25.dp, end = 25.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.5.dp),
        ) {
            Image(
                modifier = Modifier.size(width = 60.dp, height = 50.dp),
                painter = rememberAsyncImagePainter(model = foto),
                contentDescription = "image",
                contentScale = ContentScale.FillBounds
            )
            Column {
                Text(
                    text = nombreSocio,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                    lineHeight = 15.sp
                )
                Text(
                    text = tipoSocio,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W300,
                    lineHeight = 15.sp

                )
                Row(
                    modifier = Modifier.padding(top = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "4.5",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W700
                    )
                    RatingBar(
                        onRatingChange = { rating = it },
                        rating = rating
                    )
                }
//                IconLabelHorizontalSection(
//                    painter = painterResource(R.drawable.time),
//                    label = "Cuenta creada en 2024"
//                )
            }
        }
    }

}


@Composable
private fun DatosSocio(
    tipoSocio: String,
    experiencia: String
){

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Text(
            text = "Datos del socio",
            fontWeight = FontWeight.W500
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = "Tipo socio: ",
            labelWeight = FontWeight.W500,
            text = tipoSocio,
            textWeight = FontWeight.W300,
            iconWidth = 17.dp,
            iconHeight = 17.dp
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = "Años de experiencia: ",
            labelWeight = FontWeight.W500,
            text = experiencia,
            textWeight = FontWeight.W300,
            iconWidth = 17.dp,
            iconHeight = 17.dp
        )
//        IconTextHorizontalSection(
//            painter = painterResource(R.drawable.start_icon),
//            label = "Trabajos realizados: ",
//            labelWeight = FontWeight.W500,
//            text = "20 servicios",
//            textWeight = FontWeight.W300,
//            iconWidth = 17.dp,
//            iconHeight = 17.dp
//        )
    }

}

@Composable
private fun Resena(
    resena: ReseñasDTO,
    propuestaItem: PropuestaItem?,
    vmInspeccion: InspeccionViewModel
){
//    val inspeccion by vmInspeccion.inspeccion
//
//    LaunchedEffect(Unit) {
//        vmInspeccion.obtenerInspeccionPorId(resena.idInscripccion ?: "")
//    }

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        ResenaComentario(
            nombreCliente = propuestaItem?.nombreCliente ?: "Sin nombre",
            descripcion = resena.cuerpoReseña
        )
//        ResenaDescripcion(
//            titulo = inspeccion.
//        )
    }
    HorizontalDivider(thickness = 1.dp)
}

@Composable
private fun ResenaComentario(
    nombreCliente: String,
    descripcion: String,
){

    var rating by remember { mutableStateOf(5) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Image(
//                modifier = Modifier.size(35.dp),
//                painter = painterResource(R.drawable.avatarcard),
//                contentDescription = "image"
//            )
            Column {
                Text(
                    text = nombreCliente,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    lineHeight = 15.sp
                )
                Text(
                    text = "Cliente",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W300,
                    lineHeight = 15.sp
                )
            }
            Box(
                modifier = Modifier.weight(1f)
            ){
                Row(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Text(
                        text = "4.5",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W700
                    )
                    RatingBar(
                        onRatingChange = { rating = it },
                        rating = rating,
                        iconSize = 16.dp,
                        iconGap = 1.dp
                    )
                }
            }
        }

        Text(
            text = descripcion,
            fontSize = 13.sp,
            fontWeight = FontWeight.W300,
            lineHeight = 15.sp
        )

    }
}

@Composable
private fun ResenaDescripcion(
    titulo: String,
    descripcion: String
){

    Column(
        modifier = Modifier.padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = titulo,
            fontWeight = FontWeight.W500
        )
        Text(
            text = descripcion,
            fontSize = 13.sp,
            fontWeight = FontWeight.W300,
            lineHeight = 15.sp
        )
    }

}


@Composable
private fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    onRatingChange: (Int) -> Unit,
    maxStars: Int = 5,
    iconSize: Dp = 20.dp,
    iconGap: Dp = 4.dp
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(iconGap)
    ){
        for(i in 1..maxStars){
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = if(i <= rating) colorResource(R.color.ratingIcon) else Color.Gray,
                modifier = Modifier
                    .size(iconSize)
                //.clickable { onRatingChange(i) }
            )
        }
    }
}
