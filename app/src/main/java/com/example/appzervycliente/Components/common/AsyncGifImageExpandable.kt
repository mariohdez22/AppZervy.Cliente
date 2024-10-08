package com.example.appzervycliente.Components.common

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size
import com.example.appzervycliente.R

@Composable
fun AsyncGifImageExpandable(
    initialSize: Dp = 80.dp,
    finalSize: Dp = 110.dp,
    url: String,
    contentDescription: String?,
    borderWidth: Dp = 2.dp,
    shape: Shape = RectangleShape
){

    var isExpanded by remember { mutableStateOf(false) }

    val imageSize by animateDpAsState(
        targetValue = if (isExpanded) finalSize else initialSize, label = "animacion"
    )

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .decoderFactory(ImageDecoderDecoder.Factory())
            .crossfade(true)
            .diskCachePolicy(CachePolicy.DISABLED)
            .size(Size.ORIGINAL)
            .build(),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(imageSize)
            .clip(shape)
            .clickable {
                isExpanded = !isExpanded
            }
            .border(
                BorderStroke(borderWidth, Color.Black),
                shape
            )
    )


}