package com.example.appzervycliente.Views.Cliente

import androidx.activity.compose.BackHandler
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
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@Composable
fun ActivacionInspeccionPage(
    navController: NavHostController
){

    BackHandler(
        enabled = true
    ) {
        navController.navigate(Routes.MainPage.route){
            popUpTo(Routes.ActivacionInspeccionPage.route) { inclusive = true }
        }
    }

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
                .width(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Header()
            Button(
                modifier = Modifier.fillMaxWidth(0.7f),
                onClick = {
                    navController.navigate(Routes.MainPage.route){
                        popUpTo(Routes.ActivacionInspeccionPage.route) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.btnEnviarSolicitud)
                )
            ) {
                Text(
                    text = "Siguiente",
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
        ActivacionInspeccionPage(rememberNavController())
    }

}

@Composable
private fun Header(){

    Image(
        modifier = Modifier
            .size(100.dp),
        painter = painterResource(R.drawable.staractivacion),
        contentDescription = "image"
    )
    Text(
        text = "!Inspeccion Activada¡",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight.W300
    )
    Text(
        text = "Asegurate de estar pendiente con la llegada del socio",
        textAlign = TextAlign.Center,
        fontSize = 15.5.sp,
        fontWeight = FontWeight.W300
    )

}