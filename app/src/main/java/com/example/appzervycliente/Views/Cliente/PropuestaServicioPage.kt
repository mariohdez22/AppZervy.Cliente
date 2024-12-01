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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@Composable
fun PropuestaServicioPage(
    navController: NavHostController
){

    Scaffold(
        topBar = { TopBar(navController) }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding()
                )
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Header()
            HorizontalDivider(thickness = 1.dp)
            Body(navController)
        }
    }

}


@Composable
@Preview(showBackground = true)
private fun PropuestaPreview(){

    AppZervyClienteTheme(
        dynamicColor = true
    ) {
        PropuestaServicioPage(rememberNavController())
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navController: NavHostController
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
                        Routes.SolicitudDiaPage.route,
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
            containerColor = Color.White
        ),
        windowInsets = WindowInsets(left = 25.dp, right = 25.dp, top = 20.dp)
    )

}

@Composable
private fun Header(

){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, start = 25.dp, end = 25.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Propuesta de servicio",
            fontSize = 25.sp,
            fontWeight = FontWeight.W400,
        )
        Text(
            text = stringResource(R.string.example3),
            fontSize = 14.sp,
            fontWeight = FontWeight.W300
        )
    }

}

@Composable
private fun Body(
    navController: NavHostController
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(10) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 3.dp,
                ),
                onClick = {
                    navController.navigate(Routes.PropuestaDetallePage.route)
                },
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    CardHeader()
                    HorizontalDivider(thickness = 1.dp)
                    CardBody()
                }
            }
        }
    }

}

@Composable
private fun CardHeader(){

    Row(
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(25.dp),
                onClick = {},
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(R.color.btnCerrarPropuesta)
                )
            ) {
                Icon(
                    modifier = Modifier.size(17.dp),
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "close",
                    tint = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ){
                Image(
                    modifier = Modifier
                        .size(40.dp),
                    painter = painterResource(R.drawable.avatarcard),
                    contentDescription = "image"
                )
                Column{
                    Text(
                        text = "Josue Hernandez",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500,
                        lineHeight = 15.sp
                    )
                    Text(
                        text = "Proveedor de servicios",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W300,
                        lineHeight = 15.sp
                    )
                }
            }
        }

    }
}

@Composable
private fun CardBody(){

    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Montaje Articulos",
            fontSize = 20.sp,
            fontWeight = FontWeight.W400,
        )
        Text(
            text = stringResource(R.string.example3),
            fontSize = 12.sp,
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
                    append("$ 35 USD")
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.W300,
                lineHeight = 15.sp
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W500)){
                        append("Duracion: ")
                    }
                    append("3h Aprox")
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.W300,
                lineHeight = 15.sp
            )
        }
    }

}