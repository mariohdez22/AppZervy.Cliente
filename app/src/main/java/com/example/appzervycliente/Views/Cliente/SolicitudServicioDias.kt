package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.DTOs.CategoriaServicioDTO
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.Services.ViewModels.FotoSolicitudViewModel
import com.example.appzervycliente.Services.ViewModels.SolicitudServicioViewModel
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SolicitudServicioDias(
    navController: NavHostController,
    vmSolicitud: SolicitudServicioViewModel,
    vmFotoSolicitud: FotoSolicitudViewModel,
    categoria: CategoriaServicioDTO? = null
){
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = { TopBar(navController, scrollBehavior) },
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Header()
            ArticuloDescripcion()
            Solicitud()
            TipoPago(navController)
        }
    }

}

@Composable
@Preview(showBackground = true)
private fun Preview(){
    AppZervyClienteTheme(dynamicColor = false) {
        SolicitudServicioDias(rememberNavController(), viewModel(), viewModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior
){
    TopAppBar(
        title = { Text("") },
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White
                ),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back"
                )
            }
        },
        actions = {
            IconButton(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White
                ),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "settings"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.White
        ),
        scrollBehavior = scrollBehavior,
        windowInsets = WindowInsets(left = 25.dp, right = 25.dp, top = 20.dp)
    )
}

@Composable
private fun Header(){

    Box{
        Image(
            painter = painterResource(R.drawable.carpinteria),
            contentDescription = "image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp, bottom = 20.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                "Carpinteria",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.W600
            )
        }
    }

}

@Composable
private fun ArticuloDescripcion(){

    Column(
        modifier = Modifier
            .padding(top = 25.dp, bottom = 20.dp, start = 25.dp, end = 25.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = stringResource(R.string.example)
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
                Text(
                    text = "Horario: ",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = "7:00 AM a 10:00 PM",
                    fontSize = 13.sp,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ){
                Text(
                    text = "Duracion: ",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = "Un dia",
                    fontSize = 13.sp,
                )
            }
        }
    }
    HorizontalDivider(thickness = 1.dp)

}

@Composable
private fun Solicitud(){

    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var presupuesto by remember { mutableStateOf("") }

    val images = listOf(
        painterResource(R.drawable.furniture_assembly_image),
        painterResource(R.drawable.garden_care_image),
        painterResource(R.drawable.tv),
        painterResource(R.drawable.mueble)
    )

    Column(
        modifier = Modifier
            .padding(top = 30.dp, bottom = 30.dp, start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Column{
            Text(
                text = "Titulo de la solicitud",
                fontSize = 16.5.sp,
                fontWeight = FontWeight.W500
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text("") },
                value = titulo,
                onValueChange = { titulo = it },
                placeholder = {
                    Text("Agregar titulo...")
                },
                shape = RoundedCornerShape(15.dp),
                maxLines = 1,
                singleLine = true
            )
        }

        Column {
            Text(
                text = "Descripcion de la solicitud",
                fontSize = 16.5.sp,
                fontWeight = FontWeight.W500
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                label = { Text("") },
                value = descripcion,
                onValueChange = { descripcion = it },
                placeholder = {
                    Text("Agregar descripcion...")
                },
                singleLine = false,
                shape = RoundedCornerShape(15.dp)
            )
        }

        Column{
            Text(
                text = "Agregar presupuesto",
                fontSize = 16.5.sp,
                fontWeight = FontWeight.W500
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text("") },
                value = presupuesto,
                onValueChange = { presupuesto = it },
                placeholder = {
                    Text("Agregar presupuesto...")
                },
                shape = RoundedCornerShape(15.dp),
                maxLines = 1,
                singleLine = true
            )
        }

        Column{
            Text(
                text = "Agregar fotos de la zona de trabajo",
                fontSize = 16.5.sp,
                fontWeight = FontWeight.W500
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ){
                LazyRow(
                    modifier = Modifier
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(images) { image ->
                        Image(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(100.dp)
                                .clip(shape = RoundedCornerShape(10.dp)),
                            painter = image,
                            contentDescription = "image",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Button(
                    modifier = Modifier
                        .width(100.dp)
                        .fillMaxHeight(),
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .border(2.dp, Color.LightGray, CircleShape)
                    ){
                        Icon(
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.Center),
                            imageVector = Icons.Filled.Add,
                            contentDescription = "",
                            tint = Color.LightGray
                        )
                    }
                }
            }

        }

    }
}

@Composable
private fun TipoPago(
    navController: NavHostController
){

    var isTargeta by remember { mutableStateOf(false) }
    var isEfectivo by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Text(
            text = "Tipo de pago",
            fontSize = 16.5.sp,
            fontWeight = FontWeight.W500
        )
        Row {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Targeta",
                    fontWeight = FontWeight.W400
                )
                Checkbox(
                    checked = isTargeta,
                    onCheckedChange = {
                        if(!isEfectivo){
                            isTargeta = it
                        }
                    }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Efectivo",
                    fontWeight = FontWeight.W400
                )
                Checkbox(
                    checked = isEfectivo,
                    onCheckedChange = {
                        if(!isTargeta){
                            isEfectivo = it
                        }
                    }
                )
            }
        }
        HorizontalDivider(thickness = 1.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(0.75f),
                onClick = {
                    navController.navigate(Routes.EsperaPage.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.btnEnviarSolicitud),
                    contentColor = Color.White
                )
            ){
                Text("Enviar Solicitud")
            }
        }

    }

}