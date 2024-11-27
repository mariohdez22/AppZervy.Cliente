package com.example.appzervycliente.Components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.appzervycliente.R

@Composable
fun TextUserProfileSection(
    label: String,
    labelWeight: FontWeight? = null,
    labelSize: TextUnit = TextUnit.Unspecified,
    text: String,
    textWeight: FontWeight? = null,
    textSize: TextUnit = TextUnit.Unspecified,
    textHeight: TextUnit = TextUnit.Unspecified,
    contentAlignment: Alignment = Alignment.TopStart,
    contentWidth: Float = 1f,
    textButton: String,
    textButtonWeight: FontWeight? = null,
    textButtonSize: TextUnit = TextUnit.Unspecified,
    textButtonAlignment: Alignment = Alignment.TopStart
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(contentWidth)
                .align(contentAlignment)
        ) {
            Text(
                text = label,
                fontSize = labelSize,
                fontWeight = labelWeight,
            )
            Text(
                text = text,
                fontSize = textSize,
                fontWeight = textWeight,
                lineHeight = textHeight
            )
        }
        Text(
            modifier = Modifier
                .align(textButtonAlignment)
                .clickable(
                    onClick = {},
                ),
            text = textButton,
            fontSize = textButtonSize,
            fontWeight = textButtonWeight,
        )
    }
}