package com.kurban.recep.presentation.ui.widget.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun LineItem(title: String, text: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = title, style = MaterialTheme.typography.h6)
    Text(text = text, modifier = Modifier.padding(top = 2.dp))
}

@Composable
fun LineTitleItem(title: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = title, style = MaterialTheme.typography.h6)
}

@Composable
fun InfoLineItem(title: String, list: List<Pair<String, String>>) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = title, style = MaterialTheme.typography.h6)
    Column {
        for (item in list) {
            Text(text = "${item.first}: ${item.second}", modifier = Modifier.padding(top = 2.dp))
        }
    }
}