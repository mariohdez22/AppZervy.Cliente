package com.example.appzervycliente.Views.Cliente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.FormTextField
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    navController: NavHostController,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        LoginBackgroundImages()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center)
                .offset(y = (-50).dp)
        ) {

            LoginTitleSection()

            // Caja de texto para el email
            FormTextField(
                value = email,
                onValueChange = {email = it},
                label = { Text("Email") },
                icon = painterResource(R.drawable.personaicon),
                sizeRoundedCorners = 16.dp,
                keyboardType = KeyboardType.Email,
                maxWidth = 0.85f
            )

            // Caja de texto para la contraseña
            FormTextField(
                value = password,
                onValueChange = { password = it},
                label = { Text("Contraseña") },
                icon = painterResource(R.drawable.passwordicon),
                sizeRoundedCorners = 16.dp,
                maxWidth = 0.85f,
                visualTransformation = PasswordVisualTransformation()
            )

            // Texto de "¿Aún no posees una cuenta?"
            TextButton(
                onClick = { navController.navigate(Routes.RegistroPage.route) },
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(
                    text = "¿Aún no posees una cuenta?",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            // Botón de "Aceptar" con bordes redondeados
            Button(
                onClick = {
                    navController.navigate(Routes.MainPage.route)
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(0.5f), // Tamaño del botón ajustado
                shape = RoundedCornerShape(16.dp), // Bordes redondeados en el botón
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2)) // Color del botón
            ) {
                Text(text = "Aceptar", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    AppZervyClienteTheme {
        Login(
            navController = rememberNavController(),
        )
    }
}

@Composable
fun LoginBackgroundImages(){

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){
        // Fondo decorativo con imágenes
        Image(
            painter = painterResource(id = R.drawable.group5),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(0.dp)
                .fillMaxWidth(0.25f),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.group6),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(0.dp)
                .fillMaxWidth(0.2f),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun LoginTitleSection(){

    // Logo centrado
    Image(
        painter = painterResource(id = R.drawable.union),
        contentDescription = null,
        modifier = Modifier
            .size(200.dp)
            .padding(top = 10.dp) // Reducimos el margen superior
    )

    // Texto de bienvenida
    Text(
        text = "Inicia Sesión en Zervy",
        fontSize = 18.sp,
        color = Color.Black,
        modifier = Modifier.padding(top = 16.dp)
    )

}