package com.jth.rooms.repo.model

import androidx.paging.PagedList
import com.google.gson.annotations.SerializedName

data class RoomListData(
    @SerializedName("rooms")
    val rooms: PagedList<Room> ?= null,

    @SerializedName("average")
    val average: PagedList<Average> ?= null
)