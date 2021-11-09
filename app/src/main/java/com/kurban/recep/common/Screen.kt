package com.kurban.recep.common


sealed class Screen(val name: String) {
    object MainScreen : Screen("MainScreen")
    object DetailScreen : Screen("DetailScreen")
}