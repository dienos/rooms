package com.jth.rooms.repo.model

import androidx.paging.PageKeyedDataSource

class RoomDataSource : PageKeyedDataSource<Int, Room>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Room>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Room>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Room>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}