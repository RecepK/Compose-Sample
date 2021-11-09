package com.kurban.recep.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kurban.recep.presentation.ui.theme.ColorBackground
import com.kurban.recep.presentation.ui.theme.ColorItem
import com.kurban.recep.presentation.ui.theme.ColorSearchBarBackground
import com.kurban.recep.presentation.ui.theme.ColorText


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    searchEvent: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        color = ColorSearchBarBackground,
        modifier = modifier
            .padding(1.dp),
    ) {
        fun searchEvent(value: String) {
            searchEvent.invoke(value)
            keyboardController?.hideSoftwareKeyboard()
        }

        TextField(
            value = value.value,
            onValueChange = { value.value = it },
            shape = RoundedCornerShape(8.dp),
            trailingIcon = { TrailingIcon { value.value = "" } },
            leadingIcon = { LeadingIcon { searchEvent(value.value) } },
            colors = textFieldColors(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    searchEvent(value.value)
                }
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}


@Composable
private fun TrailingIcon(click: () -> Unit) {
    Icon(
        imageVector = Icons.Filled.Close,
        contentDescription = "Clear Icon",
        tint = ColorBackground,
        modifier = Modifier
            .clickable { click.invoke() }
            .background(ColorItem.copy(0.7F), CircleShape)
            .padding(4.dp)
    )
}

@Composable
private fun LeadingIcon(click: () -> Unit) {
    Icon(
        imageVector = Icons.Filled.Search,
        contentDescription = "Search Icon",
        tint = ColorItem,
        modifier = Modifier
            .clickable { click.invoke() }
    )
}

@Composable
private fun textFieldColors(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        backgroundColor = ColorBackground,
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        textColor = ColorText,
        cursorColor = ColorItem,
    )
}
