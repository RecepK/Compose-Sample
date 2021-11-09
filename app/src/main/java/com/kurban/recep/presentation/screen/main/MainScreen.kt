package com.kurban.recep.presentation.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kurban.recep.common.Utils
import com.kurban.recep.data.model.response.DataModel
import com.kurban.recep.presentation.ui.component.SearchBarComponent
import com.kurban.recep.presentation.ui.component.TabComponent
import com.kurban.recep.presentation.ui.theme.ColorBackground
import com.kurban.recep.presentation.ui.widget.Indicator
import com.kurban.recep.presentation.ui.widget.card.SearchingItemCard
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    action: (DataModel) -> Unit
) {
    val buttonState = remember { mutableStateOf(viewModel.buttonState) }.value
    val searchValueState = remember { mutableStateOf(viewModel.searchValue) }
    val dataListState = remember { mutableStateOf(viewModel.dataList) }.value.value
    val dataList = Utils.sizeControl(data = dataListState)
    val stateIndicator = remember { mutableStateOf(viewModel.stateIndicator) }
    val itemCount = remember { mutableStateOf(viewModel.itemCount) }.value
    val lazyListState = remember { mutableStateOf(viewModel.lazyListState) }.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    ) {
        //SearchBar Component
        SearchBarComponent(value = searchValueState.value) {
            viewModel.resetSearchEvent()
            viewModel.setSearchValue(it)
            viewModel.searchEvent()
        }

        Spacer(modifier = Modifier.padding(top = 5.dp))

        //Tab Component
        TabComponent<String>(
            list = Utils.getButtonList(),
            buttonState = buttonState
        ) { index, item ->
            viewModel.resetSearchEvent()
            viewModel.setMediaType(index)
            viewModel.searchEvent()
        }

        Spacer(modifier = Modifier.padding(top = 5.dp))

        //List
        LazyColumn {
            items(dataList.windowed(2, 2, true)) { item ->
                Row {
                    SearchingItemCard(
                        modifier = Modifier
                            .weight(1F)
                            .padding(8.dp),
                        model = item[0], action = action
                    )
                    SearchingItemCard(
                        modifier = Modifier
                            .weight(1F)
                            .padding(8.dp),
                        model = item[1], action = action
                    )
                }
            }

            itemsIndexed(dataList) { index, item ->
                if (index == dataList.lastIndex && (dataList.lastIndex == itemCount.value).not()) {
                    rememberCoroutineScope().launch {
                        viewModel.setItemCount(index)
                        viewModel.nextPage()
                        lazyListState.value.animateScrollToItem(itemCount.value)
                    }
                }
                if (dataList.lastIndex < itemCount.value) {
                    rememberCoroutineScope().launch {
                        lazyListState.value.animateScrollToItem(0)
                    }
                }
            }
        }
    }

    //Indicator
    Indicator(value = stateIndicator.value.value)
}