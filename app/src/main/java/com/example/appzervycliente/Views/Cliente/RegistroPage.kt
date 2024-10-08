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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavHostController,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    // Variables de estado para los campos de texto
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
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
                        .fillMaxWidth(0.20f),
                    contentScale = ContentScale.Crop
                )
                // Logo pequeño en la esquina superior derecha
                Image(
                    painter = painterResource(id = R.drawable.group4),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(99.dp)
                        .padding(end = 20.dp, top = 16.dp)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .align(Alignment.Center)
                ) {
                    // Texto de bienvenida
                    Text(
                        text = "Crea una cuenta en Zervy",
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 40.dp)
                    )

                    // Campo de Nombre
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nombre") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.personaicon),
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    // Campo de Celular
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Celular") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.phoneicon),
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    // Campo de Fecha de Nacimiento
                    OutlinedTextField(
                        value = birthDate,
                        onValueChange = { birthDate = it },
                        label = { Text("Fecha de nacimiento") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.calendaricon),
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    // Campo de Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Correo") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.emailicon),
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
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
                                painter = painterResource(id = R.drawable.passwordicon),
                                contentDescription = null
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        ),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    // Texto "¿Ya posees una cuenta?"
                    TextButton(
                        onClick = onLoginClick,
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(
                            text = "¿Ya posees una cuenta?",
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }

                    // Botón "Aceptar"
                    Button(
                        onClick = onSignUpClick,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(0.5f),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2))
                    ) {
                        Text(text = "Aceptar", color = Color.White)
                    }
                }

                // Imagen decorativa inferior
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
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RegistroPreview() {
    AppZervyClienteTheme(
        dynamicColor = true
    ) {
        SignUpScreen(
            navController = rememberNavController(),
            onSignUpClick = {},
            onLoginClick = {}
        )
    }
}


