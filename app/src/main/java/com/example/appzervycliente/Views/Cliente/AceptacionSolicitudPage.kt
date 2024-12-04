package com.example.appzervycliente.Views.Cliente

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.Services.ViewModels.PropuestaServicioViewModel
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme
import kotlinx.coroutines.delay

@Composable
fun AceptacionSolicitudPage(
    navController: NavHostController,
    found: Boolean,
){
    val context = LocalContext.current

    BackHandler(
        enabled = true
    ) {
        Toast.makeText(context, "Accion no permitida", Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(Unit) {
        delay(7000)
        if(found){
            navController.navigate(Routes.PropuestaServicioPage.route){
                popUpTo(Routes.AceptacionSolicitudPage.route){ inclusive = true }
            }
        }else{
            navController.navigate(Routes.MainPage.route){
                popUpTo(Routes.SolicitudDiaPage.route){ inclusive = true }
                popUpTo(Routes.AceptacionSolicitudPage.route){ inclusive = true }
            }
        }
    }

    val transition = rememberInfiniteTransition(label = "initialtransition")
    val alpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = "alpha"
    )

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

        if(found){
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .graphicsLayer(alpha = alpha)
            ) {
                Image(
                    modifier = Modifier
                        .size(175.dp),
                    painter = painterResource(R.drawable.socios),
                    contentDescription = "image"
                )
                Text("!Socios encontrados¡")
            }
        }
        else{
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .graphicsLayer(alpha = alpha)
            ) {
                Image(
                    modifier = Modifier
                        .size(175.dp),
                    painter = painterResource(R.drawable.sociosnotfound),
                    contentDescription = "image"
                )
                Text("!No hay socios disponibles¡")
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
        AceptacionSolicitudPage(rememberNavController(), true)
    }
}