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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun IconLabelHorizontalSection(
    painter: Painter,
    contentDescription: String? = null,
    label: String = "Label",
    labelWeight: FontWeight? = null,
    labelSize: TextUnit = TextUnit.Unspecified,
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
    }
}

@Composable
fun IconLabelHorizontalSection(
    imageVector: ImageVector = Icons.Filled.Info,
    contentDescription: String? = null,
    label: String = "Label",
    labelWeight: FontWeight? = null,
    labelSize: TextUnit = TextUnit.Unspecified,
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
    }
}