package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.appzervycliente.Components.common.CustomDropdownMenu

@Composable
fun PerfilAgregarDireccionPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues()) // Para ajustar debajo del TopBar las opciones
    ) {
        AgregarDireccionTopBar()
        AgregarDireccionContent()
    }
}

@Composable
fun AgregarDireccionTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón de volver
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.LightGray)
                .size(40.dp)
                .clickable { /* Acción para volver atrás */ },
            contentAlignment = Alignment.Center
        ) {
            Text("<", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        // Título centrado
        Text(
            text = "Agregar Dirección",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        // Botón de ajustes (puedes reemplazarlo por otro icono o dejarlo vacío)
        Box(
            modifier = Modifier
                .size(40.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarDireccionContent() {
    var direccion1 by remember { mutableStateOf("") }
    var direccion2 by remember { mutableStateOf("") }
    var codigoPostal by remember { mutableStateOf("") }

    var selectedPais by remember { mutableStateOf("Seleccione país") }
    var selectedDepartamento by remember { mutableStateOf("Seleccione departamento") }
    var selectedCiudad by remember { mutableStateOf("Seleccione ciudad") }

    val paises = listOf("El Salvador", "Guatemala")
    val departamentos = listOf("San Salvador", "La Libertad")
    val ciudades = listOf("San Salvador", "Santa Tecla")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Dirección 1
        TextField(
            value = direccion1,
            onValueChange = { direccion1 = it },
            label = { Text("Dirección 1") },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray // Cambiado a containerColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Dirección 2
        TextField(
            value = direccion2,
            onValueChange = { direccion2 = it },
            label = { Text("Dirección 2") },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray // Cambiado a containerColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Código Postal
        TextField(
            value = codigoPostal,
            onValueChange = { codigoPostal = it },
            label = { Text("Código Postal") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray // Cambiado a containerColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Selección de País
        CustomDropdownMenu(
            options = paises,
            selectedOption = selectedPais,
            onOptionSelected = { selectedPais = it },
            label = "País",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Selección de Departamento
        CustomDropdownMenu(
            options = departamentos,
            selectedOption = selectedDepartamento,
            onOptionSelected = { selectedDepartamento = it },
            label = "Departamento",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Selección de Ciudad
        CustomDropdownMenu(
            options = ciudades,
            selectedOption = selectedCiudad,
            onOptionSelected = { selectedCiudad = it },
            label = "Ciudad",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Agregar Dirección
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFF6200EE), shape = RoundedCornerShape(50))
                .clickable { /* Acción para agregar dirección */ },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Agregar Dirección", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPerfilAgregarDireccionPage() {
    PerfilAgregarDireccionPage()
}
