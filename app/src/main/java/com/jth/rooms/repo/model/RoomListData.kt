package com.jth.rooms.repo.model

import com.google.gson.annotations.SerializedName

data class RoomListData(
    @SerializedName("rooms")
    var rooms: ArrayList<Room> = arrayListOf(),

    @SerializedName("average")
    var average: ArrayList<Average> = arrayListOf()
)