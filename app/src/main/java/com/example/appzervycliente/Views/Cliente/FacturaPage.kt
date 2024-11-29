package com.example.appzervycliente.Views.Cliente

import android.content.ClipData.Item
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.IconLabelHorizontalSection
import com.example.appzervycliente.Components.common.TextHorizontalSection
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacturaPage(
    navController: NavHostController
) {

    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(navController, scrollBehavior) },
        containerColor = Color.White
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(top = paddingValues.calculateTopPadding(), bottom = 25.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Header()
            PagoServicio()
            Body()
            MetodoPago()
            HorizontalDivider(thickness = 1.dp)
            Opciones()
            Derechos()
        }
    }

}

@Composable
@Preview(showBackground = false)
private fun Preview(){

    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        FacturaPage(rememberNavController())
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior
){
    TopAppBar(
        title = { Text("") },
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(R.color.btnCarritoTopBar)
                ),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = Color.Black
                )
            }
        },
        actions = {
            IconButton(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(R.color.btnCarritoTopBar)
                ),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "settings",
                    tint = Color.Black
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            scrolledContainerColor = Color.White
        ),
        windowInsets = WindowInsets(left = 25.dp, right = 25.dp, top = 23.dp),
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun Header(){

    Column(
        modifier = Modifier
            .padding(top = 20.dp,start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Factura de servicio",
            fontSize = 25.sp,
            fontWeight = FontWeight.W400,
        )
    }

}

@Composable
private fun PagoServicio(){

    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
    ){
        Image(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(0.35f)
                .align(Alignment.BottomStart),
            painter = painterResource(R.drawable.facturabackground),
            contentDescription = "image",
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50))
                .background(color = colorResource(R.color.btnEnviarSolicitud))
                .padding(start = 40.dp, top = 20.dp, bottom = 20.dp)
                .fillMaxWidth(0.8f)
                .align(Alignment.TopEnd),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Column {
                Text(
                    text = "Pago de servicio a:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W300,
                    color = Color.White,
                    lineHeight = 15.sp
                )
                Text(
                    text = "Mario Hernandez",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.White
                )
            }

            Column {
                Text(
                    text = "Fecha: 11 de septiembre 2024",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.W300,
                    color = Color.White
                )
            }
        }
    }

}

@Composable
private fun Body(){

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp)
    ) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color.Transparent,
            ),
            border = BorderStroke(2.5.dp, Color.LightGray)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    text = "Detalle del servicio",
                )
                ItemFactura()
                ItemFactura()
            }
            HorizontalDivider(thickness = 2.5.dp, color = Color.LightGray)
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TextHorizontalSection(
                    title = "Subtotal",
                    content = "$55.00",
                    titleWeight = FontWeight.W300,
                    contentWeight = FontWeight.W500,
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                TextHorizontalSection(
                    title = "Impuesto IVA",
                    content = "$03.80",
                    titleWeight = FontWeight.W300,
                    contentWeight = FontWeight.W500,
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                TextHorizontalSection(
                    title = "Total",
                    content = "$58.80",
                    titleWeight = FontWeight.W500,
                    contentWeight = FontWeight.W500,
                    horizontalArrangement = Arrangement.SpaceBetween
                )
            }
        }
    }

}

@Composable
private fun ItemFactura(){

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier.size(21.dp)
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(R.drawable.circle),
                contentDescription = "icon",
                tint = Color.LightGray
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "1",
                fontSize = 13.sp,
                lineHeight = 15.sp
            )
        }
        TextHorizontalSection(
            title = "Inspeccion Previa",
            content = "$00.00",
            titleWeight = FontWeight.W300,
            contentWeight = FontWeight.W500,
            horizontalArrangement = Arrangement.SpaceBetween
        )
    }

}

@Composable
private fun MetodoPago(){

    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp),
        verticalArrangement = Arrangement.spacedBy(7.5.dp)
    ){
        Text(
            text = stringResource(R.string.lblMetodo),
            fontWeight = FontWeight.W500
        )
        IconLabelHorizontalSection(
            painter = painterResource(R.drawable.visaicon),
            contentDescription = "Pay Method",
            label = "Targeta Hernandez - Nº 7676***",
            iconGap = 15.dp,
            iconWidth = 40.dp,
            iconHeight = 40.dp,
            tint = Color.Unspecified
        )
    }

}

@Composable
private fun Opciones(

){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        OpcionesItem(text = "Descargar Recibo (PDF)") {}
        OpcionesItem(text = "Asistencia o ayuda") {}
    }
}

@Composable
private fun OpcionesItem(
    text: String,
    onClick: () -> Unit
){

    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
    ){
        Row(
            modifier = Modifier
                .padding(top = 5.dp, bottom = 5.dp, start = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.start_icon),
                contentDescription = "icon"
            )
            Text(
                text = text,
                fontSize = 19.sp,
                fontWeight = FontWeight.W300
            )
        }
    }

}

@Composable
private fun Derechos(){

    Column(
        modifier = Modifier
            .padding(top = 20.dp,start = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(R.string.example),
            fontWeight = FontWeight.W300,
            lineHeight = 15.sp
        )
        Text(
            text = stringResource(R.string.derechos),
            fontWeight = FontWeight.W400,
        )
    }

}