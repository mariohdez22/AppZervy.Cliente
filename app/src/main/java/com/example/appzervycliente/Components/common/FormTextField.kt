package com.example.appzervycliente.Components.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.appzervycliente.R

@Composable
fun FormTextField(
    value: String,
    label: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    icon: Painter,
    iconContentDescription: String? = null,
    sizeRoundedCorners: Dp = 16.dp,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    maxWidth: Float = 1f,
    visualTransformation: VisualTransformation = VisualTransformation.None
){

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        leadingIcon = {
            Icon(
                painter = icon,
                contentDescription = iconContentDescription
            )
        },
        modifier = Modifier
            .fillMaxWidth(maxWidth)
            .padding(top = 16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
        ),
        shape = RoundedCornerShape(sizeRoundedCorners),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation
    )

}

@Composable
fun FormTextField(
    value: String,
    label: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    icon: Painter,
    iconContentDescription: String? = null,
    sizeRoundedCorners: Dp = 16.dp,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    visualTransformation: VisualTransformation = VisualTransformation.None,
            modifier: Modifier = Modifier // Añadimos este parámetro
){

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        leadingIcon = {
            Icon(
                painter = icon,
                contentDescription = iconContentDescription
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
        ),
        shape = RoundedCornerShape(sizeRoundedCorners),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation
    )

}