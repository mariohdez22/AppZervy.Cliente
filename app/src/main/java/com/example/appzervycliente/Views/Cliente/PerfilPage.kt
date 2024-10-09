// Hecho por Michael, sustituir los datos cuando esten las APIs

package com.example.appzervycliente.Views.Cliente

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appzervycliente.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilPage() {
    Scaffold(
        topBar = {
            // Creamos una barra superior personalizada con íconos redondeados
            CustomTopBar()
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues()) // Esto asegura que el contenido esté debajo del TopBar
    ) {
        ProfileContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón de volver (ícono redondeado)
        IconButton(
            onClick = { /* Acción para volver atrás */ },
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.LightGray)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Atrás",
                tint = Color.Black
            )
        }

        // Espaciado central vacío
        Spacer(modifier = Modifier.weight(1f))

        // Botón de ajustes (ícono redondeado)
        IconButton(
            onClick = { /* Acción de ajustes */ },
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.LightGray)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Ajustes",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun ProfileContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Cabecera del perfil
        UserProfileHeader(
            userName = "Mario Hernandez",
            userEmail = "herzmariohdez@gmail.com"
        )

        // Separador (línea)
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 1.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        // Botones de acciones (con estrellas)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(4) {
                // Cada estrella con fondo gris en su propia caja
                Box(
                    modifier = Modifier
                        .size(60.dp) // Tamaño de la caja alrededor de la estrella
                        .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(8.dp)), // Fondo gris claro con bordes redondeados
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.start_icon),
                        contentDescription = "Botón de Estrella",
                        modifier = Modifier.size(30.dp), // Tamaño de la estrella
                        tint = Color.Gray // Color de la estrella
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de elementos placeholder
        Column(modifier = Modifier.fillMaxWidth()) {
            repeat(5) {
                PlaceholderItem()
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun UserProfileHeader(userName: String, userEmail: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen de perfil
        androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.avatar3dimage),
            contentDescription = "Imagen de Perfil",
            modifier = Modifier
                .size(125.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre del usuario
        Text(
            text = userName,
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )

        // Correo del usuario
        Text(
            text = userEmail,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
fun PlaceholderItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPerfilPage() {
    PerfilPage()
}
