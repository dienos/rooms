package com.jth.rooms.repo

import com.jth.rooms.repo.model.Average
import com.jth.rooms.repo.model.Room
import com.jth.rooms.repo.model.RoomListData

class MainRepository {
    var filerList : List<Room> = arrayListOf()
    var roomListData : RoomListData =  RoomListData()
    var average : Average ?= null

    companion object {
        private var instance: MainRepository? = null

        fun getInstance(): MainRepository {
            if(instance == null) {
                instance = MainRepository()
            }

            return instance!!
        }
    }
}