package com.jth.rooms.repo.model

import androidx.paging.PositionalDataSource

class RoomPositionalDataSource(private val initRooms : List<Room>, private val rooms: ArrayList<Room>) :
    PositionalDataSource<Room>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Room>) {
        callback.onResult(loadRangeInternal(params.startPosition, params.loadSize))
    }

    private fun loadRangeInternal(startPosition: Int, loadCount: Int): ArrayList<Room> {
        val modelList: ArrayList<Room> = arrayListOf()
        val endPosition = Math.min(computeCount(), startPosition + loadCount)
        for (i in startPosition until endPosition) {
            modelList.add(rooms[i])
        }
        return modelList
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Room>) {
        val totalCount = initRooms.size
        val position = computeInitialLoadPosition(params, totalCount)
        callback.onResult(initRooms, position, totalCount)
    }

    private fun computeCount(): Int {
        return rooms.size
    }
}