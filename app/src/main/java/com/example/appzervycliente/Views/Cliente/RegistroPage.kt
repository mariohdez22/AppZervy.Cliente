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
import androidx.compose.ui.platform.LocalContext
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
    // Variables de estado para los campos del formulario
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var dui by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Estados para manejar la carga y los errores
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val clientesRepository = ClientesRepository() // Instancia del repositorio

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SignUpBackgroundImages()

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

            // Texto "¿Ya posees una cuenta?"
            TextButton(
                onClick = { navController.navigate(Routes.LoginPage.route) },
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
                onClick = {
                    isLoading = true
                    errorMessage = null

                    // Iniciar el proceso de registro
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
                            // Registro exitoso, navegar a la página principal
                            navController.navigate(Routes.MainPage.route) {
                                popUpTo(Routes.MainPage.route) { inclusive = true }
                            }
                        } else {
                            // Mostrar mensaje de error
                            errorMessage = result.message
                        }
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(0.5f),
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

            // Mostrar mensaje de error si existe
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

// Función que maneja el registro de usuario
suspend fun signUpUser(
    name: String,
    phone: String,
    dui: String,
    email: String,
    password: String,
    clientesRepository: ClientesRepository
): SignUpResult {
    return try {
        // Crear el usuario en Firebase Authentication
        val authResult = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        val user = authResult.user

        if (user != null) {
            // Obtener el ID Token
            val idTokenResult = user.getIdToken(true).await()
            val idToken = idTokenResult.token

            if (idToken != null) {
                // Crear el cliente en la API
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
                    SignUpResult(success = true)
                } else {
                    val message = response.body()?.message ?: "Error al crear el cliente"
                    SignUpResult(success = false, message = message)
                }
            } else {
                SignUpResult(success = false, message = "No se pudo obtener el ID Token")
            }
        } else {
            SignUpResult(success = false, message = "No se pudo crear el usuario")
        }
    } catch (e: Exception) {
        SignUpResult(success = false, message = e.message ?: "Error desconocido")
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
fun SignUpBackgroundImages() {
    Box(
        modifier = Modifier.fillMaxWidth()
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
        sizeRoundedCorners = 16.dp,
        keyboardType = KeyboardType.Text,
    )

    // Campo de Celular
    FormTextField(
        value = phone,
        onValueChange = onPhoneChange,
        label = { Text("Celular") },
        icon = painterResource(R.drawable.phoneicon),
        sizeRoundedCorners = 16.dp,
        keyboardType = KeyboardType.Phone
    )

    // Campo de DUI
    FormTextField(
        value = dui,
        onValueChange = onDuiChange,
        label = { Text("Documento de identidad") },
        icon = painterResource(R.drawable.calendaricon),
        sizeRoundedCorners = 16.dp,
        keyboardType = KeyboardType.Text
    )

    // Campo de Correo
    FormTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Correo") },
        icon = painterResource(R.drawable.emailicon),
        sizeRoundedCorners = 16.dp,
        keyboardType = KeyboardType.Email
    )

    // Campo de Contraseña
    FormTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Contraseña") },
        icon = painterResource(R.drawable.passwordicon),
        sizeRoundedCorners = 16.dp,
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation()
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



