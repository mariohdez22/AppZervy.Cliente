package com.example.appzervycliente.Views.Cliente

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.FormTextField
import com.example.appzervycliente.DTOs.ClienteDTO
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.Services.Repository.ClientesRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resumeWithException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavHostController,
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var dui by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()
    val clientesRepository = ClientesRepository()

    // Detectar si el teclado está visible
    val imeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .imePadding() // Asegurar que el teclado no oculte el contenido
    ) {
        SignUpBackgroundImages(imeVisible)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 46.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (imeVisible) Arrangement.Top else Arrangement.Center
        ) {
            // Texto superior
            Text(
                text = "Crea una cuenta en Zervy",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = if (imeVisible) 40.dp else 72.dp) // Ajustar posición dinámica
            )

            // Espaciador para ajustar el formulario
            Spacer(modifier = Modifier.height(18.dp))

            // Formulario
            SignUnFormBody(
                name = name,
                onNameChange = { name = it },
                phone = phone,
                onPhoneChange = { phone = it },
                dui = dui,
                onDuiChange = { dui = it },
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it }
            )

            // Botón
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    isLoading = true
                    errorMessage = null
                    scope.launch {
                        val result = signUpUser(
                            name = name,
                            phone = phone,
                            dui = dui,
                            email = email,
                            password = password,
                            clientesRepository = clientesRepository
                        )
                        isLoading = false
                        if (result.success) {
                            navController.navigate(Routes.Email.route) {
                                popUpTo(Routes.RegistroPage.route) { inclusive = true }
                            }
                        } else {
                            errorMessage = result.message
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(vertical = 16.dp),
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
        }
    }
}


    suspend fun signUpUser(
    name: String,
    phone: String,
    dui: String,
    email: String,
    password: String,
    clientesRepository: ClientesRepository
): SignUpResult {
    return try {
        val authResult = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        val user = authResult.user

        if (user != null) {
            // Enviar el correo de verificación
            try {
                user.sendEmailVerification().await()
            } catch (e: Exception) {
                return SignUpResult(success = false, message = "Error al enviar el correo de verificación: ${e.message}")
            }

            // Crear el cliente en el backend
            val clienteDTO = ClienteDTO(
                nombres = name,
                celular = phone,
                correo = email,
                foto = "",
                dui = dui
            )

            val response = withContext(Dispatchers.IO) {
                clientesRepository.crearCliente(clienteDTO)
            }

            if (response.isSuccessful && response.body()?.success == true) {
                println("Cliente creado exitosamente en el backend.")
                return SignUpResult(success = true)
            } else {
                val message = response.body()?.message ?: "Error al registrar cliente en el backend"
                println("Error del backend: $message")
                return SignUpResult(success = false, message = message)
            }
        } else {
            println("Error: No se pudo obtener el usuario de Firebase Auth.")
            return SignUpResult(success = false, message = "No se pudo crear el usuario en Firebase")
        }
    } catch (e: Exception) {
        println("Error general durante el registro: ${e.message}")
        return SignUpResult(success = false, message = "Error durante el registro: ${e.message}")
    }
}



// Clase para representar el resultado del registro
data class SignUpResult(val success: Boolean, val message: String? = null)

// Extensión para convertir Task en suspender
suspend fun <T> com.google.android.gms.tasks.Task<T>.await(): T {
    return suspendCancellableCoroutine { continuation ->
        addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(task.result, null)
            } else {
                continuation.resumeWithException(task.exception ?: Exception("Error desconocido"))
            }
        }
    }
}

@Composable
fun SignUpBackgroundImages(imeVisible: Boolean) {
    // Animaciones para la imagen superior izquierda
    val animatedTopLeftSize by animateDpAsState(
        targetValue = if (imeVisible) 80.dp else 320.dp,
        animationSpec = tween(durationMillis = 300)
    )
    val animatedTopLeftPadding by animateDpAsState(
        targetValue = if (imeVisible) 8.dp else 16.dp,
        animationSpec = tween(durationMillis = 300)
    )

    // Animaciones para la imagen superior derecha
    val animatedTopRightSize by animateDpAsState(
        targetValue = if (imeVisible) 50.dp else 100.dp,
        animationSpec = tween(durationMillis = 300)
    )
    val animatedTopRightPadding by animateDpAsState(
        targetValue = if (imeVisible) 8.dp else 16.dp,
        animationSpec = tween(durationMillis = 100)
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen decorativa superior izquierda
        Image(
            painter = painterResource(id = R.drawable.group5),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-126).dp, y = (16).dp) // Asegurar que sobresalga ligeramente para cubrir el borde
                .size(animatedTopLeftSize) // Animar tamaño
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )

        // Imagen decorativa superior derecha
        Image(
            painter = painterResource(id = R.drawable.group4),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-26).dp, y = (16).dp) // Asegurar que sobresalga ligeramente para cubrir el borde
                .size(animatedTopRightSize) // Animar tamaño
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )
    }
}



@Composable
fun SignUnFormBody(
    name: String,
    onNameChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit,
    dui: String,
    onDuiChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit
) {
    // Campo de Nombre
    FormTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Nombre") },
        icon = painterResource(R.drawable.personaicon),
        sizeRoundedCorners = 42.dp,
        keyboardType = KeyboardType.Text,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .height(50.dp) // Ajustar altura
    )

    // Campo de Celular
    FormTextField(
        value = phone,
        onValueChange = onPhoneChange,
        label = { Text("Celular") },
        icon = painterResource(R.drawable.phoneicon),
        sizeRoundedCorners = 12.dp,
        keyboardType = KeyboardType.Phone,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .height(50.dp) // Ajustar altura
    )

    // Campo de DUI
    FormTextField(
        value = dui,
        onValueChange = onDuiChange,
        label = { Text("Documento de identidad") },
        icon = painterResource(R.drawable.calendaricon),
        sizeRoundedCorners = 12.dp,
        keyboardType = KeyboardType.Text,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .height(50.dp) // Ajustar altura
    )

    // Campo de Correo
    FormTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Correo") },
        icon = painterResource(R.drawable.emailicon),
        sizeRoundedCorners = 12.dp,
        keyboardType = KeyboardType.Email,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .height(50.dp) // Ajustar altura
    )

    // Campo de Contraseña
    FormTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Contraseña") },
        icon = painterResource(R.drawable.passwordicon),
        sizeRoundedCorners = 12.dp,
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .height(50.dp) // Ajustar altura
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
        )
    }
}



