package com.jth.rooms.repo

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jth.rooms.repo.model.Room
import com.jth.rooms.repo.model.RoomPositionalDataSource


class RoomDataFactory(private val initRoom: NotNullMutableLiveData<List<Room>>, private val rooms: ArrayList<Room>): DataSource.Factory<Int, Room>() {

    var postLiveData : MutableLiveData<RoomPositionalDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Room> {
        val dataSource = RoomPositionalDataSource(initRoom.value, rooms)

        postLiveData = MutableLiveData()
        postLiveData.postValue(dataSource)

        return dataSource
    }
}