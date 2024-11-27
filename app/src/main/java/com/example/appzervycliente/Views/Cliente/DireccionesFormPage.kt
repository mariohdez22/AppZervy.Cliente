package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.appzervycliente.Components.common.ComboBox
import com.example.appzervycliente.Components.common.DropDownItem
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DireccionesFormPage(
    navController: NavHostController
){

    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = { TopBar(navController, scrollBehavior) },
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
                .background(color = Color.White)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Header()
            HorizontalDivider(thickness = 1.dp)
            Body()
            HorizontalDivider(thickness = 1.dp)
        }
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
@Preview(showBackground = false)
private fun Preview(){

    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        DireccionesFormPage(rememberNavController())
    }

}

@Composable
private fun Header(){

    Column(
        modifier = Modifier
            .padding(top = 20.dp,start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Agregar Direcciones",
            fontSize = 25.sp,
            fontWeight = FontWeight.W400,
        )
    }

}

@Composable
private fun Body(){

    var primeradir by remember { mutableStateOf("") }
    var segundadir by remember { mutableStateOf("") }
    var codigopostal by remember { mutableStateOf("") }

    var pais by remember { mutableStateOf("") }
    var departamento by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf(DropDownItem("")) }

    val options = listOf(
        DropDownItem("Opcion 1"),
        DropDownItem("Opcion 2"),
        DropDownItem("Opcion 3"),
        DropDownItem("Opcion 4")
    )

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                text = "Direccion 1",
            )
            OutlinedTextField(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                value = primeradir,
                onValueChange = { primeradir = it },
                shape = RoundedCornerShape(10.dp)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                text = "Direccion 2",
            )
            OutlinedTextField(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                value = segundadir,
                onValueChange = { segundadir = it },
                shape = RoundedCornerShape(10.dp),
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                text = "Codigo postal",
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = codigopostal,
                onValueChange = { codigopostal = it },
                shape = RoundedCornerShape(10.dp),
                singleLine = true
            )
        }
        ComboBox(
            label = "Ciudad",
            items = options,
            onItemClick = { ciudad = it }
        )
        ComboBox(
            label = "Ciudad",
            items = options,
            onItemClick = { ciudad = it }
        )
        ComboBox(
            label = "Ciudad",
            items = options,
            onItemClick = { ciudad = it }
        )

    }



}
