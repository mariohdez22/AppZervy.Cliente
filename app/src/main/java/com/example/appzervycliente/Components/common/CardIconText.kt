package com.example.appzervycliente.Components.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@Composable
fun CardIconText(
    cardModifier: Modifier = Modifier,
    cardColors: CardColors = CardDefaults.elevatedCardColors(),
    cardElevation: CardElevation = CardDefaults.elevatedCardElevation(),
    cardOnClick: () -> Unit,
    iconModifier: Modifier = Modifier,
    iconPainter: Painter,
    iconTint: Color = Color.Unspecified,
    text: String,
    textSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null,
    textHeight: TextUnit = TextUnit.Unspecified,
    textColor: Color = Color.Unspecified
){
    ElevatedCard(
        modifier = cardModifier,
        colors = cardColors,
        onClick = cardOnClick,
        elevation = cardElevation
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = iconModifier,
                painter = iconPainter,
                contentDescription = "icon",
                tint = iconTint
            )
            Text(
                text = text,
                fontSize = textSize,
                textAlign = textAlign,
                lineHeight = textHeight,
                color = textColor
            )
        }
    }
}

@Composable
fun CardIconText(
    cardModifier: Modifier = Modifier,
    cardColors: CardColors = CardDefaults.elevatedCardColors(),
    cardElevation: CardElevation = CardDefaults.elevatedCardElevation(),
    cardOnClick: () -> Unit,
    iconModifier: Modifier = Modifier,
    iconVector: ImageVector,
    iconTint: Color = Color.Unspecified,
    text: String,
    textSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null,
    textHeight: TextUnit = TextUnit.Unspecified,
    textColor: Color = Color.Unspecified
){
    ElevatedCard(
        modifier = cardModifier,
        colors = cardColors,
        onClick = cardOnClick,
        elevation = cardElevation
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = iconModifier,
                imageVector = iconVector,
                contentDescription = "icon",
                tint = iconTint
            )
            Text(
                text = text,
                fontSize = textSize,
                textAlign = textAlign,
                lineHeight = textHeight,
                color = textColor
            )
        }
    }
}

@Composable
@Preview
private fun Preview(){
    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        CardIconText(
            cardOnClick = {},
            iconPainter = painterResource(R.drawable.personaicon),
            text = "preview text"
        )
    }
}