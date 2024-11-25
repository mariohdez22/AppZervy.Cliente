package com.example.appzervycliente.Components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.appzervycliente.R
import com.example.appzervycliente.ui.theme.AppZervyClienteTheme

@Composable
fun TextHorizontalSection(
    titleContainerModifier: Modifier = Modifier,
    contentContainerModifier: Modifier = Modifier,
    title: String,
    content: String,
    titleSize: TextUnit = TextUnit.Unspecified,
    contentSize: TextUnit = TextUnit.Unspecified,
    titleWeight: FontWeight? = null,
    contentWeight: FontWeight? = null,
    titleColor: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    titleLetterSpacing: TextUnit = TextUnit.Unspecified,
    contentLetterSpacing: TextUnit = TextUnit.Unspecified,
    horizontalArrangement : Arrangement.Horizontal = Arrangement.Start
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = titleContainerModifier
        ){
            Text(
                text = title,
                fontSize = titleSize,
                fontWeight = titleWeight,
                letterSpacing = titleLetterSpacing,
                color = titleColor
            )
        }
        Box(
            modifier = contentContainerModifier
        ){
            Text(
                text = content,
                fontSize = contentSize,
                fontWeight = contentWeight,
                letterSpacing = contentLetterSpacing,
                color = contentColor
            )
        }

    }
}

@Composable
fun TextHorizontalDotSpacingSection(
    titleContainerModifier: Modifier = Modifier,
    contentContainerModifier: Modifier = Modifier,
    title: String = "Esto es un titulo",
    content: String = "Esto es el texto",
    titleSize: TextUnit = TextUnit.Unspecified,
    contentSize: TextUnit = TextUnit.Unspecified,
    titleWeight: FontWeight? = null,
    contentWeight: FontWeight? = null,
    titleColor: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    titleLetterSpacing: TextUnit = TextUnit.Unspecified,
    contentLetterSpacing: TextUnit = TextUnit.Unspecified,
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = titleContainerModifier
        ){
            Text(
                text = title,
                fontSize = titleSize,
                fontWeight = titleWeight,
                letterSpacing = titleLetterSpacing,
                color = titleColor
            )
        }
        Text(
            text = stringResource(R.string.puntos),
            modifier = Modifier.weight(1f),
            maxLines = 1
        )
        Box(
            modifier = contentContainerModifier
        ){
            Text(
                text = content,
                fontSize = contentSize,
                fontWeight = contentWeight,
                letterSpacing = contentLetterSpacing,
                color = contentColor
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun previewdot(){
    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        TextHorizontalDotSpacingSection()
    }
}

@Preview(showBackground = true)
@Composable
private fun preview(){
    AppZervyClienteTheme(
        dynamicColor = false
    ) {
        TextHorizontalSection(
            content = "hola",
            title = "adios",
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        )
    }
}
