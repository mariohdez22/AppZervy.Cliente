package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.TextUserProfileSection
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@Composable
fun DireccionesPage(
    navController: NavHostController
){

    Scaffold(
        topBar = { TopBar(navController) }
    ){
        paddingValues->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .background(color = Color.White),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            Header()
            HorizontalDivider(thickness = 1.dp)
            Agregar(navController)
            HorizontalDivider(thickness = 1.dp)
            Body()

        }
    }

}

@Composable
@Preview(showBackground = false)
private fun Preview(){

    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        DireccionesPage(rememberNavController())
    }

}

@Composable
private fun Header(){

    Column(
        modifier = Modifier
            .padding(top = 20.dp,start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Direcciones",
            fontSize = 25.sp,
            fontWeight = FontWeight.W400,
        )
        Text(
            text = stringResource(R.string.example3),
            fontSize = 14.sp,
            fontWeight = FontWeight.W300,
            lineHeight = 15.sp
        )
    }

}

@Composable
private fun Agregar(
    navController: NavHostController
){

    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Routes.DireccionesFormPage.route)
            },
        verticalAlignment = Alignment.CenterVertically,
    ){
        Row(
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.start_icon),
                contentDescription = "icon"
            )
            Text(
                text = "Agregar direccion",
                fontSize = 17.sp,
                fontWeight = FontWeight.W300
            )
        }
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
                onClick = {}
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
private fun Body() {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp)
        ) {
            TextUserProfileSection(
                label = "Casa Hernandez",
                labelSize = 14.sp,
                labelWeight = FontWeight.W500,
                text = stringResource(R.string.example3),
                textSize = 14.sp,
                textWeight = FontWeight.W300,
                textHeight = 15.sp,
                textButton = "Editar",
                textButtonSize = 14.sp,
                textButtonWeight = FontWeight.W300,
                textButtonAlignment = Alignment.TopEnd,
                contentWidth = 0.6f
            )
        }
        HorizontalDivider(thickness = 1.dp)

    }
}