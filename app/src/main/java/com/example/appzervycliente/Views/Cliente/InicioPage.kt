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


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                // Imagen decorativa superior izquierda (group1)
                Image(
                    painter = painterResource(id = R.drawable.group5),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(0.dp)
                        .fillMaxWidth(0.25f),  // Ajustar al tamaño deseado
                    contentScale = ContentScale.Crop
                )

                // Imagen decorativa superior derecha (group2)
                Image(
                    painter = painterResource(id = R.drawable.group6),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(0.dp)
                        .fillMaxWidth(0.2f),
                    contentScale = ContentScale.Crop
                )

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
                            .padding(top = 24.dp) // Ajuste del margen superior
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // Botón Iniciar Sesión
                    Button(
                        onClick = onLoginClick,
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
                        onClick = onSignUpClick,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF7E57C2)),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(48.dp)
                    ) {
                        Text("Registrarse", color = Color(0xFF7E57C2), fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Imagen decorativa inferior (group3)
                        Image(
                            painter = painterResource(id = R.drawable.group3),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)  // Alineado al centro inferior dentro de un Box
                                .fillMaxWidth()                  // Ocupa todo el ancho disponible
                                .height(200.dp)                 // Ajuste del tamaño para que sobresalga correctamente
                                .offset(y = 80.dp),             // Mueve la imagen hacia arriba en lugar de usar padding negativo
                            contentScale = ContentScale.FillWidth // Ajuste para llenar el ancho
                        )
                    }
                }
            }
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
