package com.kurban.recep.presentation.ui.widget.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.kurban.recep.data.model.response.DataModel
import com.kurban.recep.presentation.ui.widget.BaseCard
import com.kurban.recep.presentation.ui.widget.item.InfoLineItem
import com.kurban.recep.presentation.ui.widget.item.LineItem


@Composable
fun MovieCard(data: DataModel?, action: () -> Unit) {
    BaseCard(
        action = action,
        header = {
            Image(
                painter = rememberCoilPainter(data?.artworkUrl100),
                contentDescription = "bg",
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colors.surface
                            )
                        ),
                    )
                    .padding(top = 48.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = rememberCoilPainter(data?.artworkUrl100),
                        modifier = Modifier
                            .size(120.dp)
                            .padding(horizontal = 8.dp)
                            .clip(MaterialTheme.shapes.small),
                        contentDescription = "logo",
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillHeight
                    )
                    Text(
                        text = "${data?.trackName}",
                        style = MaterialTheme.typography.h4,
                    )
                }
            }
        },
        content = {
            Column(modifier = Modifier.padding(start = 4.dp)) {
                InfoLineItem(title = "Bilgi", list = ArrayList<Pair<String, String>>().apply {
                    add(Pair("Tür", "${data?.primaryGenreName}"))
                    add(Pair("Yönetmen", "${data?.artistName}"))
                    add(Pair("Çıkış Tarihi", "${data?.releaseDate()}"))
                })

                LineItem(title = "Açıklama", text = "${data?.longDescription}")
                LineItem(
                    title = "Fiyat",
                    text = "Rent:${data?.trackRentalPrice} ${data?.currency} / Buy:${data?.trackHdPrice} ${data?.currency}"
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    )
}