package com.kurban.recep.presentation.ui.widget.item

import androidx.compose.runtime.Composable
import com.kurban.recep.data.model.response.DataModel
import com.kurban.recep.presentation.ui.widget.card.MovieCard
import com.kurban.recep.presentation.ui.widget.card.SongCard


@Composable
fun TrackCard(data: DataModel?, action: () -> Unit) {
    if (data?.kind == "song") {
        SongCard(data = data, action = action)
    } else {
        MovieCard(data = data, action = action)
    }
}