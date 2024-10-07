package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.IconTextHorizontalSection
import com.example.appzervycliente.Components.common.SettingTopBar
import com.example.appzervycliente.Components.common.TextCommentSection
import com.example.appzervycliente.Components.common.TextSection
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoPage(
    navController: NavHostController
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { SettingTopBar(scrollBehavior) }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CarritoTitleSection()
            HorizontalDivider(thickness = 2.5.dp)
            CarritoDirectionSection()
            HorizontalDivider(thickness = 2.5.dp)
            CarritoServicioSection()
            HorizontalDivider(thickness = 2.5.dp)
            CarritoMetodoPagoSection()
            HorizontalDivider(thickness = 2.5.dp)
            CarritoDetallesPago()
            HorizontalDivider(thickness = 2.5.dp)
            Button(
                modifier = Modifier.fillMaxWidth(0.45f),
                onClick = {}
            ) {
                Text(
                    text = stringResource(R.string.btnPagar)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CarritoPagePreview(){

    AppZervyClienteTheme(
        dynamicColor = true
    ) {
        CarritoPage(rememberNavController())
    }

}

//------------------------------------------------------------[TITLE]

@Composable
fun CarritoTitleSection(){

    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        TextSection(
            title = stringResource(R.string.lblPagarServicio),
            content = stringResource(R.string.txtPagarServicio),
            titleSize = 30.sp,
            titleWeight = FontWeight.W600,
        )
    }

}

//------------------------------------------------------------[DIRECTION]

@Composable
fun CarritoDirectionSection(){

    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        TextSection(
            title = stringResource(R.string.lblDireccion),
            content = stringResource(R.string.txtEjemplo),
            titleSize = 18.5.sp,
            titleWeight = FontWeight.W500
        )
        TextCommentSection(
            title = stringResource(R.string.lblNota),
            value = text,
            onValueChange = { text = it },
            minHeightCommentBox = 100.dp
        )
    }

}

//------------------------------------------------------------[SERVICIO]

@Composable
fun CarritoServicioSection(){

    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(7.5.dp)
    ) {
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = stringResource(R.string.lblPago),
            fontWeight = FontWeight.W500,
            contentDescription = "icon"
        )
        TextSection(
            title = "Instalacion de televisiones",
            content = stringResource(R.string.txtEjemplo),
            titleSize = 16.sp,
            titleWeight = FontWeight.W300,
            contentSize = 13.5.sp
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = stringResource(R.string.lblSocio),
            fontWeight = FontWeight.W500,
            contentDescription = "icon"
        )
        TextSection(
            title = buildAnnotatedString {
                append("Josue Hernandez | ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append("4.5")
                }
            },
            titleSize = 16.sp,
            titleWeight = FontWeight.W300,
        )
    }

}

//------------------------------------------------------------[METODO PAGO]

@Composable
fun CarritoMetodoPagoSection(){

    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(7.5.dp)
    ){
        Text(
            text = stringResource(R.string.lblMetodo),
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.visa_icon),
            contentDescription = "Pay Method",
            label = "Targeta Hernandez - Nº 7676***",
            gap = 15.dp
        )
    }

}

//------------------------------------------------------------[DETALLES PAGO]

@Composable
fun CarritoDetallesPago(){

    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(7.5.dp)
    ){

    }


}