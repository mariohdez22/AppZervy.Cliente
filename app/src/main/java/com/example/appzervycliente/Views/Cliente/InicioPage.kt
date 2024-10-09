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
import com.example.appzervycliente.Routes.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        InicioBackgroundImages()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(150.dp))

            // Logo (group4.png)
            Image(
                painter = painterResource(id = R.drawable.group4),
                contentDescription = null,
                modifier = Modifier
                    .size(258.dp, 153.dp)
                    .padding(top = 24.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            InicioBodySection(
                onLogin = {
                    navController.navigate(Routes.LoginPage.route)
                },
                onRegister = {
                    navController.navigate(Routes.RegistroPage.route)
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            InicioBottomBackgroundImages()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    AppZervyClienteTheme(
        dynamicColor = true
    ) {
        LoginScreen(rememberNavController())
    }
}


@Composable
fun InicioBodySection(
    onLogin: () -> Unit,
    onRegister: () -> Unit
){

    // Botón Iniciar Sesión
    Button(
        onClick = onLogin,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2)), // Color ajustado
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(48.dp)
    ) {
        Text("Iniciar Sesión", color = Color.White, fontSize = 16.sp)
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Botón Registrarse
    OutlinedButton(
        onClick = onRegister,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF7E57C2)),
        shape = RoundedCornerShape(50),
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(48.dp)
    ) {
        Text("Registrarse", color = Color(0xFF7E57C2), fontSize = 16.sp)
    }

}

@Composable
fun InicioBackgroundImages(){

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
fun InicioBottomBackgroundImages(){

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
                .height(200.dp)
                .offset(y = 80.dp),
            contentScale = ContentScale.FillWidth
        )
    }

}

