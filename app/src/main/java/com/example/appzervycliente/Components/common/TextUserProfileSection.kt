package com.example.appzervycliente.Components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.example.appzervycliente.R

@Composable
fun TextUserProfileSection(
    title: String,
    titleWeight: FontWeight? = null,
    titleSize: TextUnit = TextUnit.Unspecified,
    UserName: String,
    UserNameWeight: FontWeight? = null,
    UserNameSize: TextUnit = TextUnit.Unspecified,
    Description: String,
    DescriptionWeight: FontWeight? = null,
    DescriptionSize: TextUnit = TextUnit.Unspecified,
){
    Column(

    ) {
        Text(
            text = title,
            fontWeight = titleWeight,
            fontSize = titleSize
        )
        Row(

        ){
            Image(
                painter = painterResource(R.drawable.visa_icon),
                contentDescription = ""
            )
        }
    }
}