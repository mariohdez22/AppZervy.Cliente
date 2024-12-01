package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.Services.Repository.Route
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@Composable
fun PagoPosteriorPage(
    navController: NavHostController
){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 40.dp, end = 30.dp)
        ){
            Image(
                modifier = Modifier
                    .width(90.dp)
                    .height(60.dp),
                painter = painterResource(R.drawable.group4),
                contentDescription = "image",
                contentScale = ContentScale.FillBounds
            )
        }
        Image(
            modifier = Modifier
                .width(85.dp)
                .height(300.dp)
                .align(Alignment.TopStart),
            painter = painterResource(R.drawable.group5),
            contentDescription = "image",
            contentScale = ContentScale.FillBounds
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
                .align(Alignment.BottomEnd),
            painter = painterResource(R.drawable.group3),
            contentDescription = "image",
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .width(225.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Header()
            HorizontalDivider(thickness = 1.dp)
            Body()
            Button(
                modifier = Modifier.fillMaxWidth(0.6f),
                onClick = {
                    navController.navigate(Routes.ActivacionInspeccionPage.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.btnEnviarSolicitud)
                )
            ) {
                Text(
                    text = "Aceptar",
                    fontWeight = FontWeight.W300
                )
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
        PagoPosteriorPage(rememberNavController())
    }

}

@Composable
private fun Header(){

    Image(
        modifier = Modifier
            .size(120.dp),
        painter = painterResource(R.drawable.pagopendiente),
        contentDescription = "image"
    )
    Text(
        text = "Servicio pendiente de pago",
        textAlign = TextAlign.Center,
        fontSize = 25.sp,
        fontWeight = FontWeight.W400
    )
    Text(
        text = "Asegurate de pagar al finalizar el servicio",
        textAlign = TextAlign.Center,
        fontSize = 16.5.sp,
        fontWeight = FontWeight.W300
    )

}

@Composable
private fun Body(){

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Duracion de servicio: ",
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
            lineHeight = 15.sp
        )
        Text(
            text = "Un dia",
            fontWeight = FontWeight.W300,
            textAlign = TextAlign.Center,
            lineHeight = 15.sp
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tipo de pago: ",
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
            lineHeight = 15.sp
        )
        Text(
            text = "Efectivo",
            fontWeight = FontWeight.W300,
            textAlign = TextAlign.Center,
            lineHeight = 15.sp
        )
    }

}