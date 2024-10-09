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
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilDireccionesPage() {
    Scaffold(
        topBar = {
            DireccionesTopBar()
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues()) // Para ajustar debajo del TopBar
    ) {
        DireccionesContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DireccionesTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón de volver
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

        // Espaciador central
        Spacer(modifier = Modifier.weight(1f))

        // Botón de ajustes
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
fun DireccionesContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 75.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Título y subtítulo
        Text(
            text = "Direcciones",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Aquí podrás visualizar y agregar todas las ubicaciones en las cuales nuestros socios podrán visitarte.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        // Separador
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        // Botón de agregar dirección (ocupa todo el ancho)
        Button(
            onClick = { /* Acción para agregar dirección */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF0F0F0) // Fondo gris claro
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Star, // Cambié al ícono de estrella
                contentDescription = "Agregar Dirección",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Agregar Dirección",
                color = Color.Black,
                fontSize = 16.sp
            )
        }

        // Lista de direcciones
        Spacer(modifier = Modifier.height(16.dp))
        DireccionItem("Casa Hernandez", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium")
        DireccionItem("Casa Dominges", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium")
        DireccionItem("Departamento Rosales", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium")
    }
}

@Composable
fun DireccionItem(titulo: String, descripcion: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp) // Más espacio alrededor de los elementos
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = descripcion,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }

            // Botón de "Editar"
            TextButton(onClick = { /* Acción de editar */ }) {
                Text(text = "Editar", color = Color.Blue)
            }
        }
        Spacer(modifier = Modifier.height(8.dp)) // Separación antes del Divider
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPerfilDireccionesPage() {
    PerfilDireccionesPage()
}
