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
fun EbookCard(data: DataModel?, action: () -> Unit) {
    BaseCard(
        action = action,
        header = {
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
                    .padding(top = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = rememberCoilPainter(data?.artworkUrl100),
                        modifier = Modifier
                            .size(200.dp)
                            .clip(MaterialTheme.shapes.small),
                        contentDescription = "logo",
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillHeight
                    )
                    Column {
                        Text(
                            text = "${data?.trackName}",
                            style = MaterialTheme.typography.h6,
                        )
                        Text(text = "${data?.artistName}")
                    }
                }

                Column(modifier = Modifier.padding(start = 4.dp)) {
                    InfoLineItem(
                        title = "Bilgi",
                        list = ArrayList<Pair<String, String>>().apply {
                            add(Pair("Yazar", "${data?.artistName}"))
                            add(Pair("Çıkış Tarihi", "${data?.releaseDate()}"))
                            add(
                                Pair(
                                    "Boyut",
                                    "${data?.fileSizeBytes?.toDouble()?.div(1000) ?: 1} KB"
                                )
                            )
                        })

                    LineItem(title = "Açıklama", text = "${data?.description}")

                    LineItem(title = "Fiyat", text = "${data?.price} ${data?.currency}")

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        },
    )
}