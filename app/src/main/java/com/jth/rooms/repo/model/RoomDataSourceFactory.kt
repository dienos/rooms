package com.jth.rooms.repo.model

import android.content.Context
import androidx.arch.core.util.Function
import androidx.paging.DataSource

class RoomDataSourceFactory(context: Context) : DataSource.Factory<Int, Room>() {
    private val roomDataSource: RoomDataSource? = null

    override fun <ToValue : Any?> map(function: Function<Room, ToValue>): DataSource.Factory<Int, ToValue> {
        return super.map(function)
    }

    override fun create(): DataSource<Int, Room> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <ToValue : Any?> mapByPage(function: Function<MutableList<Room>, MutableList<ToValue>>): DataSource.Factory<Int, ToValue> {
        return super.mapByPage(function)
    }


}