package com.kurban.recep.presentation.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kurban.recep.presentation.ui.widget.item.BackButton


@Composable
fun BaseCard(
    action: () -> Unit,
    header: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Box(contentAlignment = Alignment.TopStart) {
            Box(contentAlignment = Alignment.BottomStart) {
                header?.invoke()
            }
            BackButton(action = action)
        }

        content?.invoke()
    }
}