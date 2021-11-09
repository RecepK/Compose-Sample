package com.kurban.recep.data.model.entity

import com.google.gson.annotations.SerializedName

enum class WrapperType(val value: String) {
    @SerializedName("track")
    TRACK("track"),

    @SerializedName("collection")
    COLLECTION("collection"),

    @SerializedName("artist")
    ARTIST("artist"),

    @SerializedName("software")
    SOFTWARE("software")
}