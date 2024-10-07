package com.example.appzervycliente.Components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.appzervycliente.R

@Composable
fun IconTextSection(
    painter: Painter,
    contentDescription: String? = null,
    label: String = "Esto es un texto",
    fontWeight: FontWeight? = null,
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
            painter = painter,
            contentDescription = contentDescription
        )
        Text(
            text = label,
            fontWeight = fontWeight
        )
    }

}

@Composable
fun IconTextSection(
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