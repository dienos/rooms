package com.jth.rooms.repo.model

import com.google.gson.annotations.SerializedName

class ParsingRoom(
    @SerializedName("room_type")
    val room_type: Int = -1,

    @SerializedName("img_url")
    var img_url: String = "",

    @SerializedName("hash_tags")
    var hash_tags: ArrayList<String> = arrayListOf(),

    @SerializedName("price_title")
    var price_title: String = "",

    @SerializedName("selling_type")
    var selling_type: Int = -1,

    @SerializedName("is_check")
    var is_check: Boolean = false,

    @SerializedName("desc")
    var desc: String = ""
)