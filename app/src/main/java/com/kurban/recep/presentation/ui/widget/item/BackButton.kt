package com.kurban.recep.presentation.ui.widget.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun BackButton(action: () -> Unit) {
    Icon(
        imageVector = Icons.Filled.ArrowBack,
        contentDescription = "BackButton",
        tint = Color.White,
        modifier = Modifier
            .clickable { action.invoke() }
            .padding(12.dp)
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colors.onSecondary.copy(0.5f))
            .padding(12.dp),
    )
}