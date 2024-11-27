package com.example.appzervycliente.Views.Cliente


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun AsistenciaAlClientePage(
    navController: NavHostController
) {
    val preguntasFrecuentes = listOf(
        "Sed ut perspiciatis unde omnis",
        "Doloremque laudantium",
        "Totam rem aperiam",
        "Inventore veritatis et",
        "Quasi architecto beatae",
        "Sed quia consequop"
    )

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

            // Preguntas Frecuentes
            Text(
                text = "Preguntas Frecuentes",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(preguntasFrecuentes) { pregunta ->
                    PreguntaFrecuenteItem(pregunta)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Opciones de asistencia
            Text(
                text = "Opciones de asistencia",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OpcionAsistenciaItem(
                    texto = "Publicar inconformidad sobre algún servicio",
                    icono = R.drawable.start_icon
                )
                OpcionAsistenciaItem(
                    texto = "Solicitar asistencia por chat",
                    icono = R.drawable.start_icon
                )
            }
        }
    }
}

@Composable
fun PreguntaFrecuenteItem(pregunta: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F7F7), shape = MaterialTheme.shapes.small)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = pregunta,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Opciones",
            tint = Color.Gray
        )
    }
}

@Composable
fun OpcionAsistenciaItem(texto: String, icono: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = icono),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = texto,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Opciones",
            tint = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AsistenciaAlClientePagePreview() {
    AsistenciaAlClientePage(rememberNavController())
}
