package com.example.appzervycliente.Views.Cliente

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.IconTextHorizontalSection
import com.example.appzervycliente.DTOs.PropuestaServicioDTO
import com.example.appzervycliente.DTOs.SolicitudServicioDTO
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.ROOT_ACEPTACION_SOLICITUD_PAGE
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.Services.ViewModels.PropuestaServicioViewModel
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  EsperaPage(
    navController: NavHostController,
    vmPropuesta: PropuestaServicioViewModel,
    solicitud: SolicitudServicioDTO? = null
){
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )
    val scope = rememberCoroutineScope()

    //var showImagePreview by remember { mutableStateOf(false) }
    //var imagePreview by remember { mutableStateOf<Painter?>(null) }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                navController = navController,
                visible = bottomSheetState.currentValue == SheetValue.Expanded
                        //|| showImagePreview
            )
        },
        sheetContent = {
            BodySheet(solicitud, scope, bottomSheetState){
                //showImagePreview = !showImagePreview
                //imagePreview = it
            }
        },
        sheetPeekHeight = 58.dp
    ) {
        paddingValues ->
        Box(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = 25.dp
                )
                .fillMaxSize()
                .background(color = Color.White)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Cargando(navController, vmPropuesta, solicitud?.tipoCategoria ?: "")
                Publicidad()
            }

            AnimatedVisibility(
                visible =
                        bottomSheetState.currentValue == SheetValue.Expanded
                        //|| showImagePreview
                ,
                enter = fadeIn(animationSpec = tween(durationMillis = 300)),
                exit = fadeOut(animationSpec = tween(durationMillis = 300))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                )
            }


        }

    }

//    AnimatedVisibility(
//        visible = showImagePreview,
//        enter = fadeIn(animationSpec = tween(durationMillis = 300)),
//        exit = fadeOut(animationSpec = tween(durationMillis = 300))
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .clickable {
//                    showImagePreview = !showImagePreview
//                    scope.launch {
//                        bottomSheetState.expand()
//                    }
//                }
//        ){
//            imagePreview?.let {
//                Image(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight(0.4f)
//                        .align(Alignment.Center),
//                    painter = it,
//                    contentDescription = "imagePreview",
//                    contentScale = ContentScale.FillBounds
//                )
//            }
//        }
//    }

}

@Composable
@Preview
private fun EsperaPreview(){
    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        EsperaPage(rememberNavController(), viewModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navController: NavHostController,
    visible: Boolean
){
    val containerColor by animateColorAsState(
        targetValue = if(visible) Color.Black.copy(alpha = 0.5f) else Color.White,
        animationSpec = tween(durationMillis = 75)
    )

    val iconButtonColor by animateColorAsState(
        targetValue = if(visible) Color.Transparent else Color.White,
        animationSpec = tween(durationMillis = 75)
    )

    val iconColor by animateColorAsState(
        targetValue = if(visible) Color.Transparent else Color.Black,
        animationSpec = tween(durationMillis = 75)
    )

    TopAppBar(
        title = { Text("") },
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = iconButtonColor
                ),
                onClick = {
                    navController.popBackStack(
                        Routes.SolicitudDiaPage.route,
                        inclusive = false
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = iconColor
                )
            }
        },
        actions = {
            IconButton(
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = iconButtonColor
                ),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "settings",
                    tint = iconColor
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor
        ),
        windowInsets = WindowInsets(left = 25.dp, right = 25.dp, top = 20.dp)
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BodySheet(
    solicitud: SolicitudServicioDTO? = null,
    scope: CoroutineScope,
    sheetState: SheetState,
    onClickImage: (Painter) -> Unit
){
    val scrollState = rememberScrollState()
//    val context = LocalContext.current
//
//    val images = listOf(
//        painterResource(R.drawable.furniture_assembly_image),
//        painterResource(R.drawable.garden_care_image),
//        painterResource(R.drawable.tv),
//        painterResource(R.drawable.mueble),
//        painterResource(R.drawable.furniture_assembly_image),
//        painterResource(R.drawable.garden_care_image),
//        painterResource(R.drawable.tv),
//        painterResource(R.drawable.mueble),
//    )

    Column(
        modifier = Modifier
            .heightIn(max = 500.dp)
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(25.dp)
        ) {
            Text(
                text = "Detalles de la solicitud",
                fontSize = 25.sp,
                fontWeight = FontWeight.W500,
            )
        }
        HorizontalDivider(thickness = 1.dp)
        Column(
            modifier = Modifier
                .padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(
                text = solicitud?.tituloCategoria ?: "Indefinido",
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
            )
            Text(
                text = solicitud?.descripcionSolicitud ?: "Indefinido"
            )
            Detalles(solicitud)
        }

//        Column(
//            modifier = Modifier
//                .padding(start = 25.dp, end = 25.dp),
//            verticalArrangement = Arrangement.spacedBy(10.dp)
//        ){
//            Text(
//                text = "Detalles de la solicitud",
//                fontSize = 17.sp,
//                fontWeight = FontWeight.W500,
//            )
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(100.dp),
//                horizontalArrangement = Arrangement.spacedBy(5.dp)
//            ){
//                LazyRow(
//                    modifier = Modifier
//                        .padding(bottom = 10.dp)
//                        .weight(1f),
//                    horizontalArrangement = Arrangement.spacedBy(10.dp)
//                ) {
//                    items(images) { image ->
//                        Image(
//                            modifier = Modifier
//                                .fillMaxHeight()
//                                .width(110.dp)
//                                .clip(shape = RoundedCornerShape(10.dp))
//                                .clickable {
//                                    scope.launch {
//                                        sheetState.partialExpand()
//                                    }
//                                    onClickImage(image)
//                                },
//                            painter = image,
//                            contentDescription = "image",
//                            contentScale = ContentScale.Crop
//                        )
//                    }
//                }
//            }
//        }
    }

}

@Composable
private fun Detalles(
    solicitud: SolicitudServicioDTO?
){

    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = "Categoria",
            text = solicitud?.tipoCategoria ?: "Indefinido",
            labelSize = 14.5.sp,
            textSize = 14.5.sp,
            labelWeight = FontWeight.W500,
            textWeight = FontWeight.W300,
            iconGap = 7.dp,
            horizontalArrangement = Arrangement.SpaceBetween
        )
//        IconTextHorizontalSection(
//            painter = painterResource(R.drawable.start_icon),
//            label = "Fecha",
//            text = solicitud?.fechaSolicitud ?: "Indefinido",
//            labelSize = 14.5.sp,
//            textSize = 14.5.sp,
//            labelWeight = FontWeight.W500,
//            textWeight = FontWeight.W300,
//            iconGap = 7.dp,
//            horizontalArrangement = Arrangement.SpaceBetween
//        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = "Tipo de pago",
            text = solicitud?.tipoPago ?: "Indefinido",
            labelSize = 14.5.sp,
            textSize = 14.5.sp,
            labelWeight = FontWeight.W500,
            textWeight = FontWeight.W300,
            iconGap = 7.dp,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        IconTextHorizontalSection(
            painter = painterResource(R.drawable.start_icon),
            label = "Duracion",
            text = "Un dia",
            labelSize = 14.5.sp,
            textSize = 14.5.sp,
            labelWeight = FontWeight.W500,
            textWeight = FontWeight.W300,
            iconGap = 7.dp,
            horizontalArrangement = Arrangement.SpaceBetween
        )
    }

}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//@Preview(showBackground = true)
//private fun SheetPreview(){
//    AppZervyClienteTheme(
//        dynamicColor = false
//    ) {
//        BodySheet(rememberCoroutineScope(), rememberModalBottomSheetState())
//    }
//}


@Composable
private fun Cargando(
    navController: NavHostController,
    vmPropuesta: PropuestaServicioViewModel,
    tipoCategoria: String
){

    val isLoading by vmPropuesta.isLoading
    val errorMessage by vmPropuesta.errorMessage
    var propuestas by vmPropuesta.propuestas

    LaunchedEffect(Unit) {
        vmPropuesta.obtenerPropuestas()
    }

    val time by vmPropuesta.time

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 25.dp,
                    end = 25.dp,
                    bottom = 30.dp
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Encontrando socios cercanos...",
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
            )
            Text(
                text = buildAnnotatedString {
                    append("Estimado de espera: ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W500)){
                        append(time.toString())
                    }
                },
                fontWeight = FontWeight.W300,
                fontSize = 14.sp,
            )
            LinearProgressIndicator(
                modifier = Modifier
                    .height(13.dp)
                    .fillMaxWidth(),
                color = Color.Magenta,
            )
        }
    }
    HorizontalDivider(thickness = 1.dp)

    when{
        isLoading -> {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 25.dp),
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(
//                            start = 25.dp,
//                            end = 25.dp,
//                            bottom = 30.dp
//                        ),
//                    verticalArrangement = Arrangement.spacedBy(10.dp)
//                ) {
//                    Text(
//                        text = "Encontrando socios cercanos...",
//                        fontWeight = FontWeight.W500,
//                        fontSize = 20.sp,
//                    )
//                    Text(
//                        text = buildAnnotatedString {
//                            append("Estimado de espera: ")
//                            withStyle(style = SpanStyle(fontWeight = FontWeight.W500)){
//                                append(time.toString())
//                            }
//                        },
//                        fontWeight = FontWeight.W300,
//                        fontSize = 14.sp,
//                    )
//                    LinearProgressIndicator(
//                        modifier = Modifier
//                            .height(13.dp)
//                            .fillMaxWidth(),
//                        color = Color.Magenta,
//                    )
//                }
//            }
//            HorizontalDivider(thickness = 1.dp)
        }
        errorMessage != null -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No se pudo encontrar socios cercanos")
            }
        }
        else -> {
            LaunchedEffect(Unit) {
                delay(7000)
//                propuestas = propuestas.filter {
//                    it.idSocio != null &&
//                    it.tipoCategoria == tipoCategoria
//                }
                navController.navigate(
                    Routes.AceptacionSolicitudPage.route
                ){
                    popUpTo(Routes.EsperaPage.route){ inclusive = true }
                    popUpTo(Routes.SolicitudDiaPage.route) { inclusive = false }
                }
            }
        }

    }



}

@Composable
private fun Publicidad(){
    Column(
        modifier = Modifier
            .padding(
                top = 30.dp,
                start = 25.dp,
                end = 25.dp,
            ),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Espacio publicitario",
            fontSize = 16.sp,
            fontWeight = FontWeight.W400
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(5){
                AdsPreview(adUnitId = stringResource(R.string.adtestbannerid))
            }
        }
    }

}

@Composable
private fun AdsPreview(
    adUnitId: String
){
    AndroidView(
        factory = { ctx ->
            AdView(ctx).apply {
                setAdSize(AdSize.MEDIUM_RECTANGLE)
                this.adUnitId = adUnitId
                loadAd(AdRequest.Builder().build())
            }
        },
        update = { adview ->
            adview.loadAd(AdRequest.Builder().build())
        }
    )

}