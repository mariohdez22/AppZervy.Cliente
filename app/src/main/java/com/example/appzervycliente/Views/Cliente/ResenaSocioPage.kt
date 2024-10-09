package com.example.appzervycliente.Views.Cliente

import android.text.BoringLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appzervycliente.Components.common.AsyncGifImageExpandable
import com.example.appzervycliente.Components.common.CommentSection
import com.example.appzervycliente.Components.common.SettingTopBar
import com.example.appzervycliente.Components.common.TextSection
import com.example.appzervycliente.R
import com.example.appzervycliente.Routes.Routes
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResenaSocioPage(
    navController: NavHostController
){

    var nextSection by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    Scaffold(
        topBar = { SettingTopBar(
            onNavigationIconClick = {
                navController.navigate(Routes.MainPage.route)
            })
        }
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

                if(nextSection){
                    ResenaInfoSecondSection()
                }else{
                    ResenaInfoFirstSection()
                }

            }
            HorizontalDivider(thickness = 1.5.dp)

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(55.dp)
            ) {
                if(nextSection){
                    ResenaCalificacionSecondSection(
                        title = title,
                        comment = comment,
                        onTitleChange = { title = it },
                        onCommentChange = { comment = it}
                    )
                }else{
                    ResenaCalificacionFirstSection()
                }
                ResenaButtonSection(
                    onNextSection = { nextSection = !nextSection },
                    onValidated = { nextSection = !nextSection },
                    nextSection = nextSection
                )
            }

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
fun ResenaInfoFirstSection(

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

@Composable
fun ResenaInfoSecondSection(

){
    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth(),
    ) {
        TextSection(
            title = "Socio: Josue Ortega",
            content = "Rubro: Servicios multiples",
            titleWeight = FontWeight.W500,
            titleSize = 22.5.sp,
            contentSize = 14.sp,
            contentWeight = FontWeight.W300,
            spaceBetween = 3.dp
        )
    }
}

//------------------------------------------------------------[CALIFICACION]

@Composable
fun ResenaCalificacionFirstSection(){
    
    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        Text(
            text = stringResource(R.string.lblPreguntaCalifica),
            fontWeight = FontWeight.W500,
            fontSize = 16.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ){
            AsyncGifImageExpandable(
                url = stringResource(R.string.urlEficiencia),
                contentDescription = "gif",
                shape = CircleShape
            )
            AsyncGifImageExpandable(
                url = stringResource(R.string.urlEficiencia),
                contentDescription = "gif",
                shape = CircleShape
            )
            AsyncGifImageExpandable(
                url = stringResource(R.string.urlEficiencia),
                contentDescription = "gif",
                shape = CircleShape
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
        ) {
            AsyncGifImageExpandable(
                url = stringResource(R.string.urlEficiencia),
                contentDescription = "gif",
                shape = CircleShape
            )
            AsyncGifImageExpandable(
                url = stringResource(R.string.urlEficiencia),
                contentDescription = "gif",
                shape = CircleShape
            )
        }

    }

}

@Composable
fun ResenaCalificacionSecondSection(
    title: String,
    comment: String,
    onTitleChange: (String) -> Unit,
    onCommentChange: (String) -> Unit
){

    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = title,
                onValueChange = onTitleChange,
                placeholder = {
                    Text(stringResource(
                        R.string.lblAgregarTitulo),
                        fontWeight = FontWeight.W200
                    )
                },
                shape = RoundedCornerShape(12.dp)
            )
            CommentSection(
                value = comment,
                onValueChange = onCommentChange,
                minHeight = 200.dp,
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}

//------------------------------------------------------------[BOTONES]

@Composable
fun ResenaButtonSection(
    onNextSection: () -> Unit,
    onValidated: () -> Unit,
    nextSection: Boolean
){
    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        if(nextSection){
            ResenaButtonEndSection(
                onClick = onValidated
            )
        }else{
            ResenaButtonNextSection(
                onClick = onNextSection
            )
        }
    }

}

@Composable
fun ResenaButtonNextSection(
    onClick: () -> Unit,
){

    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.Black
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Text(
                text = stringResource(R.string.btnSiguienteResena)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = ""
            )
        }
    }

}

@Composable
fun ResenaButtonEndSection(
    onClick: () -> Unit,
){

    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.Black
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Text(
                text = stringResource(R.string.btnTerminarResena)
            )
        }
    }

}