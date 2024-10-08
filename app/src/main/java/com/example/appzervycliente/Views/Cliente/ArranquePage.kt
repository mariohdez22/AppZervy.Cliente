package com.example.appzervycliente.Views.Cliente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme
import kotlinx.coroutines.delay
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.*

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppZervyClienteTheme {
                SplashScreen(navController = navController)
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    SplashScreenContent {
        // Navegar a la pantalla de inicio de sesión cuando se complete el splash screen
        navController.navigate("inicio") {
            popUpTo("splash_screen") { inclusive = true }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreenContent(onSplashFinished: () -> Unit) {
    // Transición infinita para el efecto de parpadeo (fade in y fade out)
    val transition = rememberInfiniteTransition(label = "fadeTransition")

    // Animación de opacidad (de transparente a opaco) con repetición infinita
    val alpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,  // Duración de la animación
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alphaAnimation"
    )

    // Introduce un retraso de 3 segundos antes de navegar al LoginScreen
    LaunchedEffect(Unit) {
        delay(3000) // 3 segundos de espera (ajusta este valor si lo necesitas)
        onSplashFinished() // Llama a la función para finalizar el splash y navegar
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A40)),  // Fondo color #1A1A40
        contentAlignment = Alignment.Center // Centrar el contenido
    ) {
        // Imagen animada solo con el efecto de parpadeo (fade in y fade out)
        Image(
            painter = painterResource(id = R.drawable.union),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(alpha = alpha) // Solo afecta la opacidad
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    AppZervyClienteTheme {
        SplashScreenContent {}
    }
}


