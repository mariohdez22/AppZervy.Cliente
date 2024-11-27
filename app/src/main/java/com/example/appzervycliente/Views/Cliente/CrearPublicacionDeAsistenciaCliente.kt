package com.example.appzervycliente.Views.Cliente

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.appzervycliente.Components.common.SettingTopBar
import com.example.appzervycliente.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearPublicacionDeAsistenciaClientePage(
    navController: NavHostController
) {
    var titulo by remember { mutableStateOf("") }
    var comentario by remember { mutableStateOf("") }
    var motivoSeleccionado by remember { mutableStateOf("") }
    var servicioSeleccionado by remember { mutableStateOf("") }
    val imagenes = remember { mutableStateListOf<Uri>() }

    val permissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        // Manejo de resultados de permisos
        val allGranted = permissions.values.all { it }
        if (!allGranted) {
            // Mostrar un mensaje o realizar alguna acción si los permisos son denegados
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imagenes.add(it) }
    }

    // Solicitar permisos dependiendo de la versión de Android
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionsLauncher.launch(
                arrayOf(
                    Manifest.permission.READ_MEDIA_IMAGES
                )
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionsLauncher.launch(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Barra Superior
            SettingTopBar(
                scrollBehavior = null,
                onNavigationIconClick = { navController.popBackStack() }
            )

            // Título principal
            Text(
                text = "Soporte Técnico",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de entrada
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título Publicación") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = comentario,
                onValueChange = { comentario = it },
                label = { Text("Comentario") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 4
            )

            // Dropdowns
            DropdownMenuField(
                label = "Motivo Publicación",
                opciones = listOf("Mal Servicio", "Inconformidad", "Precio Erróneo"),
                seleccion = motivoSeleccionado,
                onSeleccionChange = { motivoSeleccionado = it }
            )

            DropdownMenuField(
                label = "Servicio del cual se está inconforme",
                opciones = listOf("Servicio A", "Servicio B", "Servicio C"),
                seleccion = servicioSeleccionado,
                onSeleccionChange = { servicioSeleccionado = it }
            )

            // Selector de imágenes
            Text(
                text = "Adjuntar fotos de la evidencia",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(imagenes.size) { index ->
                    Image(
                        painter = rememberAsyncImagePainter(imagenes[index]),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.Gray, RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                item {
                    IconButton(
                        onClick = { imagePickerLauncher.launch("image/*") },
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color(0xFFF7F7F7), RoundedCornerShape(8.dp))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Añadir Imagen",
                            tint = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de enviar
            Button(
                onClick = { /* Acción para enviar la publicación */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3B82F6),
                    contentColor = Color.White
                )
            ) {
                Text("Publicar")
            }
        }
    }
}

@Composable
fun DropdownMenuField(label: String, opciones: List<String>, seleccion: String, onSeleccionChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = seleccion,
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null
                    )
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(onClick = {
                    onSeleccionChange(opcion)
                    expanded = false
                }, text = { Text(opcion) })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CrearPublicacionDeAsistenciaClientePreview() {
    CrearPublicacionDeAsistenciaClientePage(rememberNavController())
}
