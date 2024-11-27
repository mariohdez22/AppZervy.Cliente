package com.example.appzervycliente.views.Cliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@Composable
fun VistaPagoPosteriorVariosDiasEfectivosPage(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        FondoDecorativo()

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .width(225.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Encabezado()
            HorizontalDivider(thickness = 1.dp, color = Color.Gray)
            Contenido()
            BotonAceptar {
                navController.popBackStack()
            }
        }
    }
}

@Composable
private fun FondoDecorativo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Elementos decorativos del fondo
        Image(
            painter = painterResource(R.drawable.group5),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(140.dp),
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(R.drawable.group6),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(140.dp),
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(R.drawable.group3),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun Encabezado() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.pagopendiente),
            contentDescription = "Icono de pago pendiente",
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = "Servicio pendiente de pago",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 32.dp)
        )
        Text(
            text = "Asegúrate de pagar al finalizar el servicio.",
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Contenido() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        InfoTexto("Duración de servicio:", "Varios Días")
        InfoTexto("Tipo de pago:", "Efectivo")
    }
}

@Composable
private fun InfoTexto(titulo: String, detalle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = titulo,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Text(
            text = detalle,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun BotonAceptar(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(0.6f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF3B82F6),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = "Aceptar",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VistaPagoPosteriorVariosDiasEfectivosPreview() {
    AppZervyClienteTheme {
        VistaPagoPosteriorVariosDiasEfectivosPage(rememberNavController())
    }
}
