package com.kurban.recep.data.model.entity

sealed class MediaType(val value: String) {
    object MOVIE : MediaType("movie")
    object MUSIC : MediaType("music")
    object EBOOK : MediaType("ebook")
    object SOFTWARE : MediaType("software")
    object UNKNOWN : MediaType("")
}

fun MediaType.capitalize(): String {
    return value.capitalize()
}