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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialInspeccionPage(
    navController: NavHostController
){

    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(navController, scrollBehavior) }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(color = Color.White),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Header()
            HorizontalDivider(thickness = 1.dp)
            HistorialItem()
        }
    }

}

@Composable
@Preview(showBackground = false)
private fun Preview(){

    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        HistorialInspeccionPage(rememberNavController())
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
        windowInsets = WindowInsets(left = 25.dp, right = 25.dp, top = 23.dp),
        scrollBehavior = scrollBehavior
    )
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
private fun HistorialItem(

){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  },
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Instalacion de Televisiones",
                fontWeight = FontWeight.W500,
                fontSize = 18.5.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    text = "10/09/2024",
                    fontWeight = FontWeight.W300,
                    fontSize = 14.sp
                )
                Text(
                    text = "Instalacion TV",
                    fontWeight = FontWeight.W300,
                    fontSize = 13.5.sp
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Precio: $40.00",
                fontWeight = FontWeight.W500
            )
        }

    }
    HorizontalDivider(thickness = 1.dp)

}