package com.example.appzervycliente.Components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun IconTextHorizontalSection(
    painter: Painter,
    contentDescription: String? = null,
    label: String = "Esto es un texto",
    fontWeight: FontWeight? = null,
    gap: Dp = 4.dp,
    tint: Color = Color.Black
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(gap)
    ) {
        Icon(
            modifier = Modifier
                .size(height = 20.dp, width = 20.dp),
            painter = painter,
            contentDescription = contentDescription,
            tint = tint
        )
        Text(
            text = label,
            fontWeight = fontWeight
        )
    }

}

@Composable
fun IconTextHorizontalSection(
    imageVector: ImageVector,
    contentDescription: String? = null,
    label: String = "Esto es un texto",
    fontWeight: FontWeight?,
    gap: Dp = 4.dp
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(gap)
    ) {
        Icon(
            modifier = Modifier
                .size(height = 20.dp, width = 20.dp),
            imageVector = imageVector,
            contentDescription = contentDescription
        )
        Text(
            text = label,
            fontWeight = fontWeight
        )
    }

}


@Composable
fun IconTextHorizontalSection(
    painter: Painter,
    contentDescription: String? = null,
    label: String = "Label",
    labelWeight: FontWeight? = null,
    labelSize: TextUnit = TextUnit.Unspecified,
    text: String = "",
    textWeight: FontWeight? = null,
    textSize: TextUnit = TextUnit.Unspecified,
    iconGap: Dp = 4.dp,
    iconWidth: Dp = 20.dp,
    iconHeight: Dp = 20.dp,
    horizontalArrangement : Arrangement.Horizontal = Arrangement.Start
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(iconGap)
        ) {
            Icon(
                modifier = Modifier
                    .size(height = iconHeight, width = iconWidth),
                painter = painter,
                contentDescription = contentDescription
            )
            Text(
                text = label,
                fontWeight = labelWeight,
                fontSize = labelSize
            )
        }
        Text(
            text = text,
            fontWeight = textWeight,
            fontSize = textSize
        )
    }
}

@Composable
fun IconTextHorizontalSection(
    imageVector: ImageVector = Icons.Filled.Info,
    contentDescription: String? = null,
    label: String = "Label",
    labelWeight: FontWeight? = null,
    labelSize: TextUnit = TextUnit.Unspecified,
    text: String = "",
    textWeight: FontWeight? = null,
    textSize: TextUnit = TextUnit.Unspecified,
    iconGap: Dp = 4.dp,
    iconWidth: Dp = 20.dp,
    iconHeight: Dp = 20.dp,
    horizontalArrangement : Arrangement.Horizontal = Arrangement.Start
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(iconGap)
        ) {
            Icon(
                modifier = Modifier
                    .size(height = iconHeight, width = iconWidth),
                imageVector = imageVector,
                contentDescription = contentDescription
            )
            Text(
                text = label,
                fontWeight = labelWeight,
                fontSize = labelSize
            )
        }
        Text(
            text = text,
            fontWeight = textWeight,
            fontSize = textSize
        )
    }
}