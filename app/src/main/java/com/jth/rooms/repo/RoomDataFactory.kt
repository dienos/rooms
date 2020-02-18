package com.jth.rooms.repo

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jth.rooms.repo.model.Room
import com.jth.rooms.repo.model.RoomPositionalDataSource


class RoomDataFactory: DataSource.Factory<Int, Room>() {

    var postLiveData : MutableLiveData<RoomPositionalDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Room> {
        val repo = MainRepository.getInstance()

        val dataSource = RoomPositionalDataSource(repo.filerList, repo.roomListData.rooms)

        postLiveData = MutableLiveData()
        postLiveData.postValue(dataSource)

        return dataSource
    }
}