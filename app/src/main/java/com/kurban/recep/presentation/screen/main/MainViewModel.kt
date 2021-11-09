package com.kurban.recep.presentation.screen.main

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurban.recep.common.Constants
import com.kurban.recep.common.Resource
import com.kurban.recep.data.model.entity.MediaType
import com.kurban.recep.data.model.response.DataModel
import com.kurban.recep.data.model.response.DataResponse
import com.kurban.recep.domain.usecase.DataFetchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: DataFetchUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<Resource<DataResponse>> = MutableStateFlow(Resource.Loading(false))
    val state: StateFlow<Resource<DataResponse>> = _state

    val dataList = mutableStateOf<List<DataModel>>(emptyList())
    val searchValue = mutableStateOf("")
    val buttonState = mutableStateOf(0)

    val stateIndicator = mutableStateOf(false)
    private val page = mutableStateOf(Constants.DEFAULT_PAGE)
    val itemCount = mutableStateOf(Constants.DEFAULT_ITEM_COUNT)
    val lazyListState = mutableStateOf(LazyListState())

    private fun getDataList(term: String, media: MediaType, limit: Int) {
        stateIndicator.value = true
        useCase.getDataList(term = term, media = media, limit = limit).onEach {
            when (it) {
                is Resource.Loading -> {
                    _state.value = Resource.Loading(it.isLoading)
                }
                is Resource.Error -> {
                    _state.value = Resource.Error(message = it.message)
                }
                is Resource.Success -> {
                    _state.value = Resource.Success(data = it.data)
                    dataList.value = it.data.results
                    delay(1000)
                    stateIndicator.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setMediaType(value: Int) {
        buttonState.value = value
    }

    fun setSearchValue(value: String) {
        searchValue.value = value
    }

    fun setItemCount(value: Int) {
        itemCount.value = value
    }

    fun nextPage() {
        page.value += 1
        searchEvent()
    }

    fun resetSearchEvent() {
        page.value = Constants.DEFAULT_PAGE
        itemCount.value = Constants.DEFAULT_ITEM_COUNT
        dataList.value = emptyList()
    }

    fun searchEvent() {
        val term = searchValue.value

        val media = when (buttonState.value) {
            0 -> MediaType.MOVIE
            1 -> MediaType.MUSIC
            2 -> MediaType.EBOOK
            3 -> MediaType.SOFTWARE
            else -> MediaType.UNKNOWN
        }

        if (term.isNotEmpty()) {
            getDataList(term = term, media = media, limit = Constants.PAGE_SIZE * page.value)
        }
    }
}