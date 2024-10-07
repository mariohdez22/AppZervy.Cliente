package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.SettingTopBar
import com.example.appzervycliente.Components.common.TextSection
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResenaSocioPage(
    navController: NavHostController
){
    Scaffold(
        topBar = { SettingTopBar() }
    ){
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.spacedBy(35.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(85.dp)
            ) {
                ResenaTitleSection()
                ResenaInfoSection()
            }
            HorizontalDivider(thickness = 1.5.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResenaSocioPreview(){

    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        ResenaSocioPage(rememberNavController())
    }

}

//------------------------------------------------------------[TITLE]

@Composable
fun ResenaTitleSection(

){

    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.widthIn(max = 250.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.lblPregunta),
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.lblCalifica),
                textAlign = TextAlign.Center,
                fontSize = 17.sp,
                fontWeight = FontWeight.W400
            )
        }
    }

}

@Composable
fun ResenaInfoSection(

){
    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth(),
    ) {
        TextSection(
            title = "Reparacion de computadoras",
            content = stringResource(R.string.txtEjemplo),
            titleWeight = FontWeight.W500,
            titleSize = 17.5.sp,
            contentWeight = FontWeight.W300,
            spaceBetween = 15.dp
        )
    }
}
