package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appzervycliente.Components.common.GradientText
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun WelcomeScreen(
    navController: NavHostController,
) {
    // Obtener el usuario autenticado de Firebase
    val user = Firebase.auth.currentUser
    val displayName = user?.displayName ?: user?.email ?: "Usuario" // Prioriza el nombre, luego el correo

    // Usar LaunchedEffect para redirigir después de 7 segundos
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(7000) // Espera 7 segundos
        navController.navigate(Routes.MainPage.route) {
            popUpTo(Routes.MainPage.route) { inclusive = true } // Limpia el stack de navegación
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Fondos decorativos
        WelcomeBackgroundImages()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Texto de bienvenida con degradado
            GradientText(
                text = "¡Bienvenido",
                gradientColors = listOf(Color(0xFF7E57C2), Color(0xFFBA68C8)), // Degradado lila
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(4.dp))

            // Nombre del usuario con degradado
            GradientText(
                text = "$displayName!",
                gradientColors = listOf(Color(0xFF7E57C2), Color(0xFFBA68C8)), // Degradado lila
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun WelcomeBackgroundImages() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo decorativo superior izquierdo
        Image(
            painter = painterResource(id = R.drawable.group5),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth(0.25f)
                .padding(top = 0.dp, start = 0.dp),
            contentScale = ContentScale.Crop
        )

        // Fondo decorativo inferior derecho
        Image(
            painter = painterResource(id = R.drawable.group6),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxWidth(0.25f)
                .padding(bottom = 0.dp, end = 0.dp),
            contentScale = ContentScale.Crop
        )

        // Logo superior derecho
        Image(
            painter = painterResource(id = R.drawable.group4),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(100.dp)
                .padding(top = 16.dp, end = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    AppZervyClienteTheme {
        WelcomeScreen(
            navController = rememberNavController(),
        )
    }
}



