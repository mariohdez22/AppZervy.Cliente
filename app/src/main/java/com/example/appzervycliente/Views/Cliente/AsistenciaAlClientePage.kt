package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
        "¿Cómo puedo cambiar mi dirección de servicio?" to "Puedes cambiar tu dirección desde tu perfil en la sección de direcciones.",
        "¿Qué métodos de pago están disponibles?" to "Aceptamos tarjetas de crédito, débito y pagos en efectivo al finalizar el servicio.",
        "¿Cómo puedo cancelar un servicio agendado?" to "Puedes cancelar el servicio desde la sección de tus servicios activos en la aplicación.",
        "¿Cuánto tiempo tarda en confirmarse un servicio?" to "La confirmación puede tomar hasta 24 horas dependiendo del socio.",
        "¿Puedo solicitar un reembolso?" to "Sí, los reembolsos están sujetos a los términos y condiciones de cada servicio."
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Permite que toda la vista sea desplazable
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
                    text = "Encuentra respuestas a tus preguntas frecuentes o solicita ayuda personalizada.",
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
            preguntasFrecuentes.forEach { (pregunta, respuesta) ->
                PreguntaFrecuenteItem(pregunta, respuesta)
                Spacer(modifier = Modifier.height(0.dp))
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
fun PreguntaFrecuenteItem(pregunta: String, respuesta: String) {
    var expandida by remember { mutableStateOf(false) }

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
            Text(
                text = pregunta,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            IconButton(onClick = { expandida = !expandida }) {
                Icon(
                    imageVector = if (expandida) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = "Expandir o contraer",
                    tint = Color.Gray
                )
            }
        }
        if (expandida) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = respuesta,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )
        }
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
    }
}

@Preview(showBackground = true)
@Composable
fun AsistenciaAlClientePagePreview() {
    AsistenciaAlClientePage(rememberNavController())
}
