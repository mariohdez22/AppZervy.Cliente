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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.FormTextField
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    navController: NavHostController,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Estados para manejar la carga y los errores
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Estado para mostrar el diálogo de restablecimiento de contraseña
    var showResetPasswordDialog by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

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
                onValueChange = { email = it },
                label = { Text("Email") },
                icon = painterResource(R.drawable.personaicon),
                sizeRoundedCorners = 16.dp,
                keyboardType = KeyboardType.Email,
                maxWidth = 0.85f
            )

            // Caja de texto para la contraseña
            FormTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                icon = painterResource(R.drawable.passwordicon),
                sizeRoundedCorners = 16.dp,
                maxWidth = 0.85f,
                visualTransformation = PasswordVisualTransformation()
            )

            // Texto de "¿Olvidaste tu contraseña?"
            TextButton(
                onClick = { showResetPasswordDialog = true },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            // Texto de "¿Aún no posees una cuenta?"
            TextButton(
                onClick = { navController.navigate(Routes.RegistroPage.route) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = "¿Aún no posees una cuenta?",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            // Mostrar mensaje de error si existe
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            // Botón de "Aceptar" con bordes redondeados
            Button(
                onClick = {
                    isLoading = true
                    errorMessage = null

                    scope.launch {
                        val result = signInUser(email, password)
                        isLoading = false
                        if (result.success) {
                            // Inicio de sesión exitoso, navegar a la página principal
                            navController.navigate(Routes.MainPage.route) {
                                popUpTo(Routes.LoginPage.route) { inclusive = true }
                            }
                        } else {
                            // Mostrar mensaje de error
                            errorMessage = result.message
                        }
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(0.5f), // Tamaño del botón ajustado
                shape = RoundedCornerShape(16.dp), // Bordes redondeados en el botón
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2)), // Color del botón
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
                } else {
                    Text(text = "Aceptar", color = Color.White)
                }
            }
        }

        // Mostrar el diálogo de restablecimiento de contraseña
        if (showResetPasswordDialog) {
            ResetPasswordDialog(
                onDismiss = { showResetPasswordDialog = false }
            )
        }
    }
}

// Función que maneja el inicio de sesión
suspend fun signInUser(email: String, password: String): SignInResult {
    return try {
        val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
        val user = authResult.user

        if (user != null) {
            // Obtener el ID Token
            val idTokenResult = user.getIdToken(true).await()
            val idToken = idTokenResult.token

            if (idToken != null) {
                // Aquí puedes almacenar el ID Token si es necesario
                // Por ejemplo, en un SessionManager
                SessionManager.idToken = idToken

                SignInResult(success = true)
            } else {
                SignInResult(success = false, message = "No se pudo obtener el ID Token")
            }
        } else {
            SignInResult(success = false, message = "No se pudo iniciar sesión")
        }
    } catch (e: Exception) {
        SignInResult(success = false, message = e.message ?: "Error desconocido")
    }
}

// Clase para representar el resultado del inicio de sesión
data class SignInResult(val success: Boolean, val message: String? = null)

// Objeto para gestionar el ID Token (si aún no lo tienes)
object SessionManager {
    var idToken: String? = null
}

// Función para restablecer la contraseña
suspend fun resetPassword(email: String): ResetPasswordResult {
    return try {
        Firebase.auth.sendPasswordResetEmail(email).await()
        ResetPasswordResult(success = true, message = "Correo de restablecimiento enviado con éxito.")
    } catch (e: Exception) {
        ResetPasswordResult(success = false, message = e.message ?: "Error desconocido")
    }
}

// Clase para representar el resultado del restablecimiento de contraseña
data class ResetPasswordResult(val success: Boolean, val message: String)

@Composable
fun ResetPasswordDialog(
    onDismiss: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Restablecer contraseña") },
        text = {
            Column {
                Text(text = "Ingresa tu correo electrónico para recibir instrucciones de restablecimiento de contraseña.")
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo electrónico") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                message?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = it, color = if (it.contains("éxito")) Color.Green else Color.Red)
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    isLoading = true
                    message = null
                    scope.launch {
                        val result = resetPassword(email)
                        isLoading = false
                        message = result.message
                        if (result.success) {
                            // Puedes cerrar el diálogo después de un tiempo si lo deseas
                            onDismiss()
                        }
                    }
                },
                enabled = !isLoading && email.isNotEmpty()
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.Gray,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Enviar")
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun LoginBackgroundImages() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
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
    }
}

@Composable
fun LoginTitleSection() {
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

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    AppZervyClienteTheme {
        Login(
            navController = rememberNavController(),
        )
    }
}
