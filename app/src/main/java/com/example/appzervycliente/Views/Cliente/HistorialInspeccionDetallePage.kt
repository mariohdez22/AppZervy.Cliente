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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Star
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.IconTextButtonHorizontalSection
import com.example.appzervycliente.Components.common.IconTextHorizontalSection
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialInspeccionDetallePage(
    navController: NavHostController
){

    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(navController, scrollBehavior) },
        containerColor = Color.White
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding(), bottom = 25.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(25.dp),
        ) {
            Header()
            HorizontalDivider(thickness = 1.dp)
            Body()
            HorizontalDivider(thickness = 1.dp)
            Socio()
            HorizontalDivider(thickness = 1.dp)
            Opciones()
            HorizontalDivider(thickness = 1.dp)
            Soporte()
        }
    }

}

@Composable
@Preview(showBackground = false)
private fun Preview(){

    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        HistorialInspeccionDetallePage(rememberNavController())
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
            scrolledContainerColor = Color.White
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
    ) {
        Text(
            text = "Historial Inspecciones",
            fontSize = 25.sp,
            fontWeight = FontWeight.W400,
        )
    }

}

@Composable
private fun Body(){

    Box(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    text = "Revision de tuberias del baño",
                    fontWeight = FontWeight.W600,
                )
                Text(
                    text = "Fontaneria",
                    fontWeight = FontWeight.W400,
                    fontSize = 15.sp
                )
                Text(
                    text = stringResource(R.string.example3),
                    fontWeight = FontWeight.W300,
                    fontSize = 15.sp,
                    lineHeight = 15.sp
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                IconTextHorizontalSection(
                    text = stringResource(R.string.example3),
                    label = "",
                    painter = painterResource(R.drawable.start_icon),
                    textWeight = FontWeight.W300,
                    textSize = 12.sp,
                    textHeight = 15.sp,
                    iconGap = 15.dp
                )
                IconTextHorizontalSection(
                    text = "Efectivo",
                    label = "Metodo de pago",
                    painter = painterResource(R.drawable.start_icon),
                    textWeight = FontWeight.W300,
                    iconGap = 15.dp,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                )
                IconTextHorizontalSection(
                    text = "2 Dias",
                    label = "Duracion Servicio",
                    painter = painterResource(R.drawable.start_icon),
                    textWeight = FontWeight.W300,
                    iconGap = 15.dp,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                )
                IconTextHorizontalSection(
                    text = "$55.00",
                    label = "Precio Base",
                    painter = painterResource(R.drawable.start_icon),
                    textWeight = FontWeight.W300,
                    iconGap = 15.dp,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                )
            }

        }
        Column(
            modifier = Modifier
                .width(80.dp)
                .align(Alignment.TopEnd)
                .clip(shape = RoundedCornerShape(50))
                .background(color = colorResource(R.color.txtInspeccionDetalle)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "10/11/2024",
                fontSize = 12.sp,
                fontWeight = FontWeight.W300,
            )
        }
    }

}

@Composable
private fun Socio(){

    var rating by remember { mutableStateOf(5) }

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        Text(
            text = "Socio del servicio",
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(35.dp),
                painter = painterResource(R.drawable.avatarcard),
                contentDescription = "image"
            )
            Column {
                Text(
                    text = "Josue Hernandez",
                    fontSize = 16.sp,
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
private fun Opciones(){

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Text(
            text = "Otras opciones",
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        )
        IconTextButtonHorizontalSection(
            label = "Ver recibo completo",
            labelWeight = FontWeight.W300,
            buttonText = "Ver",
            painter = painterResource(R.drawable.start_icon),
            iconGap = 10.dp,
            buttonContainerColor = colorResource(R.color.btnEnviarSolicitud),
            onButtonClick = {},
            horizontalArrangement = Arrangement.SpaceBetween
        )
        IconTextButtonHorizontalSection(
            label = "Calificar Inspeccion",
            labelWeight = FontWeight.W300,
            buttonText = "Ver",
            painter = painterResource(R.drawable.start_icon),
            iconGap = 10.dp,
            buttonContainerColor = colorResource(R.color.btnEnviarSolicitud),
            onButtonClick = {},
            horizontalArrangement = Arrangement.SpaceBetween
        )
    }

}

@Composable
private fun Soporte(){

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Text(
            text = "Ayuda y soporte",
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        )
        IconTextButtonHorizontalSection(
            label = "Publicar una inconformidad",
            labelWeight = FontWeight.W300,
            buttonText = "Ver",
            painter = painterResource(R.drawable.start_icon),
            iconGap = 10.dp,
            buttonContainerColor = colorResource(R.color.btnEnviarSolicitud),
            onButtonClick = {},
            horizontalArrangement = Arrangement.SpaceBetween
        )
    }

}