package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.ui.unit.Dp

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
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val imeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    val animatedPaddingTop by animateDpAsState(
        targetValue = if (imeVisible) 16.dp else 64.dp,
        animationSpec = tween(durationMillis = 300)
    )

    val animatedLogoSize by animateDpAsState(
        targetValue = if (imeVisible) 150.dp else 200.dp,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .imePadding()
            .padding(bottom = 8.dp)
    ) {
        LoginBackgroundImages(imeVisible)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = if (imeVisible) 8.dp else 0.dp),
            verticalArrangement = if (imeVisible) Arrangement.Top else Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginTitleSection(animatedPaddingTop, animatedLogoSize)

            // Formulario
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FormTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    icon = painterResource(R.drawable.personaicon),
                    sizeRoundedCorners = 36.dp,
                    keyboardType = KeyboardType.Email,
                    maxWidth = 0.95f
                )

                Spacer(modifier = Modifier.height(8.dp))

                FormTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    icon = painterResource(R.drawable.passwordicon),
                    sizeRoundedCorners = 36.dp,
                    maxWidth = 0.95f,
                    visualTransformation = PasswordVisualTransformation()
                )

                TextButton(
                    onClick = {
                        scope.launch {
                            sendPasswordResetEmail(email, context)
                        }
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = "¿Olvidaste tu contraseña?",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }

            // Botón de inicio de sesión
            Button(
                onClick = {
                    isLoading = true
                    errorMessage = null

                    scope.launch {
                        val result = signInUser(email, password)
                        isLoading = false
                        if (result.success) {
                            navController.navigate(Routes.Welcome.route) {
                                popUpTo(Routes.LoginPage.route) { inclusive = true }
                                popUpTo(Routes.InicioPage.route) { inclusive = true }
                            }
                        } else {
                            errorMessage = result.message
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(vertical = 0.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2)),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
                } else {
                    Text(text = "Aceptar", color = Color.White)
                }
            }

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
        }

        // Mostrar el Toast si hay error
        errorMessage?.let { message ->
            LaunchedEffect(message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                errorMessage = null
            }
        }
    }
}

// Función para enviar correo de recuperación de contraseña
suspend fun sendPasswordResetEmail(email: String, context: android.content.Context) {
    if (email.isEmpty()) {
        Toast.makeText(context, "Por favor, ingresa un correo electrónico.", Toast.LENGTH_SHORT).show()
        return
    }

    try {
        Firebase.auth.sendPasswordResetEmail(email).await()
        Toast.makeText(context, "Correo de recuperación enviado a $email.", Toast.LENGTH_LONG).show()
    } catch (e: Exception) {
        Toast.makeText(context, "Error: ${e.localizedMessage ?: "No se pudo enviar el correo."}", Toast.LENGTH_LONG).show()
    }
}


@Composable
fun LoginTitleSection(paddingTop: Dp, logoSize: Dp) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = paddingTop)
    ) {
        Image(
            painter = painterResource(id = R.drawable.union),
            contentDescription = null,
            modifier = Modifier.size(logoSize)
        )
        Text(
            text = "Inicia Sesión en Zervy",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

// Función que maneja el inicio de sesión
suspend fun signInUser(email: String, password: String): SignInResult {
    return try {
        val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
        val user = authResult.user

        if (user != null) {
            if (user.isEmailVerified) { // Validar si el correo está verificado
                // Obtener el ID Token
                val idTokenResult = user.getIdToken(true).await()
                val idToken = idTokenResult.token

                if (idToken != null) {
                    // Aquí puedes almacenar el ID Token si es necesario
                    SessionManager.idToken = idToken

                    SignInResult(success = true)
                } else {
                    SignInResult(success = false, message = "No se pudo obtener el ID Token")
                }
            } else {
                SignInResult(success = false, message = "El correo no ha sido verificado. Por favor, verifica tu correo antes de iniciar sesión.")
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
fun LoginBackgroundImages(imeVisible: Boolean) {
    // Animar tamaños de las imágenes de fondo
    val animatedLeftImageSize by animateDpAsState(
        targetValue = if (imeVisible) 80.dp else 360.dp,
        animationSpec = tween(durationMillis = 300)
    )
    val animatedRightImageSize by animateDpAsState(
        targetValue = if (imeVisible) 80.dp else 360.dp,
        animationSpec = tween(durationMillis = 300)
    )
    val animatedUndertImageSize by animateDpAsState(
        targetValue = if (imeVisible) 80.dp else 360.dp,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Fondo decorativo superior izquierdo
        Image(
            painter = painterResource(id = R.drawable.group5),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-136).dp, y = (46).dp) // Asegurar que sobresalga ligeramente para cubrir el borde
                .size(animatedLeftImageSize) // Animar tamaño
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )

        // Fondo decorativo superior derecho
        Image(
            painter = painterResource(id = R.drawable.group6),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 136.dp, y = (46).dp) // Asegurar que sobresalga ligeramente para cubrir el borde
                .size(animatedRightImageSize) // Animar tamaño
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )
        Image(
            painter = painterResource(id = R.drawable.group3),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-26).dp, y = (700).dp) // Asegurar que sobresalga ligeramente para cubrir el borde
                .size(animatedUndertImageSize) // Animar tamaño
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )
    }
}



@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    AppZervyClienteTheme {
        Login(navController = rememberNavController())
    }
}
