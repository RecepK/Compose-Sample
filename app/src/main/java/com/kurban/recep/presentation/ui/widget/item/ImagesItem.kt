package com.kurban.recep.presentation.ui.widget.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter



@Composable
fun ImagesItem(list: List<String>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = Modifier
            .padding(4.dp)
    ) {
        items(list) { image ->
            Image(
                painter = rememberImagePainter(data = image),
                modifier = modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentDescription = "Image",
                alignment = Alignment.Center,
                contentScale = ContentScale.FillHeight
            )
        }
    }
}