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
import com.example.appzervycliente.Components.common.FormTextField
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavHostController,
) {
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

            //Formulario
            SignUnFormBody()

            // Texto "¿Ya posees una cuenta?"
            TextButton(
                onClick = { navController.navigate(Routes.MainPage.route) },
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
                onClick = { navController.navigate(Routes.LoginPage.route) },
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
//        Box(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            // Imagen decorativa inferior (group3)
//            Image(
//                painter = painterResource(id = R.drawable.group3),
//                contentDescription = null,
//                modifier = Modifier
//                    .align(Alignment.BottomCenter)
//                    .fillMaxWidth()
//                    .height(150.dp)
//                    .offset(y = 30.dp),
//                contentScale = ContentScale.FillWidth
//            )
//        }
    }
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

@Composable
fun SignUpBackgroundImages(){

    Box(
        modifier = Modifier.fillMaxWidth()
    ){
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

){
    // Variables de estado para los campos de texto
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Campo de Nombre
    FormTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text("Nombre") },
        icon = painterResource(R.drawable.personaicon),
        sizeRoundedCorners = 16.dp,
        keyboardType = KeyboardType.Text,
    )

    FormTextField(
        value = phone,
        onValueChange = { phone = it },
        label = { Text("Celular") },
        icon = painterResource(R.drawable.phoneicon),
        sizeRoundedCorners = 16.dp,
        keyboardType = KeyboardType.Phone
    )

    FormTextField(
        value = birthDate,
        onValueChange = { birthDate = it },
        label = { Text("Fecha de nacimiento") },
        icon = painterResource(R.drawable.calendaricon),
        sizeRoundedCorners = 16.dp,
        keyboardType = KeyboardType.Text
    )

    FormTextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Correo") },
        icon = painterResource(R.drawable.emailicon),
        sizeRoundedCorners = 16.dp,
        keyboardType = KeyboardType.Email
    )

    FormTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Contraseña") },
        icon = painterResource(R.drawable.passwordicon),
        sizeRoundedCorners = 16.dp,
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation()
    )

}


