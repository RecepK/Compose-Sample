package com.kurban.recep.presentation.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kurban.recep.presentation.ui.theme.*


@Composable
fun <T> TabComponent(
    list: List<String>,
    buttonState: MutableState<Int>,
    click: (Int, T) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        color = ColorTabBackground,
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorBackground),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            itemsIndexed(list) { index, item ->
                RowComponent(
                    buttonState = buttonState,
                    index = index,
                    item = item
                ) { index, item ->
                    click.invoke(index, item as T)
                }
            }
        }
    }
}

@Composable
private fun <T> RowComponent(
    buttonState: MutableState<Int>,
    index: Int,
    item: T,
    click: (Int, T) -> Unit
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .background(ColorBackground)
            .padding(4.dp),
    ) {
        Divider(index)
        OutlinedButton(
            modifier = Modifier
                .padding(start = 2.dp, end = 2.dp),
            onClick = {
                buttonState.value = index
                click.invoke(index, item)
            },
            colors = buttonColorsControl(buttonState.value == index),
            border = BorderStroke(0.dp, color = Color.Transparent)
        ) {
            Text(text = "$item", color = buttonTextColorsControl(buttonState.value == index))
        }
    }
}

@Composable
private fun buttonTextColorsControl(state: Boolean): Color {
    return if (state) ColorTabButtonText
    else ColorTabButtonDisableText
}

@Composable
private fun buttonColorsControl(state: Boolean): ButtonColors {
    return if (state) {
        ButtonDefaults.outlinedButtonColors(
            backgroundColor = ColorTabButtonBackground,
            contentColor = ColorTabButtonBackground,
            disabledContentColor = ColorTabButtonBackground,
        )
    } else {
        ButtonDefaults.outlinedButtonColors(
            backgroundColor = ColorTabDisableButtonBackground,
            contentColor = ColorTabDisableButtonBackground,
            disabledContentColor = ColorTabDisableButtonBackground
        )
    }
}

@Composable
private fun Divider(index: Int) {
    if ((index == 0).not()) {
        Divider(
            color = ColorItem,
            thickness = 4.dp,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 1.dp, end = 4.dp)
                .width(1.dp)
        )
    }
}