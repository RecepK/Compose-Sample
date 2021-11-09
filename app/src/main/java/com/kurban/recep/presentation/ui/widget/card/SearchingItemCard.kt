package com.kurban.recep.presentation.ui.widget.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.kurban.recep.data.model.response.DataModel


@Composable
fun SearchingItemCard(
    model: DataModel?,
    modifier: Modifier = Modifier,
    action: (DataModel) -> Unit
) {
    model?.let {
        Surface(
            modifier.clickable { action.invoke(it) },
            elevation = 1.dp,
            shape = MaterialTheme.shapes.small,
        ) {
            Box(contentAlignment = Alignment.TopEnd) {
                Box(contentAlignment = Alignment.Center) {
                    Column {
                        Image(
                            painter = rememberCoilPainter(model.artworkUrl100),
                            contentDescription = "logo",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.TopCenter
                        )
                        CustomText(text = model.artistName)
                    }
                }
            }
        }
    }
}

@Composable
private fun CustomText(text: String?, modifier: Modifier = Modifier) {
    if (text != null) {
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
                .padding(start = 8.dp, top = 8.dp),
        )
    }
}