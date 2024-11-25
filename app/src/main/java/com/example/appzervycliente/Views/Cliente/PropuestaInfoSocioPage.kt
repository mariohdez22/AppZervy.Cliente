package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.IconLabelHorizontalSection
import com.example.appzervycliente.Components.common.IconTextHorizontalSection
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@Composable
fun PropuestaInfoSocioPage(
    navController: NavHostController
){
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { TopBar(navController) }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding(), bottom = 25.dp)
                .background(color = Color.White)
                .fillMaxWidth()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Header()
            HorizontalDivider(thickness = 1.dp)
            DatosSocio()
            HorizontalDivider(thickness = 1.dp)
            repeat(5){
                Resena()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview(){
    AppZervyClienteTheme {
        PropuestaInfoSocioPage(rememberNavController())
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

    var rating by remember { mutableStateOf(5) }

    Column(
        modifier = Modifier
            .padding(top = 20.dp,start = 25.dp, end = 25.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.5.dp),
        ) {
            Image(
                modifier = Modifier.size(width = 60.dp, height = 50.dp),
                painter = painterResource(R.drawable.avatar3dimage),
                contentDescription = "image"
            )
            Column {
                Text(
                    text = "Dennis Alexander",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                    lineHeight = 15.sp
                )
                Text(
                    text = "Armador de muebles",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W300,
                    lineHeight = 15.sp

                )
                Row(
                    modifier = Modifier.padding(top = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "4.5",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W700
                    )
                    RatingBar(
                        onRatingChange = { rating = it },
                        rating = rating
                    )
                }
                IconLabelHorizontalSection(
                    painter = painterResource(R.drawable.time),
                    label = "Cuenta creada en 2024"
                )
            }
        }
    }

}


@Composable
private fun DatosSocio(

){

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Text(
            text = "Datos del socio",
            fontWeight = FontWeight.W500
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = "Tipo socio: ",
            labelWeight = FontWeight.W500,
            text = "Individual",
            textWeight = FontWeight.W300,
            iconWidth = 17.dp,
            iconHeight = 17.dp
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = "Años de experiencia: ",
            labelWeight = FontWeight.W500,
            text = "2 años",
            textWeight = FontWeight.W300,
            iconWidth = 17.dp,
            iconHeight = 17.dp
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = "Trabajos realizados: ",
            labelWeight = FontWeight.W500,
            text = "20 servicios",
            textWeight = FontWeight.W300,
            iconWidth = 17.dp,
            iconHeight = 17.dp
        )
    }

}

@Composable
private fun Resena(){

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        Text(
            text = "Reseña de inspecciones",
            fontWeight = FontWeight.W500
        )
        ResenaComentario()
        ResenaDescripcion()
    }
    HorizontalDivider(thickness = 1.dp)
}

@Composable
private fun ResenaComentario(){

    var rating by remember { mutableStateOf(5) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
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
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W700
                    )
                    RatingBar(
                        onRatingChange = { rating = it },
                        rating = rating,
                        iconSize = 16.dp,
                        iconGap = 1.dp
                    )
                }
            }
        }

        Text(
            text = stringResource(R.string.example),
            fontSize = 13.sp,
            fontWeight = FontWeight.W300,
            lineHeight = 15.sp
        )

    }
}

@Composable
private fun ResenaDescripcion(

){

    Column(
        modifier = Modifier.padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = "Reparacion de ventilador",
            fontWeight = FontWeight.W500
        )
        Text(
            text = stringResource(R.string.example3),
            fontSize = 13.sp,
            fontWeight = FontWeight.W300,
            lineHeight = 15.sp
        )
    }

}


@Composable
private fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    onRatingChange: (Int) -> Unit,
    maxStars: Int = 5,
    iconSize: Dp = 20.dp,
    iconGap: Dp = 4.dp
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(iconGap)
    ){
        for(i in 1..maxStars){
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = if(i <= rating) colorResource(R.color.ratingIcon) else Color.Gray,
                modifier = Modifier
                    .size(iconSize)
                //.clickable { onRatingChange(i) }
            )
        }
    }
}
