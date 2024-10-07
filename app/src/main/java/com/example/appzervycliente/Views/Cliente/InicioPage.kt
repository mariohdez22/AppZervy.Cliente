package com.example.appzervycliente.Views.Cliente


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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    // Variables de estado para los campos de texto
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.White)
            ) {
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

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Imagen decorativa inferior (group3)
                    Image(
                        painter = painterResource(id = R.drawable.group3),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .height(150.dp)
                            .offset(y = 30.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.Center)
                        .offset(y = (-50).dp)
                ) {
                    // Logo
                    Image(
                        painter = painterResource(id = R.drawable.union),
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)
                            .padding(top = 10.dp)
                    )

                    // Texto de bienvenida
                    Text(
                        text = "Inicia Sesión en Zervy",
                        fontSize = 24.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    // Campo de Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.personaicon),
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .padding(top = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )

                    // Campo de Contraseña
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.emailicon),
                                contentDescription = null
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .padding(top = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )



                    // Botón de "Aceptar"
                    Button(
                        onClick = onLoginClick,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(0.5f),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2))
                    ) {
                        Text(text = "Aceptar", color = Color.White)
                    }

                    // Botón de "Registrarse"
                    TextButton(
                        onClick = onSignUpClick,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(text = "Registrarse")
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    AppZervyClienteTheme(
        dynamicColor = true
    ) {
        LoginScreen(
            navController = rememberNavController(),
            onLoginClick = {},
            onSignUpClick = {}
        )
    }
}
