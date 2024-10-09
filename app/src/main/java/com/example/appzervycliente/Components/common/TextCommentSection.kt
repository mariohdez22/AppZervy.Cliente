package com.example.appzervycliente.Components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appzervycliente.R

@Composable
fun TextCommentSection(
    title: String = "Titulo",
    value: String,
    onValueChange: (String) -> Unit,
    minHeightCommentBox: Dp = 100.dp
){
    Column(
        verticalArrangement = Arrangement.spacedBy(7.5.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.5.sp,
            fontWeight = FontWeight.W500
        )
        CommentSection(
            value = value,
            onValueChange = onValueChange,
            minHeight = minHeightCommentBox
        )
    }
}