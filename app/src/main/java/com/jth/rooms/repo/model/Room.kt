package com.jth.rooms.repo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class Room(
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    , @ColumnInfo(name = "room_type") var room_type: String = ""
    , @ColumnInfo(name = "img_url") var img_url: String = ""
    , @ColumnInfo(name = "hash_tags") var hash_tags: String = ""
    , @ColumnInfo(name = "price_title") var price_title: String = ""
    , @ColumnInfo(name = "selling_type")  var selling_type: String = ""
    , @ColumnInfo(name = "is_check")  var is_check: Boolean = false
    , @ColumnInfo(name = "desc")  var desc: String = ""
    , @ColumnInfo(name = "viewType")  var viewType: Int = 0)
