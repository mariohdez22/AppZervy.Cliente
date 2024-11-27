package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.SettingTopBar
import com.example.appzervycliente.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublicacionDeConsultasAsistenciaCliente(
    navController: NavHostController
) {
    val publicaciones = remember {
        listOf(
            Publicacion(
                titulo = "Disgusto con el servicio realizado",
                estado = "Resuelta",
                fecha = "11 de septiembre 2024",
                descripcion = "Sed ut perspiciatis unde omnis. perspiciatis unde omnis..."
            ),
            Publicacion(
                titulo = "Disgusto con el servicio realizado",
                estado = "Resuelta",
                fecha = "11 de septiembre 2024",
                descripcion = "Sed ut perspiciatis unde omnis. perspiciatis unde omnis..."
            ),
            Publicacion(
                titulo = "Disgusto con el servicio realizado",
                estado = "Resuelta",
                fecha = "11 de septiembre 2024",
                descripcion = "Sed ut perspiciatis unde omnis. perspiciatis unde omnis..."
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Barra Superior
            SettingTopBar(
                scrollBehavior = null,
                onNavigationIconClick = { navController.popBackStack() }
            )

            // Título principal
            Column {
                Text(
                    text = "Asistencia y soporte",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de nueva publicación
            Button(
                onClick = { /* Acción para crear nueva publicación */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3B82F6),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Crear publicación de inconformidad")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Filtros
            FiltrosPublicacion()

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de publicaciones
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(publicaciones) { publicacion ->
                    PublicacionItem(publicacion)
                }
            }
        }
    }
}

@Composable
fun FiltrosPublicacion() {
    val filtros = listOf("Resueltas", "Pendientes", "Canceladas")
    val categorias = listOf("Mal Servicio", "Inconformidad", "Precio Erróneo")

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Filtros principales
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            filtros.forEachIndexed { index, filtro ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (index == 0) {
                        Icon(
                            painter = painterResource(id = R.drawable.start_icon),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Text(
                        text = filtro,
                        fontSize = 16.sp,
                        fontWeight = if (index == 0) FontWeight.Bold else FontWeight.Medium,
                        color = if (index == 0) Color.Black else Color.Gray,
                        modifier = Modifier.padding(start = if (index == 0) 4.dp else 0.dp)
                    )
                }
            }
        }

        // Categorías secundarias
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            categorias.forEach { categoria ->
                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFF7F7F7),
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = categoria,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun PublicacionItem(publicacion: Publicacion) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F7F7), shape = MaterialTheme.shapes.small)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = publicacion.titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.start_icon),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${publicacion.estado} - ${publicacion.fecha}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Opciones",
                tint = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = publicacion.descripcion,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = Color.Gray
        )
    }
}

data class Publicacion(
    val titulo: String,
    val estado: String,
    val fecha: String,
    val descripcion: String
)

@Preview(showBackground = true)
@Composable
fun PublicacionDeConsultasAsistenciaClientePreview() {
    PublicacionDeConsultasAsistenciaCliente(rememberNavController())
}
