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
import com.kurban.recep.presentation.ui.widget.item.ImagesItem
import com.kurban.recep.presentation.ui.widget.item.InfoLineItem
import com.kurban.recep.presentation.ui.widget.item.LineItem
import com.kurban.recep.presentation.ui.widget.item.LineTitleItem


@Composable
fun SoftwareCard(data: DataModel?, action: () -> Unit) {
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
                            .size(180.dp)
                            .padding(horizontal = 8.dp)
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
                        Text(text = "v:${data?.version}")
                    }
                }

                Column(modifier = Modifier.padding(start = 4.dp)) {
                    InfoLineItem(
                        title = "Bilgi",
                        list = ArrayList<Pair<String, String>>().apply {
                            add(Pair("Tür", "${data?.primaryGenreName}"))
                            add(Pair("Şirket", "${data?.artistName}"))
                            add(Pair("Çıkış Tarihi", "${data?.releaseDate()}"))
                        })

                    data?.screenshotUrls?.let {
                        LineTitleItem(title = "Ekran görüntüleri")
                        ImagesItem(
                            list = it,
                            modifier = Modifier.size(225.dp, 125.dp)
                        )
                    }

                    data?.screenshotUrls?.let {
                        LineTitleItem(title = "iPad Ekran görüntüleri")
                        ImagesItem(
                            list = it,
                            modifier = Modifier.size(240.dp, 220.dp)
                        )
                    }

                    LineItem(title = "Fiyat", text = "${data?.formattedPrice}")
                }
            }
        }
    )
}