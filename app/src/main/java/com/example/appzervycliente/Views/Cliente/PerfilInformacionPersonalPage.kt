// Hecho por Michael, sustituir los datos cuando esten las APIs

package com.example.appzervycliente.Views.Cliente

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilInformacionPersonalPage() {
    Scaffold(
        topBar = {
            // Cambié el nombre para evitar el conflicto
            InformacionTopBar()
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues()) // Esto asegura que el contenido esté debajo del TopBar
    ) {
        InformacionPersonalContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformacionTopBar() { // Nuevo nombre de la barra superior
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
fun InformacionPersonalContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 75.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Título y subtítulo
        Text(
            text = "Información Personal",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium",
            fontSize = 14.sp,
            color = Color.Gray
        )

        // Separador
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        // Secciones de información con botón de "Editar"
        InformacionPersonalSection("Nombre de Usuario", "Mario Oscar Hernandez Hernandez")
        InformacionPersonalSection("Contraseña", "Contraseña actual Ty****************")
        InformacionPersonalSection("Celular", "7345 - 6544")
        InformacionPersonalSection("Documento civil", "89344565 - 5")
        InformacionPersonalSection("Email", "herzmariohdez@gmail.com")
    }
}

@Composable
fun InformacionPersonalSection(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = value,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        // Botón de "Editar"
        TextButton(onClick = { /* Acción de editar */ }) {
            Text(text = "Editar", color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilPreviewInformacionPersonalPage() {
    PerfilInformacionPersonalPage()
}
