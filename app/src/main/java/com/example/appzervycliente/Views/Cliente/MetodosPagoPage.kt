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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@Composable
fun MetodosPagoPage(
    navController: NavHostController
){

    var showFormatoPago by remember { mutableStateOf(false) }
    var optionSelected by remember { mutableStateOf("") }

    val options = listOf(
        "Targeta de credito/debito",
        "Google Pay",
        "Apple Pay"
    )


    Scaffold(
        topBar = { TopBar(navController) }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Header()
            HorizontalDivider(thickness = 1.dp)
            Agregar{
                showFormatoPago = true
            }
            HorizontalDivider(thickness = 1.dp)
            Item()
        }

        if(showFormatoPago){
            PopUp(
                onDismissRequest = { showFormatoPago = false },
                options = options,
                onOptionSelected = { optionSelected = it }
            )
        }
    }

}

@Composable
@Preview
private fun Preview(){

    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        MetodosPagoPage(rememberNavController())
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
    onClick: () -> Unit
){

    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
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
                text = "Agregar metodo de pago",
                fontSize = 17.sp,
                fontWeight = FontWeight.W300
            )
        }
    }

}

@Composable
private fun Item(){

    Row(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, start = 25.dp, end = 25.dp)
            .height(50.dp)
            .fillMaxWidth()
            .clickable {  },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ){
        Icon(
            modifier = Modifier
                .size(35.dp, 45.dp),
            painter = painterResource(R.drawable.visaicon),
            contentDescription = "icon",
            tint = Color.Unspecified
        )
        Column {
            Text(
                text = "Targeta Hernandez - Nº 7656***",
                fontWeight = FontWeight.W500
            )
            Text(
                text = "Vigente",
                fontWeight = FontWeight.W300
            )
        }
    }
    HorizontalDivider(thickness = 1.dp)
}

@Composable
private fun PopUp(
    options: List<String>,
    onDismissRequest: () -> Unit,
    onOptionSelected: (String) -> Unit
){
    var selectedOption by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(35.dp)
            ){
                Text(
                    text = "Elegir formato de pago",
                    fontWeight = FontWeight.W500,
                    fontSize = 22.sp
                )
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEach { option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ){
                            RadioButton(
                                selected = selectedOption == option,
                                onClick = {
                                    selectedOption = option
                                    onOptionSelected(option)
                                }
                            )
                            Text(
                                text = option
                            )
                        }
                    }
                }

            }
        }
    }
}
