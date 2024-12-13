package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.appzervycliente.Components.common.IconLabelHorizontalSection
import com.example.appzervycliente.Components.common.IconTextHorizontalSection
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.ROOT_PROPUESTA_INFOSOCIO_PAGE
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme
import com.google.gson.Gson
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropuestaDetallePage(
    navController: NavHostController,
    propuestaItem: PropuestaItem? = null
){
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
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
                titulo = propuestaItem?.tituloCategoria ?: "Sin titulo",
                descripcion = propuestaItem?.descripcionPropuesta ?: "Sin Descripcion",
                precio = propuestaItem?.precioBase ?: "Sin precio",
                duracion = propuestaItem?.duracionServicio ?: "Sin duracion"
            )
            HorizontalDivider(thickness = 1.dp)
            SocioDetalle(
                navController = navController,
                propuestaItem = propuestaItem
            )
            HorizontalDivider(thickness = 1.dp)
            Detalles(propuestaItem)
            HorizontalDivider(thickness = 1.dp)
            SubTotal(subTotal = propuestaItem?.precioBase ?: "0")
            HorizontalDivider(thickness = 1.dp)
            AceptarPropuesta(navController)
        }
    }

}

@Composable
@Preview(showBackground = false)
private fun PropuestaPreview(){

    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        PropuestaDetallePage(rememberNavController())
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
                        Routes.PropuestaServicioPage.route,
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
    titulo: String,
    descripcion: String,
    precio: String,
    duracion: String
){

    Column(
        modifier = Modifier
            .padding(top = 20.dp,start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = titulo,
            fontSize = 25.sp,
            fontWeight = FontWeight.W400,
        )
        Text(
            text = descripcion,
            fontSize = 14.sp,
            fontWeight = FontWeight.W300,
            lineHeight = 15.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ){
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W500)){
                        append("Precio: ")
                    }
                    append(precio)
                },
                fontSize = 14.sp,
                fontWeight = FontWeight.W300,
                lineHeight = 15.sp
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W500)){
                        append("Duracion: ")
                    }
                    append(duracion)
                },
                fontSize = 14.sp,
                fontWeight = FontWeight.W300,
                lineHeight = 15.sp
            )
        }
    }

}

@Composable
private fun SocioDetalle(
    navController: NavHostController,
    propuestaItem: PropuestaItem? = null
){

    var rating by remember { mutableStateOf(5) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val jsonArg = Gson().toJson(propuestaItem)
                val encodeJson = URLEncoder.encode(jsonArg,"UTF-8")
                navController.navigate(
                    "${ROOT_PROPUESTA_INFOSOCIO_PAGE}/${encodeJson}"
                )
            }
            .padding(start = 25.dp, end = 25.dp, bottom = 10.dp, top = 10.dp)
    ){
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(35.dp),
                painter = rememberAsyncImagePainter(
                    model = propuestaItem?.fotoSocio ?: stringResource(R.string.imgNotFound)
                ),
                contentDescription = "image",
                contentScale = ContentScale.FillBounds
            )
            Column {
                Text(
                    text = propuestaItem?.nombreSocio ?: "Sin nombre",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    lineHeight = 15.sp
                )
                Text(
                    text = propuestaItem?.tipoSocio ?: "Indefinido",
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
                        fontSize = 23.sp,
                        fontWeight = FontWeight.W700
                    )
                    RatingBar(
                        onRatingChange = { rating = it },
                        rating = rating
                    )
                }
            }
        }

    }


}

@Composable
private fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    onRatingChange: (Int) -> Unit,
    maxStars: Int = 5
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ){
        for(i in 1..maxStars){
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = if(i <= rating) colorResource(R.color.ratingIcon) else Color.Gray,
                modifier = Modifier
                    .size(20.dp)
                    //.clickable { onRatingChange(i) }
            )
        }
    }
}

@Composable
private fun Detalles(
    propuestaItem: PropuestaItem? = null
){
    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        DetallesLista(
            tituloCategoria = propuestaItem?.tituloCategoria ?: "Sin titulo",
            tipoPago = propuestaItem?.tipoPago ?: "Indefinido",
            tipoCategoria = propuestaItem?.tipoCategoria ?: "Indefinido"
        )
        DetalleInfo(
            descripcion = propuestaItem?.descripcionPropuesta ?: "Sin Descripcion"
        )
    }
}

@Composable
private fun DetallesLista(
    tituloCategoria: String,
    tipoPago: String,
    tipoCategoria: String,
){

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "Datos generales",
            fontWeight = FontWeight.W500,
            fontSize = 17.sp
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = tituloCategoria,
            fontWeight = FontWeight.W300,
            contentDescription = "icon"
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = tipoPago,
            fontWeight = FontWeight.W300,
            contentDescription = "icon"
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = tipoCategoria,
            fontWeight = FontWeight.W300,
            contentDescription = "icon"
        )
    }
}

@Composable
private fun DetalleInfo(
    descripcion: String
){

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Descripcion del servicio",
            fontWeight = FontWeight.W500,
            fontSize = 15.sp
        )
        Text(
            text = descripcion,
            fontWeight = FontWeight.W300,
            fontSize = 15.sp
        )
    }
}

@Composable
private fun SubTotal(
    subTotal: String,
){
    Row(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "Subtotal",
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        )
        Text(
            text = "$ $subTotal USD",
            fontSize = 18.sp,
            fontWeight = FontWeight.W300
        )
    }
}

@Composable
private fun AceptarPropuesta(
    navController: NavHostController
){
    Row(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Button(
            modifier = Modifier,
            onClick = {
                navController.navigate(Routes.PagoPosteriorPage.route){
                    popUpTo(Routes.SolicitudDiaPage.route) { inclusive = true }
                    popUpTo(Routes.PropuestaServicioPage.route){ inclusive = true }
                    popUpTo(Routes.PropuestaDetallePage.route){ inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = colorResource(R.color.btnEnviarSolicitud)
            )
        ) {
            Text(
                text = "Aceptar Propuesta",
                fontWeight = FontWeight.W400,
                fontSize = 18.sp
            )
        }
    }
}