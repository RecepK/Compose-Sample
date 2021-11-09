package com.kurban.recep.presentation.screen.detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kurban.recep.data.model.entity.WrapperType
import com.kurban.recep.data.model.response.DataModel
import com.kurban.recep.presentation.ui.widget.card.EbookCard
import com.kurban.recep.presentation.ui.widget.card.SoftwareCard
import com.kurban.recep.presentation.ui.widget.item.TrackCard


@Composable
fun DetailScreen(
    data: DataModel?,
    viewModel: DetailViewModel = hiltViewModel(),
    action: () -> Unit
) {
    when (data?.wrapperType) {
        WrapperType.TRACK -> {
            TrackCard(data = data, action = action)
        }
        WrapperType.SOFTWARE -> {
            SoftwareCard(data = data, action = action)
        }
        else -> {
            EbookCard(data = data, action = action)
        }
    }
}