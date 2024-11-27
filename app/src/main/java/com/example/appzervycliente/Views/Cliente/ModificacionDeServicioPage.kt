package com.example.appzervycliente.views.servicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun ModificacionDeServicioPage(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Barra Superior
            SettingTopBar(
                scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
                onNavigationIconClick = { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Título
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Modificación de servicio",
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

            Spacer(modifier = Modifier.height(24.dp))

            // Datos Generales
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Datos generales",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconTextRow("Servicio", "Instalación Eléctrica")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconTextRow("Duración General", "Varios Días")
                }
            }


            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Cambios en la propuesta",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Cambios en la Propuesta
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF7F7F7),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    ChangeRow("Propuesta anterior", "Precio base", "$300 USD", "Duración", "3 Días")
                    Divider(color = Color.Gray, thickness = 0.5.dp, modifier = Modifier.padding(vertical = 8.dp))
                    ChangeRow("Propuesta actual", "Nuevo precio", "$350 USD", "Nueva duración", "4 Días")
                }
            }



            Spacer(modifier = Modifier.height(16.dp))

            // Tipo de Pago
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Tipo de pago",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.start_icon),
                        contentDescription = "Icono",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Tarjeta",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "En dado caso se rechace el servicio, no se cobrará la inspección realizada, ya que esta es totalmente gratuita para poder inspeccionar la residencia y determinar mejor el costo y duración del servicio.",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Divider()

            // Botones de Aceptar y Rechazar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Aceptar cambios */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3B82F6),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text("Aceptar Cambios")
                }
                Button(
                    onClick = { /* Rechazar cambios */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF87171),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text("Rechazar Cambios")
                }
            }
        }
    }
}

@Composable
fun IconTextRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.start_icon),
                contentDescription = "Icon",
                tint = Color.Black,
                modifier = Modifier.size(16.dp)
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
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun ChangeRow(title: String, label1: String, value1: String, label2: String, value2: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(label1, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                Text(value1, fontSize = 12.sp, fontWeight = FontWeight.Normal)
            }
            Column {
                Text(label2, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, )
                Text(value2, fontSize = 12.sp, fontWeight = FontWeight.Normal)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModificacionDeServicioPreview() {
    ModificacionDeServicioPage(rememberNavController())
}
