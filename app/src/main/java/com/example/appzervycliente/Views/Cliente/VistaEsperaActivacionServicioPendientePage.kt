package com.example.appzervycliente.views.servicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
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
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaEsperaActivacionServicioPendientePage(
    navController: NavHostController
) {
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
                    text = "Servicio Pendiente",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "El servicio permanece pendiente hasta el día de la ejecución del mismo, donde es activada la ubicación de la residencia.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Start
                )
            }

            Divider()

            // Datos generales
            SectionTitle("Datos generales")
            GeneralDataRow("Estado del servicio", "Pendiente")
            GeneralDataRow("Duración del servicio", "5 Días")
            GeneralDataRow("Fecha de Inicio", "20 de Marzo del 2024")
            GeneralDataRow("Fecha de Finalización", "25 de Marzo del 2024")

            Divider()

            // Datos del servicio
            SectionTitle("Datos del servicio")
            GeneralDataRow("Servicio", "Instalación Eléctrica")
            GeneralDataRow("Duración General", "Varios Días")
            GeneralDataRow("Precio Base", "$ 350 USD")

            Divider()

            // Seguimiento del servicio
            SectionTitle("Seguimiento del servicio")
            GeneralDataRow("Días Transcurridos", "2 Días", hasBackground = true)
            GeneralDataRow("Fecha Actual", "22 de Marzo del 2024", hasBackground = true)

            // Nota informativa
            Text(
                text = "Para que el servicio vuelva a activarse, tienes que esperar a que el socio seleccione la activación del servicio para el día de hoy, y en dado caso el socio por algún motivo no reanude el servicio, se procederá a cancelar el servicio, y no se te cobrará nada por la cancelación, y el socio será sancionado.",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun GeneralDataRow(label: String, value: String, hasBackground: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            androidx.compose.material3.Icon(
                painter = painterResource(id = R.drawable.start_icon),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            modifier = if (hasBackground) {
                Modifier
                    .background(
                        color = Color(0xFFF1F1F1), // Color gris claro
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            } else Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VistaEsperaActivacionServicioPendientePreview() {
    AppZervyClienteTheme {
        VistaEsperaActivacionServicioPendientePage(rememberNavController())
    }
}
