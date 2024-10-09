package com.example.appzervycliente.Components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appzervycliente.R

@Composable
fun TextSection(
    title: String,
    content: String,
    titleSize: TextUnit = TextUnit.Unspecified,
    contentSize: TextUnit = TextUnit.Unspecified,
    titleWeight: FontWeight? = null,
    contentWeight: FontWeight? = null,
    spaceBetween: Dp = 7.5.dp
){
    Column(
        verticalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        Text(
            text = title,
            fontSize = titleSize,
            fontWeight = titleWeight
        )
        Text(
            text = content,
            fontSize = contentSize,
            fontWeight = contentWeight
        )

    }
}

@Composable
fun TextSection(
    title: AnnotatedString = buildAnnotatedString {  },
    content: AnnotatedString = buildAnnotatedString {  },
    titleSize: TextUnit = TextUnit.Unspecified,
    contentSize: TextUnit = TextUnit.Unspecified,
    titleWeight: FontWeight? = null,
    contentWeight: FontWeight? = null,
    spaceBetween: Dp = 7.5.dp
){
    Column(
        verticalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        Text(
            text = title,
            fontSize = titleSize,
            fontWeight = titleWeight
        )
        Text(
            text = content,
            fontSize = contentSize,
            fontWeight = contentWeight
        )

    }
}

@Composable
fun TextSection(
    title: String,
    content: List<String>
){

}