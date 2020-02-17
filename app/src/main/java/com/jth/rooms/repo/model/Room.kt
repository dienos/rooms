package com.jth.rooms.repo.model

import com.google.gson.annotations.SerializedName

class Room(
    var id : Int =  -1,

    var viewType : Int = -1,

    @SerializedName("room_type")
    var room_type: String = "",

    @SerializedName("img_url")
    var img_url: String = "",

    @SerializedName("hash_tags")
    var hash_tags: ArrayList<String> = arrayListOf(),

    @SerializedName("price_title")
    var price_title: String = "",

    @SerializedName("selling_type")
    var selling_type: String = "",

    @SerializedName("is_check")
    var is_check: Boolean = false,

    @SerializedName("desc")
    var desc: String = ""
)