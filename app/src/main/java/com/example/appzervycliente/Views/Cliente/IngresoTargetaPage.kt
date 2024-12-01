package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.ComboBox
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngresoTargetaPage(
    navController: NavHostController
){
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var dataCard by remember {
        mutableStateOf(CardDataPreview("", "", "", ""))
    }

    Scaffold(
        topBar = { TopBar(navController, scrollBehavior) },
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding(), bottom = 15.dp)
                .fillMaxSize()
                .background(color = Color.White)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Header(dataCard)
            HorizontalDivider(thickness = 1.dp)
            Body{
                dataCard = it
            }
            HorizontalDivider(thickness = 1.dp)
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text(
                        text = "Agregar Targeta",
                        color = Color.White
                    )
                }
            }

        }
    }

}

@Composable
@Preview(showBackground = true)
private fun Preview(){
    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        IngresoTargetaPage(rememberNavController())
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
                onClick = {
                    navController.popBackStack()
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
        windowInsets = WindowInsets(left = 25.dp, right = 25.dp, top = 23.dp),
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun Header(
    cardData: CardDataPreview
){
    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Text(
            text = "Agregar metodo de pago",
            fontSize = 25.sp,
            fontWeight = FontWeight.W500
        )
        Card(
            modifier = Modifier
                .height(225.dp)
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .background(brush = Brush.linearGradient(
                        colors = listOf(
                            colorResource(R.color.cardPago1),
                            colorResource(R.color.cardPago2)
                        )
                    ))
                    .fillMaxSize()
            ){
                CardBody(cardData)
            }
        }
    }
}

@Composable
private fun CardBody(
    cardData: CardDataPreview
){

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(R.drawable.cardchip),
                contentDescription = "image"
            )
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(R.drawable.visaicon),
                contentDescription = "image"
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = buildString {
                    cardData.numero.chunked(4).forEachIndexed{ index, chunk ->
                        append(chunk)
                        if(index < 3) append(" ")
                    }
                },
                fontSize = 30.sp,
                fontWeight = FontWeight.W300,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    modifier = Modifier
                        .width(45.dp),
                    text = "Valida Hasta",
                    fontWeight = FontWeight.W800,
                    fontSize = 11.5.sp,
                    lineHeight = 13.sp
                )
                Text(
                    text = cardData.fecha,
                    fontWeight = FontWeight.W300,
                    fontSize = 24.sp
                )
            }
            Text(
                text = cardData.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        }

    }

}

@Composable
private fun Body(
    onDataChange: (CardDataPreview) -> Unit
){

    var alias by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var direcciones by remember { mutableStateOf("") }

    var options = listOf("opcion 1", "opcion 2", "opcion 3", "opcion 4")

    LaunchedEffect(alias,nombre,numero,fecha) {
        onDataChange(
            CardDataPreview(nombre = nombre, alias = alias, fecha = fecha, numero = numero)
        )
    }

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                text = "Alias Targeta",
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = alias,
                onValueChange = { alias = it },
                shape = RoundedCornerShape(10.dp),
                singleLine = true
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                text = "Numero Targeta",
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = numero,
                onValueChange = {
                    if(it.length <= 16){
                        numero = it.replace(Regex("\\D"), "")
                    }
                },
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(
                    text = "Fecha vencimiento",
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = fecha,
                    onValueChange = {
                        if(it.length <= 5){
                            val newFecha = it.replace(Regex("\\D"), "")

                            fecha = when {
                                newFecha.length <= 2 ->
                                    newFecha
                                else ->
                                    newFecha.substring(0,2) + "/" + newFecha.substring(2)
                            }
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(
                    text = "CVV",
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = cvv,
                    onValueChange = { cvv = it },
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                text = "Nombre del propietario",
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = nombre,
                onValueChange = {
                    nombre = it.uppercase()
                },
                shape = RoundedCornerShape(10.dp),
                singleLine = true
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                text = "Direcciones",
            )
            ComboBox(
                onItemSelected = { direcciones = it },
                shape = RoundedCornerShape(10.dp),
                items = options,
                selectedItem = direcciones
            )
        }


    }

}


private data class CardDataPreview(
    var alias: String,
    var numero: String,
    var fecha: String,
    var nombre: String,
)