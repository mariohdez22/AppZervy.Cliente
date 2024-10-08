package com.example.appzervycliente.Components.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.appzervycliente.R

@Composable
fun CommentSection(
    value: String,
    onValueChange: (String) -> Unit,
    minHeight: Dp = 100.dp,
    shape: Shape = RoundedCornerShape(8.dp)
){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = minHeight),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                stringResource(R.string.lblNotaPlaceholder),
                fontWeight = FontWeight.W200
            )
        },
        maxLines = 5,
        singleLine = false,
        shape = shape
    )
}