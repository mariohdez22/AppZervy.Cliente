package com.example.appzervycliente.Views.Cliente

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun EmailVerificationScreen(navController: NavHostController) {
    val currentUser = Firebase.auth.currentUser
    var verificationStatus by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    // Animación de opacidad y escala para el ícono del correo
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        VerificationBackgroundImages()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Ícono de email con animación de opacidad y escala
            Image(
                painter = painterResource(id = R.drawable.mail),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp)
                    .graphicsLayer(
                        alpha = alpha, // Aplicar animación de opacidad
                        scaleX = scale, // Aplicar animación de escala en X
                        scaleY = scale  // Aplicar animación de escala en Y
                    )
            )

            // Título
            Text(
                text = "Verificación de email",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Mensaje de información
            Text(
                text = "Hemos enviado un número de verificación a tu correo\n${currentUser?.email}",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón para verificar
            Button(
                onClick = {
                    currentUser?.reload()?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            verificationStatus = currentUser.isEmailVerified
                            if (verificationStatus) {
                                navController.navigate(Routes.LoginPage.route) {
                                    popUpTo(Routes.Email.route) { inclusive = true }
                                }
                            } else {
                                errorMessage = "El correo no ha sido verificado. Por favor, inténtalo de nuevo."
                            }
                        } else {
                            errorMessage = task.exception?.localizedMessage ?: "Error al verificar."
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2)),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "Verificar", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mensaje de error
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Enlace para reenvío
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "¿No has recibido el código?",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                TextButton(
                    onClick = {
                        currentUser?.sendEmailVerification()?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                errorMessage = "Correo reenviado con éxito."
                            } else {
                                errorMessage = task.exception?.localizedMessage ?: "Error al reenviar."
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Reenviar código",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF7E57C2)
                    )
                }
            }
        }
    }
}




@Composable
fun VerificationBackgroundImages() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo decorativo superior izquierdo
        Image(
            painter = painterResource(id = R.drawable.group5),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth(0.25f),
            contentScale = ContentScale.Crop
        )

        // Fondo decorativo inferior derecho
        Image(
            painter = painterResource(id = R.drawable.group6),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxWidth(0.25f),
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
fun EmailVerificationPreview() {
    EmailVerificationScreen(navController = rememberNavController())
}
