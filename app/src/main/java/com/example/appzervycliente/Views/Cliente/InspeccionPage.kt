package com.example.appzervycliente.Views.Cliente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.IconTextHorizontalSection
import com.example.appzervycliente.Components.common.SettingTopBar
import com.example.appzervycliente.Components.common.TextHorizontalDotSpacingSection
import com.example.appzervycliente.Components.common.TextHorizontalSection
import com.example.appzervycliente.Components.common.TextSection
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspeccionPage(
    navController: NavHostController
){

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(SheetValue.PartiallyExpanded)
    )

    BottomSheetScaffold(
        modifier = Modifier
            .fillMaxSize(),
        sheetContent = {
            InspeccionSheetBody()
        },
        containerColor = Color.White,
        sheetPeekHeight = 100.dp,
        sheetTonalElevation = 1.5.dp,
    ) {
        Scaffold(
            topBar = { SettingTopBar(
                onNavigationIconClick = {
                    navController.navigate(Routes.MainPage.route)
                })
            }
        ) {
            paddingValues ->
            Column(
                modifier = Modifier
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                InspeccionTitleSection()
                HorizontalDivider(thickness = 2.5.dp)
                InspeccionCodigoSection()
                HorizontalDivider(thickness = 2.5.dp)
                InspeccionInfoSection()
                HorizontalDivider(thickness = 2.5.dp)
                InspeccionPagoSection()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun InspeccionPreview(){

    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        InspeccionPage(rememberNavController())
    }

}

//------------------------------------------------------------[TITLE]

@Composable
fun InspeccionTitleSection(

){
    Column(
        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        TextSection(
            title = stringResource(R.string.lblInspeccionActiva),
            content = stringResource(R.string.txtEjemplo),
            titleSize = 30.sp,
            titleWeight = FontWeight.W400,
            contentWeight = FontWeight.W300
        )
    }
}

//------------------------------------------------------------[CODIGO]

@Composable
fun InspeccionCodigoSection(

){
    Column(
        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
    ) {
        TextHorizontalSection(
            title = stringResource(R.string.lblCodigo),
            content = "657993",
            contentSize = 22.5.sp,
            titleSize = 30.sp,
            titleWeight = FontWeight.W400,
            horizontalArrangement = Arrangement.SpaceBetween,
            contentLetterSpacing = 5.sp,
            contentColor = Color.White,
            contentContainerModifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(colorResource(R.color.lblCodigo))
                .padding(
                    top = 7.dp,
                    bottom = 7.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        )
    }
}

//------------------------------------------------------------[INFO]

@Composable
fun InspeccionInfoSection(

){
    Column(
        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp)
    ) {
       //TextUserProfileSection()
        Text(
            text = stringResource(R.string.lblDatosGenerales),
            fontWeight = FontWeight.W400,
            fontSize = 15.5.sp
        )
        InspeccionFormSection()
    }
}

@Composable
fun InspeccionFormSection(

){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = stringResource(R.string.lblEstado),
            text = "En Curso",
            labelSize = 14.5.sp,
            textSize = 14.5.sp,
            labelWeight = FontWeight.W300,
            textWeight = FontWeight.W300,
            iconGap = 7.dp,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = stringResource(R.string.lblDuracion),
            text = "3 dias",
            labelSize = 14.5.sp,
            textSize = 14.5.sp,
            labelWeight = FontWeight.W300,
            textWeight = FontWeight.W300,
            iconGap = 7.dp,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = stringResource(R.string.lblFechaInicio),
            text = "7 de octubre del 2024",
            labelSize = 14.5.sp,
            textSize = 14.5.sp,
            labelWeight = FontWeight.W300,
            textWeight = FontWeight.W300,
            iconGap = 7.dp,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = stringResource(R.string.lblFechaFinal),
            text = "7 de octubre del 2024",
            labelSize = 14.5.sp,
            textSize = 14.5.sp,
            labelWeight = FontWeight.W300,
            textWeight = FontWeight.W300,
            iconGap = 7.dp,
            horizontalArrangement = Arrangement.SpaceBetween
        )
    }
}

//------------------------------------------------------------[PAGO]

@Composable
fun InspeccionPagoSection(

){
    Column(
        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        TextHorizontalDotSpacingSection(
            title = stringResource(R.string.lblPrecio),
            content = "$35.45",
        )
    }

}

//------------------------------------------------------------[BOTTOM SHEET]

@Composable
fun InspeccionSheetBody(

){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        Text(
            text = stringResource(R.string.lblDetalles),
            fontSize = 22.sp,
            fontWeight = FontWeight.W400,
        )
        TextHorizontalSection(
            title = "Revision de tuberias del baño",
            content = "$35.45",
            titleSize = 16.sp,
            contentSize = 16.sp,
            titleWeight = FontWeight.W400,
            horizontalArrangement = Arrangement.SpaceBetween,
            contentContainerModifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(colorResource(R.color.btnCarritoTopBar))
                .padding(start = 2.dp, end = 2.dp)
        )
        Text(
            text = stringResource(R.string.txtEjemplo),
            fontSize = 13.sp,
            fontWeight = FontWeight.W300
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = stringResource(R.string.lblCategoria),
            text = "Fontaneria",
            labelSize = 14.5.sp,
            textSize = 14.5.sp,
            labelWeight = FontWeight.W300,
            textWeight = FontWeight.W300,
            iconGap = 7.dp,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = stringResource(R.string.lblDuracion),
            text = "3 dias",
            labelSize = 14.5.sp,
            textSize = 14.5.sp,
            labelWeight = FontWeight.W300,
            textWeight = FontWeight.W300,
            iconGap = 7.dp,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        HorizontalDivider(thickness = 2.5.dp)
        TextHorizontalDotSpacingSection(
            title = stringResource(R.string.lblPrecio),
            content = "$35.45",
            titleWeight = FontWeight.Bold,
            contentWeight = FontWeight.Bold
        )
    }
}