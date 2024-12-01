package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.CardIconText
import com.example.appzervycliente.Components.common.IconLabelHorizontalSection
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PerfilClientePage(
    navController: NavHostController
){
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = { TopBar(navController, scrollBehavior) },
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ){
            Header()
            Opciones(navController)
            Body()
        }
    }

}

@Composable
@Preview(showBackground = true)
private fun Preview(){

    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        PerfilClientePage(rememberNavController())
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
                onClick = {}
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
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.White
        ),
        windowInsets = WindowInsets(left = 25.dp, right = 25.dp, top = 20.dp)
    )
}

@Composable
private fun Header(){

    Box{
        Image(
            painter = painterResource(R.drawable.perfilbackground),
            contentDescription = "image",
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp, bottom = 10.dp, top = 50.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Image(
                modifier = Modifier.size(130.dp),
                painter = painterResource(R.drawable.avatar3dimage),
                contentDescription = "image"
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                Text(
                    "Mario Hernandez",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )
                Text(
                    "herzmariohdez@gmail.com",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.W300
                )
            }
        }
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Opciones(
    navController: NavHostController
){
    FlowRow(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ){
        CardIconText(
            cardModifier = Modifier
                .height(120.dp)
                .width(110.dp),
            cardOnClick = {
                navController.navigate(Routes.InformacionPersonalPage.route)
            },
            cardElevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 3.dp
            ),
            cardColors = CardDefaults.elevatedCardColors(
                containerColor = colorResource(R.color.cardPerfilBackground)
            ),
            iconModifier = Modifier.size(50.dp),
            iconPainter = painterResource(R.drawable.perfilicon),
            iconTint = colorResource(R.color.iconPerfilTint),
            text = "Informacion personal",
            textSize = 15.5.sp,
            textAlign = TextAlign.Center,
            textHeight = 15.sp,
            textColor = colorResource(R.color.txtPerfil)
        )

        CardIconText(
            cardModifier = Modifier
                .height(120.dp)
                .width(110.dp),
            cardOnClick = {
                navController.navigate(Routes.DireccionesPage.route)
            },
            cardElevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 3.dp
            ),
            cardColors = CardDefaults.elevatedCardColors(
                containerColor = colorResource(R.color.cardPerfilBackground)
            ),
            iconModifier = Modifier.size(50.dp),
            iconVector = Icons.Filled.LocationOn,
            iconTint = colorResource(R.color.iconPerfilTint),
            text = "Direcciones del cliente",
            textSize = 15.5.sp,
            textAlign = TextAlign.Center,
            textHeight = 15.sp,
            textColor = colorResource(R.color.txtPerfil)
        )

        CardIconText(
            cardModifier = Modifier
                .height(120.dp)
                .width(110.dp),
            cardOnClick = {
                navController.navigate(Routes.MetodosPagoPage.route)
            },
            cardElevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 3.dp
            ),
            cardColors = CardDefaults.elevatedCardColors(
                containerColor = colorResource(R.color.cardPerfilBackground)
            ),
            iconModifier = Modifier.size(50.dp),
            iconPainter = painterResource(R.drawable.cuentasicon),
            iconTint = colorResource(R.color.iconPerfilTint),
            text = "Metodos de pago",
            textSize = 15.5.sp,
            textAlign = TextAlign.Center,
            textHeight = 15.sp,
            textColor = colorResource(R.color.txtPerfil)
        )

    }

}

@Composable
private fun Body(){

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, top = 35.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        InfoGeneral()
        InfoExtra()
    }

}

@Composable
private fun InfoGeneral(){

    Text(
        text = "Informacion general",
        fontSize = 20.sp,
        fontWeight = FontWeight.W500,
    )
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        IconLabelHorizontalSection(
            label = "Opcion 1",
            labelSize = 17.sp,
            labelWeight = FontWeight.W300,
            iconHeight = 25.dp,
            iconWidth = 25.dp
        )
        IconLabelHorizontalSection(
            label = "Opcion 2",
            labelSize = 17.sp,
            labelWeight = FontWeight.W300,
            iconHeight = 25.dp,
            iconWidth = 25.dp
        )
        IconLabelHorizontalSection(
            label = "Opcion 3",
            labelSize = 17.sp,
            labelWeight = FontWeight.W300,
            iconHeight = 25.dp,
            iconWidth = 25.dp
        )
        IconLabelHorizontalSection(
            label = "Opcion 4",
            labelSize = 17.sp,
            labelWeight = FontWeight.W300,
            iconHeight = 25.dp,
            iconWidth = 25.dp
        )
    }

}

@Composable
private fun InfoExtra(){

    Text(
        text = "Informacion extra",
        fontSize = 20.sp,
        fontWeight = FontWeight.W500,
    )
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        IconLabelHorizontalSection(
            label = "Opcion 1",
            labelSize = 17.sp,
            labelWeight = FontWeight.W300,
            iconHeight = 25.dp,
            iconWidth = 25.dp
        )
        IconLabelHorizontalSection(
            label = "Opcion 2",
            labelSize = 17.sp,
            labelWeight = FontWeight.W300,
            iconHeight = 25.dp,
            iconWidth = 25.dp
        )
    }
}