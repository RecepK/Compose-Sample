package com.kurban.recep.presentation.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kurban.recep.presentation.ui.theme.ColorItem


@Composable
fun Indicator(value: Boolean) {
    if (value) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(60.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { CircularProgressIndicator(color = ColorItem) }
    }
}