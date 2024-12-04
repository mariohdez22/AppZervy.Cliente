package com.example.appzervycliente.Views.Cliente

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.platform.LocalContext
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.Services.ViewModels.ClientesViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun SplashScreen(
    navController: NavHostController
) {

    val context = LocalContext.current
    val user = Firebase.auth.currentUser

    BackHandler(
        enabled = true
    ) {
        Toast.makeText(context, "Accion no permitida", Toast.LENGTH_SHORT).show()
    }

    SplashScreenContent {
        if(user == null){
            navController.navigate(Routes.InicioPage.route) {
                popUpTo(Routes.ArranquePage.route) { inclusive = true }
            }
        }else{
            navController.navigate(Routes.Welcome.route) {
                popUpTo(Routes.ArranquePage.route) { inclusive = true }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreenContent(onSplashFinished: () -> Unit) {
    val transition = rememberInfiniteTransition(label = "fadeTransition")

    val alpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alphaAnimation"
    )

    LaunchedEffect(Unit) {
        delay(3000)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A40)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.union),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(alpha = alpha)
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


