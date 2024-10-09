package com.example.appzervycliente.Components.common

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import com.example.appzervycliente.R

@Composable
fun CustomDropdownMenu(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = ""
) {
    var expanded by remember { mutableStateOf(false) }

    // Anchura del menú desplegable
    var dropdownWidth by remember { mutableStateOf(0) }

    // Posición del menú desplegable
    var dropdownOffset by remember { mutableStateOf(Offset.Zero) }

    Box(modifier = modifier) {
        // Etiqueta y campo de selección
        Column {
            if (label.isNotEmpty()) {
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontFamily = FontFamily.SansSerif
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                    .clickable { expanded = !expanded }
                    .onGloballyPositioned { layoutCoordinates ->
                        // Obtener las dimensiones y posición del campo
                        dropdownWidth = layoutCoordinates.size.width
                        val position = layoutCoordinates.localToWindow(Offset.Zero)
                        dropdownOffset = position
                    }
                    .padding(horizontal = 12.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedOption,
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(id = R.drawable.drop_down_arrow),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Menú desplegable
        if (expanded) {
            val density = LocalDensity.current
            Popup(
                alignment = Alignment.TopStart,
                offset = IntOffset(
                    x = with(density) { dropdownOffset.x.toInt() },
                    y = with(density) { (dropdownOffset.y + 48.dp.toPx()).toInt() }
                ),
                properties = PopupProperties(focusable = true),
                onDismissRequest = { expanded = false }
            ) {
                Column(
                    modifier = Modifier
                        .width(with(density) { dropdownWidth.toDp() })
                        .background(Color.White, shape = RoundedCornerShape(4.dp))
                        .shadow(4.dp)
                ) {
                    options.forEach { option ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onOptionSelected(option)
                                    expanded = false
                                }
                                .padding(12.dp)
                        ) {
                            Text(
                                text = option,
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
