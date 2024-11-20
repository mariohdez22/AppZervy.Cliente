package com.example.appzervycliente.Views.Cliente

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EsperaPage(
    navController: NavHostController
){
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(bottomSheetState.currentValue == SheetValue.Expanded)
        },
        sheetContent = { BodySheet(scope, bottomSheetState) },
        sheetPeekHeight = 72.dp
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
                Cargando()
                Publicidad()
            }

            AnimatedVisibility(
                visible = bottomSheetState.currentValue == SheetValue.Expanded,
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

}

@Composable
@Preview
private fun EsperaPreview(){
    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        EsperaPage(rememberNavController())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
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
                onClick = {}
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
    scope: CoroutineScope,
    sheetState: SheetState
){
    Column(
        modifier = Modifier
            .padding(25.dp)
            .fillMaxWidth()
    ) {
        Text("hola que tal")
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun SheetPreview(){
    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        BodySheet(rememberCoroutineScope(), rememberModalBottomSheetState())
    }
}


@Composable
private fun Cargando(){

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
                text = "Estimado de espera",
                fontWeight = FontWeight.W300,
                fontSize = 14.sp,
            )
        }
    }
    HorizontalDivider(thickness = 1.dp)

}

@Composable
private fun Publicidad(){

    val images = listOf(
        painterResource(R.drawable.furniture_assembly_image),
        painterResource(R.drawable.garden_care_image),
        painterResource(R.drawable.tv),
        painterResource(R.drawable.mueble),
        painterResource(R.drawable.furniture_assembly_image),
        painterResource(R.drawable.tv),
    )


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
            items(images){ image ->
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    painter = image,
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }

}