package com.kurban.recep.common

import com.kurban.recep.data.model.entity.MediaType
import com.kurban.recep.data.model.entity.capitalize

object Utils {

    fun getButtonList(): ArrayList<String> {
        return ArrayList<String>().apply {
            add(MediaType.MOVIE.capitalize())
            add(MediaType.MUSIC.capitalize())
            add(MediaType.EBOOK.capitalize())
            add(MediaType.SOFTWARE.capitalize())
        }
    }

    fun <T> sizeControl(data: List<T?>): List<T?> {
        return ArrayList<T?>().apply {
            addAll(data)
            if (data.size % 2 == 1) {
                add(null)
            }
        }
    }
}